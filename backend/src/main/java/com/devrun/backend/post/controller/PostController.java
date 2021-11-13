package com.devrun.backend.post.controller;

import com.devrun.backend.common.dto.ApiResponse;
import com.devrun.backend.common.dto.PageResult;
import com.devrun.backend.post.dto.request.CreatePostRequest;
import com.devrun.backend.post.dto.request.EditPostRequest;
import com.devrun.backend.post.dto.response.PostAtDetailResult;
import com.devrun.backend.post.dto.response.PostAtListResult;
import com.devrun.backend.post.service.PostService;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<PageResult<PostAtListResult>> posts(Pageable pageable) {
        return ApiResponse.ok(postService.getPostPagingList(pageable));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<PostAtDetailResult> post(@PathVariable("id") Long postId) {
        return ApiResponse.ok(postService.getPostById(postId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse create(@RequestBody @Valid CreatePostRequest request) {
        postService.createPost(request);

        return ApiResponse.ok(null);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse<Long> update(@PathVariable("id") Long postId, @RequestBody @Valid EditPostRequest request) {
//        postService.updatePost(postId, request);
//
//        return ApiResponse.ok(null);
//    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> delete(@PathVariable("id") Long postId) {
        postService.deletePostById(postId);

        return ApiResponse.ok(null);
    }

}
