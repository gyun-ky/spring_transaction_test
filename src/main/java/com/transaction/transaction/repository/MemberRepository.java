package com.transaction.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.transaction.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
