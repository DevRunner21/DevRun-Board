package com.devrun.backend.tag.controller;

import com.devrun.backend.common.dto.ApiResponse;
import com.devrun.backend.post.dto.request.CreatePostRequest;
import com.devrun.backend.tag.dto.request.CreateTagRequest;
import com.devrun.backend.tag.service.TagService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse create(@RequestBody @Valid CreateTagRequest request) {
        tagService.createNewTag(request);

        return ApiResponse.ok(null);
    }

}
