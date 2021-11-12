package com.devrun.backend.tag.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import com.devrun.backend.post.domain.Post;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tag_post")
@Getter
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

    @Builder
    public TagPost(Long id, Post post, Tag tag) {
        this.id = id;
        this.post = post;
        this.tag = tag;
    }

    public void changePost(Post post) {
        if (Objects.nonNull(this.post)) {
            post.getTagPosts().remove(this);
        }
        this.post = post;
        post.getTagPosts().add(this);
    }

}
