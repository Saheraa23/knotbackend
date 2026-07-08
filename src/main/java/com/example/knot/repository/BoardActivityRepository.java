package com.example.knot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knot.entity.BoardActivity;

@Repository
public interface BoardActivityRepository extends JpaRepository<BoardActivity,Long> {

}
