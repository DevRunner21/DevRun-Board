package com.devrun.backend.member.repository;

import com.devrun.backend.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMembersByLoginId(String loginId);

    Optional<Member> findMembersByEmail(String email);

}
