package com.devrun.backend.post.service;

import com.devrun.backend.common.dto.PageResponse;
import com.devrun.backend.common.exception.BusinessException;
import com.devrun.backend.common.exception.ErrorInfo;
import com.devrun.backend.member.domain.Member;
import com.devrun.backend.member.domain.MemberRepository;
import com.devrun.backend.post.domain.Post;
import com.devrun.backend.post.domain.PostRepository;
import com.devrun.backend.post.dto.response.ReadPostResponse;
import com.devrun.backend.post.dto.response.ReadPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    public PageResponse<ReadPostsResponse> getPosts(Pageable pageRequest) {
        Page<ReadPostsResponse> readPostsResponsePage = postRepository.findAll(pageRequest).map(post ->
            ReadPostsResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .writerId(post.getMember().getLoginId())
                .build()
        );

        return PageResponse.<ReadPostsResponse>of(readPostsResponsePage);
    }

    @Transactional
    public ReadPostResponse getPostDetail(Long postId) {
        Post foundPost = postRepository.findPostById(postId)
            .orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));
        foundPost.plusViewCount();

        return ReadPostResponse.builder()
            .id(foundPost.getId())
            .title(foundPost.getTitle())
            .content(foundPost.getContent())
            .writerId(foundPost.getMember().getLoginId())
            .createdAt(foundPost.getCreatedAt())
            .viewCount(foundPost.getViewCount())
            .build();
    }

    @Transactional
    public void createPost(String title, String content, String writerId) {
        Member foundMember = memberRepository.findByLoginId(writerId)
            .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        Post newPost = Post.builder()
            .title(title)
            .content(content)
            .member(foundMember)
            .build();

        postRepository.save(newPost);
    }

    @Transactional
    public void updatePost(Long postId, String title, String content) {
        Post foundPost = postRepository.findById(postId)
            .orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));

        foundPost.updatePost(title, content);
    }

    @Transactional
    public void deletePostById(Long postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));

        postRepository.delete(foundPost);
    }

}
