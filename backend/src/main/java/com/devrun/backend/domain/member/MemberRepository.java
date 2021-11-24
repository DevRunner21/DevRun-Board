package com.devrun.backend.domain.member;

import com.devrun.backend.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMembersByLoginId(String loginId);

    Optional<Member> findMembersByEmail(String email);

}
