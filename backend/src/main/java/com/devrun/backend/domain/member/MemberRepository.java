package com.devrun.backend.domain.member;

import com.devrun.backend.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findMembersByLoginId(String loginId);
//
//    Optional<Member> findMembersByEmail(String email);

    @Query("select m from Member m join fetch m.permission where m.username = :username")
    Optional<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m join fetch m.permission  where m.provider = :provider and m.providerId = :providerId")
    Optional<Member> findByProviderAndProviderId(@Param("provider") String provider, @Param("providerId") String providerId);

}
