package com.devrun.backend.member.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "member",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_user_login_id", columnNames = {"login_id"}),
                @UniqueConstraint(name = "UK_user_login_email", columnNames = {"email"})
        }
)
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id", nullable = false, length = 30)
    private String loginId;

    @Column(name = "login_pw", nullable = false, length = 100)
    private String loginPw;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public static Member of(Long id, String loginId, String loginPw, String name, String email) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setLoginPw(loginPw);
        member.setName(name);
        member.setEmail(email);

        return member;
    }

    public static Member of(String loginId, String loginPw, String name, String email) {
        Member member = new Member();
        member.setName(name);
        member.setLoginPw(loginPw);
        member.setName(name);
        member.setEmail(email);

        return member;
    }

}
