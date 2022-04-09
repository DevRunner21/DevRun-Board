package com.devrun.backend.post.controller;

import com.devrun.backend.common.dto.ApiResponse;
import com.devrun.backend.common.dto.PageResponse;
import com.devrun.backend.post.dto.request.CreatePostRequest;
import com.devrun.backend.post.dto.request.EditPostRequest;
import com.devrun.backend.post.dto.response.ReadPostResponse;
import com.devrun.backend.post.dto.response.ReadPostsResponse;
import com.devrun.backend.post.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<PageResponse<ReadPostsResponse>> readPosts(Pageable pageable) {
        return ApiResponse.ok(postService.getPosts(pageable));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ReadPostResponse> readPost(@PathVariable("id") Long postId) {
        return ApiResponse.ok(postService.getPostDetail(postId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse registerPost(@RequestBody @Valid CreatePostRequest request) {
        postService.createPost(request.getTitle(), request.getContent(), request.getWriterId());

        return ApiResponse.ok(null);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Long> editPost(@PathVariable("id") Long postId, @RequestBody @Valid EditPostRequest request) {
        postService.updatePost(postId, request.getTitle(), request.getContent());

        return ApiResponse.ok(null);
    }

    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> deletePost(@PathVariable("id") Long postId) {
        postService.deletePostById(postId);

        return ApiResponse.ok(null);
    }

}
