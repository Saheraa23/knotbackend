package com.example.knot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knot.entity.StickyNote;

@Repository
public interface StickyNoteRepository extends JpaRepository<StickyNote,Long> {

}
