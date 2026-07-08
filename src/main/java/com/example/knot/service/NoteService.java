package com.example.knot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.knot.entity.StickyNote;
import com.example.knot.exception.NoteConflictException;
import com.example.knot.exception.StickyNoteNotFoundException;
import com.example.knot.repository.StickyNoteRepository;

@Service
public class NoteService {
    @Autowired
    StickyNoteRepository StickyRepo;
    public StickyNote saveData(StickyNote data)
    {
       if (data.getContent().isBlank())
            {
             throw new RuntimeException("Content cannot be empty");
            }  
            
        return StickyRepo.save(data);
    }
    public List<StickyNote>getAllData()
    {
        return StickyRepo.findAll();
    }
    public StickyNote getUserDetails(Long id)
    {
        return StickyRepo.findById(id).orElseThrow(()->new StickyNoteNotFoundException("Sticky Note not found"));
    }
    public StickyNote updateDatabase(Long id,StickyNote data)
    {
        StickyNote viewData=StickyRepo.findById(id).orElseThrow(()->new StickyNoteNotFoundException("Sticky Note not found"));
        
        if (!viewData.getVersion().equals(data.getVersion())) {
            throw new NoteConflictException("Msg:This record has been modified by another user");
        }
        viewData.setId(data.getId());
        viewData.setContent(data.getContent());
        viewData.setColorCode(data.getColorCode());
        viewData.setXPos(data.getXPos());
        viewData.setYPos(data.getYPos());
       // viewData.setVersion(data.getVersion());
        viewData.setDeletedAt(data.getDeletedAt());
        viewData.setCreatedAt(data.getCreatedAt());

      // viewData.setVersion(viewData.getVersion() + 1);

        return StickyRepo.save(viewData);

    }
    public StickyNote getDelete(Long id)
    {
        StickyNote stu=StickyRepo.findById(id).orElseThrow(()->new StickyNoteNotFoundException("Sticky Note Not Found"));
        StickyRepo.delete(stu);
        return stu;
    }
    public StickyNote patchNote(Long id, StickyNote data) {

    StickyNote note = StickyRepo.findById(id)
            .orElseThrow(() -> new StickyNoteNotFoundException("Sticky Note Not Found"));

    if (data.getContent() != null) {
        note.setContent(data.getContent());
    }

    if (data.getColorCode() != null) {
        note.setColorCode(data.getColorCode());
    }
     if (data.getXPos() != null) {
        note.setXPos(data.getXPos());
    }

    if (data.getYPos() != null) {
        note.setYPos(data.getYPos());
    }

    if (data.getDeletedAt() != null) {
        note.setDeletedAt(data.getDeletedAt());
    }

    if (data.getCreatedAt() != null) {
        note.setCreatedAt(data.getCreatedAt());
    }

    /* if(data.getVersion() != null) {
        note.setVersion(data.getVersion());
    } */
    return StickyRepo.save(note);
   }
}
