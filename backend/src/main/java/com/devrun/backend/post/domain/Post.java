package com.devrun.backend.post.domain;

import com.devrun.backend.category.domain.Category;
import com.devrun.backend.common.entity.BaseTimeEntity;
import com.devrun.backend.member.domain.Member;
import com.devrun.backend.tag.domain.TagPost;
import java.util.ArrayList;
import java.util.List;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private final List<TagPost> tagPosts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_to_category"))
    private Category category;

    @Builder
    public Post(Long id, String title, String content, Long viewCount, Member member, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.member = member;
        this.category = category;
    }

    public void addTagPost(TagPost tagPost) {
        tagPost.changePost(this);
    }

    public void plusViewCount() {
        this.viewCount++;
    }

    public Post updatePost(String title, String content) {
        this.title = title;
        this.content = content;

        return this;
    }

}
