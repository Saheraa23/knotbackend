package com.example.knot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knot.entity.BrainstormingBoard;

@Repository
public interface BoardRepository extends JpaRepository<BrainstormingBoard,Long>{

}
