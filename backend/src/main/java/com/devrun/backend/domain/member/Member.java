package com.devrun.backend.domain.member;

import com.devrun.backend.common.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Table(name = "member")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "profile_image")
    private String profileImage;

    @OneToOne(optional = false)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public static Member of(Long id, String username, String provider, String providerId, String profileImage, Permission permission) {
        Assert.notNull(id,"id must not be null!");
        Assert.notNull(username,"username must not be null!");
        Assert.notNull(provider,"provider must not be null!");
        Assert.notNull(providerId,"providerId must not be null!");
        Assert.notNull(profileImage,"profileImage must not be null!");

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setProfileImage(profileImage);
        member.setPermission(permission);

        return member;
    }

    public static Member of(String username, String provider, String providerId, String profileImage, Permission permission) {
        Assert.notNull(username,"username must not be null!");
        Assert.notNull(provider,"provider must not be null!");
        Assert.notNull(providerId,"providerId must not be null!");
        Assert.notNull(profileImage,"profileImage must not be null!");

        Member member = new Member();
        member.setUsername(username);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setProfileImage(profileImage);
        member.setPermission(permission);

        return member;
    }

}
