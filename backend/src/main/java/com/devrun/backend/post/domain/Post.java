package com.devrun.backend.post.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import com.devrun.backend.member.domain.Member;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "tb_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", length = 1000)
    private String content;

    @Builder.Default
    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_member_id_for_post"))
    private Member member;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TagPost> tagPosts = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_to_category"))
//    private Category category;


    public Post(Long id, String title, String content, Long viewCount, Member member) {
        Assert.notNull(title, "title must not be null!");
        Assert.notNull(content, "content must not be null!");
        Assert.notNull(member, "member must not be null!");
//        Assert.notNull(tagPosts,"tagPosts must not be null!");

        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.member = member;
    }

    public void plusViewCount() {
        this.viewCount++;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
