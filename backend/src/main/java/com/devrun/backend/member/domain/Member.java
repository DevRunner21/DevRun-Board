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

@Entity
@Table(
        name = "member",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_user_login_id", columnNames = {"login_id"}),
                @UniqueConstraint(name = "UK_user_login_email", columnNames = {"email"})
        }
)
@Getter
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

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String email) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.email = email;
    }

}
