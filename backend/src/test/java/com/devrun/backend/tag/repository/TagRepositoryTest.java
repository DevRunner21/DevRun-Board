package com.devrun.backend.tag.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.devrun.backend.domain.tag.Tag;
import com.devrun.backend.domain.tag.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@DisplayName("TagRepository 테스트")
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("태그 생성 테스트")
    void save_tag() {
        Tag newTag = Tag.of("자바");

        Tag savedTag = tagRepository.save(newTag);

        assertThat(savedTag.getId(), is(newTag.getId()));
    }

}
