package com.devrun.backend.post.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.devrun.backend.member.domain.Member;
import com.devrun.backend.member.domain.MemberRepository;
import com.devrun.backend.member.domain.Permission;
import com.devrun.backend.member.domain.PermissionRepository;
import com.devrun.backend.post.domain.Post;
import com.devrun.backend.post.domain.PostRepository;
import com.devrun.backend.post.dto.response.ReadPostResponse;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    void setUp() {
        Permission permission = Permission.builder()
                .name("USER")
                .build();
        permissionRepository.save(permission);

        Member member = Member.builder()
                .loginId("user01")
                .name("김지훈")
                .password("1234")
                .permission(permission)
                .build();
        memberRepository.save(member);

        Post post = Post.builder()
                .member(member)
                .title("title")
                .content("content")
                .build();
        postRepository.save(post);
    }

    @Test
    void getPostDetail는_호출된_만큼_조회수를_증가시킨다() {
        int CALL_COUNT = 3;
        ReadPostResponse postDetail = null;

        for (int i = 0; i < CALL_COUNT; i++) {
            postDetail = postService.getPostDetail(1L);
        }

        assertThat(postDetail.getViewCount()).isEqualTo(CALL_COUNT);
    }

//    @Test
//    void 조회수_증가에_대한_동시성_문제() throws InterruptedException {
//
//        int numberOfThreads = 10;
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            service.execute(() -> {
//                postService.getPostDetail(1L);
//                latch.countDown();
//            });
//        }
//        latch.await();
//
//        ReadPostResponse postDetail = postService.getPostDetail(1L);
//        assertThat(postDetail.getViewCount() - 1).isEqualTo(numberOfThreads);
//
//    }

    @Test
    void 조회수_증가에_대한_동시성_문제_해결() throws InterruptedException {

        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                postService.getPostDetailLock(1L);
                latch.countDown();
            });
        }
        latch.await();

        ReadPostResponse postDetail = postService.getPostDetail(1L);
        assertThat(postDetail.getViewCount() - 1).isEqualTo(numberOfThreads);

    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
        memberRepository.deleteAll();
        permissionRepository.deleteAll();
    }

}
