package com.devrun.backend.post.dto.request;

import com.devrun.backend.domain.member.Member;
import com.devrun.backend.domain.post.Post;
import com.devrun.backend.domain.tag.TagPost;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class EditPostRequest {

    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    @Length(max = 200, message = "200자 이하로 적어주세요.")
    private String title;

    @Length(max = 1000, message = "1000자 이하로 적어주세요.")
    private String content;

    private final List<Long> tagIds = new ArrayList<>();

    public Post convertToPost(Member member, List<TagPost> tagPosts) {
        return Post.of(title, content, member, tagPosts);
    }

}
