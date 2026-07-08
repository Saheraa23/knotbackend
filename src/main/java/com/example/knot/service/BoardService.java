package com.example.knot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.knot.entity.BrainstormingBoard;
import com.example.knot.exception.StickyNoteNotFoundException;
import com.example.knot.repository.BoardRepository;

@Service
public class BoardService {
    @Autowired
    BoardRepository Brepo;
    public BrainstormingBoard saveData( BrainstormingBoard data)
    {
         if (data.getMaxNoteCapacity() <= 0) {
            throw new RuntimeException("Maximum capacity must be greater than zero");
    }
           return Brepo.save(data);
    }
    public List<BrainstormingBoard>getAllData()
    {
        return Brepo.findAll();
    }
    public BrainstormingBoard getUserDetails(Long id)
    {
        return Brepo.findById(id).orElseThrow(()->new StickyNoteNotFoundException("Board not found"));
    }
    public BrainstormingBoard updateDatabase(Long id,BrainstormingBoard data)
    {
        BrainstormingBoard viewData=Brepo.findById(id).orElseThrow(()->new StickyNoteNotFoundException("Board Not Found"));
        viewData.setId(data.getId());
        viewData.setTitle(data.getTitle());
        viewData.setDescription(data.getDescription());
        viewData.setMaxNoteCapacity(data.getMaxNoteCapacity());
        viewData.setCurrentNoteCount(data.getCurrentNoteCount());
        viewData.setCreatedAt(data.getCreatedAt());
        return Brepo.save(viewData);
    }
    public BrainstormingBoard getDelete(Long id)
    {
        BrainstormingBoard stu=Brepo.findById(id).orElseThrow(()->new StickyNoteNotFoundException("Board Not Found"));
        Brepo.delete(stu);
        return stu;
    } 
    public BrainstormingBoard patchBoard(Long id, BrainstormingBoard data) {

    BrainstormingBoard board = Brepo.findById(id)
            .orElseThrow(() -> new StickyNoteNotFoundException("Board Not Found"));

    if (data.getTitle() != null) {
        board.setTitle(data.getTitle());
    }

    if (data.getDescription() != null) {
        board.setDescription(data.getDescription());
    }
    if (data.getMaxNoteCapacity() != null) {
        board.setMaxNoteCapacity(data.getMaxNoteCapacity());
    }

    if (data.getCurrentNoteCount() != null) {
        board.setCurrentNoteCount(data.getCurrentNoteCount());
    }
    if (data.getCreatedAt() != null) {
        board.setCreatedAt(data.getCreatedAt());
    }
    
    return Brepo.save(board);
   }
}
