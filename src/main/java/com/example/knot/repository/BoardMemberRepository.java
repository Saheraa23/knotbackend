package com.example.knot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knot.entity.BoardMember;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember,Long>{

}
