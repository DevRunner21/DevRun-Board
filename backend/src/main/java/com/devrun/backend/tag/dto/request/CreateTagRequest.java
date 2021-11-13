package com.devrun.backend.tag.dto.request;

import com.devrun.backend.tag.domain.Tag;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateTagRequest {

    @NotBlank(message = "Tag 이름은 필수 입력값입니다.")
    private String name;

    public Tag convertToTag() {
        return Tag.of(this.name);
    }

}
