package com.devrun.backend.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;

@Getter
public class ReadPostResponse {

    private Long id;

    private String title;

    private String content;

    private String writerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Builder
    public ReadPostResponse(Long id, String title, String content, String writerId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.createdAt = createdAt;
    }

}
