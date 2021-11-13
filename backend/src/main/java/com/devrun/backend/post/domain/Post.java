package com.devrun.backend.post.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import com.devrun.backend.member.domain.Member;
import com.devrun.backend.tag.domain.TagPost;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Lob
    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_post_to_member"))
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagPost> tagPosts = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_to_category"))
//    private Category category;

    public static Post of(Long id, String title, String content, Member member, List<TagPost> tagPosts) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setViewCount(0L);
        post.setMember(member);
        tagPosts.stream().forEach(post::addTagPost);

        return post;
    }

    public static Post of(String title, String content, Member member, List<TagPost> tagPosts) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setViewCount(0L);
        post.setMember(member);
        tagPosts.forEach(post::addTagPost);

        return post;
    }

    public void addTagPost(TagPost tagPost) {
        tagPost.changePost(this);
    }

    public void plusViewCount() {
        this.viewCount++;
    }

    public void updatePost(String title, String content, List<TagPost> tagPosts) {
        this.title = title;
        this.content = content;
        this.tagPosts = tagPosts;
    }

}
