package com.devrun.backend.tag.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tag_post")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_post_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_tag_post_to_post"))
//    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_tag_post_to_tag"))
    private Tag tag;

    public static TagPost of(Tag tag) {
        TagPost tagPost = new TagPost();
        tagPost.setTag(tag);

        return tagPost;
    }

}
