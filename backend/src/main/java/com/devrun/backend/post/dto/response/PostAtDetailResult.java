package com.devrun.backend.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostAtDetailResult {

    private Long id;

    private String title;

    private String content;

    private String writerId;

    private List<TagAtPostDetailResult> tags = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;


    public static PostAtDetailResult of(Long id, String title, String content, String writerId,
            List<TagAtPostDetailResult> tags, LocalDateTime createdAt) {
        PostAtDetailResult postAtDetailResult = new PostAtDetailResult();
        postAtDetailResult.setId(id);
        postAtDetailResult.setTitle(title);
        postAtDetailResult.setContent(content);
        postAtDetailResult.setWriterId(writerId);
        postAtDetailResult.setTags(
                tags.stream()
                        .map(tag -> TagAtPostDetailResult.of(tag.getId(), tag.getName()))
                        .collect(Collectors.toList())
        );
        postAtDetailResult.setCreatedAt(createdAt);

        return postAtDetailResult;
    }

}
