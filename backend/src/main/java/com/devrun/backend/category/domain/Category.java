package com.devrun.backend.category.domain;

import com.devrun.backend.common.entity.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Table(name = "tb_category")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    public static Category of(Long id, String name) {
        Assert.notNull(id, "id must not be null!");
        Assert.notNull(name, "name must not be null!");

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        return category;
    }

    public static Category of(String name) {
        Assert.notNull(name, "name must not be null!");

        Category category = new Category();
        category.setName(name);

        return category;
    }

}
