package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원 가입 시 중복된 회원 있는지 검사
    Member findByEmail(String email);
}
