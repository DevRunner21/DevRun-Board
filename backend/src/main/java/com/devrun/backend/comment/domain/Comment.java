package com.devrun.backend.comment.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import com.devrun.backend.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
@Table(name = "comment")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Lob
    @Column(name = "content", length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_comment_to_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_comment_id")
    private Comment superComment;

    @OneToMany(mappedBy = "superComment", cascade = CascadeType.ALL)
    private final List<Comment> subComments = new ArrayList<>();

    public static Comment of(Long id, String content, Member member, Comment superComment, List<Comment> subComments) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent(content);
        comment.setMember(member);
        comment.changeSuperComment(superComment);
        subComments.forEach(comment::addSubComment);

        return comment;
    }

    public static Comment of(String content, Member member, Comment superComment, List<Comment> subComments) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setMember(member);
        comment.changeSuperComment(superComment);
        subComments.forEach(comment::addSubComment);

        return comment;
    }

    public void addSubComment(Comment subComment) {
        subComment.changeSuperComment(this);
    }

    private void changeSuperComment(Comment comment) {
        if (Objects.nonNull(this.superComment)) {
            this.superComment.getSubComments().remove(this);
        }
        this.superComment = comment;
        comment.getSubComments().add(this);
    }

}
