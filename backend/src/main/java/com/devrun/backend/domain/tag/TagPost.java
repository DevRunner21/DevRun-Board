package com.devrun.backend.domain.tag;

import com.devrun.backend.common.entity.BaseTimeEntity;
import com.devrun.backend.domain.post.Post;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tag_post")
@Getter @Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_tag_post_to_post"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_tag_post_to_tag"))
    private Tag tag;

    public static TagPost of(Tag tag) {
        TagPost tagPost = new TagPost();
        tagPost.setTag(tag);

        return tagPost;
    }

    public void changePost(Post post) {
        if (Objects.nonNull(this.post)) {
            post.getTagPosts().remove(this);
        }
        this.post = post;
        post.getTagPosts().add(this);
    }

}
