package com.devrun.backend.post.service;

import com.devrun.backend.common.dto.PageResult;
import com.devrun.backend.common.enums.ErrorInfo;
import com.devrun.backend.common.exception.BusinessException;
import com.devrun.backend.domain.member.Member;
import com.devrun.backend.domain.member.MemberRepository;
import com.devrun.backend.domain.post.Post;
import com.devrun.backend.post.dto.request.CreatePostRequest;
import com.devrun.backend.post.dto.request.EditPostRequest;
import com.devrun.backend.post.dto.response.PostAtDetailResult;
import com.devrun.backend.post.dto.response.PostAtListResult;
import com.devrun.backend.post.dto.response.TagAtPostDetailResult;
import com.devrun.backend.domain.post.PostRepository;
import com.devrun.backend.domain.tag.Tag;
import com.devrun.backend.domain.tag.TagPost;
import com.devrun.backend.domain.tag.TagRepository;
import java.util.List;
import java.util.stream.Collectors;
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
    private final TagRepository tagRepository;

    public PageResult<PostAtListResult> getPostPagingList(Pageable pageable) {
        Page<PostAtListResult> postAtListResponses = postRepository.findAll(pageable).map(post ->
            PostAtListResult.of(
                    post.getId(),
                    post.getContent(),
                    post.getMember().getLoginId(),
                    post.getCreatedAt()
            ));

        return PageResult.<PostAtListResult>of(postAtListResponses);
    }

    public PostAtDetailResult getPostById(Long postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));
        foundPost.plusViewCount();

        return PostAtDetailResult.of(
                foundPost.getId(),
                foundPost.getTitle(),
                foundPost.getContent(),
                foundPost.getMember().getLoginId(),
                foundPost.getTagPosts().stream()
                .map(TagPost::getTag)
                .map(tag -> TagAtPostDetailResult.of(tag.getId(), tag.getName()))
                .collect(Collectors.toList()),
                foundPost.getCreatedAt()
        );
    }

    @Transactional
    public void createPost(CreatePostRequest request) {
        Member foundMember = memberRepository.findMembersByLoginId(request.getWriterId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        if (!memberRepository.findMembersByLoginId(request.getWriterId()).isPresent()) {
            throw new BusinessException(ErrorInfo.MEMBER_NOT_FOUND);
        }

        List<Tag> tags = request.getTagIds().stream()
                .map(tagId -> tagRepository.findTagById(tagId)
                        .orElseThrow(() -> new BusinessException(ErrorInfo.TAG_NOT_FOUND))
                ).collect(Collectors.toList());

        List<TagPost> tagPosts = tags.stream()
                .map(tag -> TagPost.of(tag))
                .collect(Collectors.toList());

        Post newPost = request.convertToPost(foundMember, tagPosts);

        postRepository.save(newPost);
    }

    @Transactional
    public void updatePost(Long postId, EditPostRequest request) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));

        // todo : 원래 존재하는 태그들을 수정해야함..
        List<Tag> tags = request.getTagIds().stream()
                .map(tagId -> tagRepository.findTagById(tagId)
                        .orElseThrow(() -> new BusinessException(ErrorInfo.TAG_NOT_FOUND))
                ).collect(Collectors.toList());

        List<TagPost> tagPosts = tags.stream()
                .map(TagPost::of)
                .collect(Collectors.toList());

        foundPost.updatePost(request.getTitle(), request.getContent(), tagPosts);
    }

    @Transactional
    public void deletePostById(Long postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));

        postRepository.delete(foundPost);
    }

}
