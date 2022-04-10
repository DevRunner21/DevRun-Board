package com.devrun.backend.post.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 페이징 조회 + 페치조인
    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Optional<Post> findPostById(Long postId);

}
