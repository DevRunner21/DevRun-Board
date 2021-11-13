package com.devrun.backend.post.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagAtPostDetailResult {

    private Long id;

    private String name;

    public static TagAtPostDetailResult of(Long id, String name) {
        TagAtPostDetailResult tagAtPostDetailResult = new TagAtPostDetailResult();
        tagAtPostDetailResult.setId(id);
        tagAtPostDetailResult.setName(name);

        return tagAtPostDetailResult;
    }

}
