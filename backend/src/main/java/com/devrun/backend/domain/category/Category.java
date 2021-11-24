package com.devrun.backend.domain.category;

import com.devrun.backend.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Table(name = "category")
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
        Assert.notNull(id,"id must not be null!");
        Assert.notNull(name,"name must not be null!");

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        return category;
    }

    public static Category of(String name) {
        Assert.notNull(name,"name must not be null!");

        Category category = new Category();
        category.setName(name);

        return category;
    }

}
