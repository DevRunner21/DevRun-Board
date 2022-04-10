package com.devrun.backend.tag.domain;

import com.devrun.backend.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tag")
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
