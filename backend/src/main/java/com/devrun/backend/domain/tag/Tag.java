package com.devrun.backend.domain.tag;

import com.devrun.backend.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "tag",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_tag_name", columnNames = {"name"}),
        })

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public static Tag of(Long id, String name) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);

        return tag;
    }

    public static Tag of(String name) {
        Tag tag = new Tag();
        tag.setName(name);

        return tag;
    }

}
