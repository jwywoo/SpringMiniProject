package com.example.postcommentauth.member.repository;

import com.example.postcommentauth.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
