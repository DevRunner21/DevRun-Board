package com.devrun.backend.post.repository;

import com.devrun.backend.post.domain.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 페이징 조회 + 페치조인
    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAll(Pageable pageable);

//    @EntityGraph(attributePaths = {"member", "tags"})
    @Query("select p from Post p"
           + " join fetch p.member"
           + " join fetch p.tagPosts tp"
           + " join fetch tp.tag t"
           + " where p.id = :postId")
    Optional<Post> findById(@Param("postId") Long postId);

}
