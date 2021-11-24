package com.devrun.backend.domain.tag;

import com.devrun.backend.domain.tag.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findTagById(Long tagId);

    Optional<Tag> findTagByName(String tagName);

}
