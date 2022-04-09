package com.devrun.backend.post.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
public class EditPostRequest {

    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    @Length(max = 200, message = "200자 이하로 적어주세요.")
    private String title;

    @Length(max = 1000, message = "1000자 이하로 적어주세요.")
    private String content;

}
