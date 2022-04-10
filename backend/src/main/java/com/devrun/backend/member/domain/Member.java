package com.devrun.backend.member.domain;

import com.devrun.backend.common.entity.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Table(name = "tb_member")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loginId", nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @OneToOne(optional = false)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public Member(Long id, String loginId, String password, String name, String profileImageUrl, Permission permission) {
        Assert.notNull(id, "id must not be null!");
        Assert.notNull(loginId, "loginId must not be null!");
        Assert.notNull(password, "password must not be null!");
        Assert.notNull(name, "name must not be null!");
        Assert.notNull(permission, "permission must not be null!");

        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.permission = permission;
    }

}
