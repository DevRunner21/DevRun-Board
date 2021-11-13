package com.devrun.backend.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostAtListResult {

    private Long id;

    private String title;

    private String writerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static PostAtListResult of(Long id, String title, String writerId, LocalDateTime createdAt) {
        PostAtListResult postAtListResult = new PostAtListResult();
        postAtListResult.setId(id);
        postAtListResult.setTitle(title);
        postAtListResult.setCreatedAt(createdAt);
        postAtListResult.setWriterId(writerId);

        return postAtListResult;
    }

}
