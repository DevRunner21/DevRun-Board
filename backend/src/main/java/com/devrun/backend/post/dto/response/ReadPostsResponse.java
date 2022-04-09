package com.devrun.backend.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.*;

@Getter
public class ReadPostsResponse {

    private Long id;

    private String title;

    private String writerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Builder
    public ReadPostsResponse(Long id, String title, String writerId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.createdAt = createdAt;
    }

}
