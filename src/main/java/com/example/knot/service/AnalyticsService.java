package com.example.knot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.knot.entity.BoardActivity;
import com.example.knot.exception.ResourceNotFoundException;
import com.example.knot.repository.BoardActivityRepository;

@Service
public class AnalyticsService {
    @Autowired
    BoardActivityRepository BoardRepo;
    public BoardActivity saveData(BoardActivity data)
    {
        if (data.getActionDescription().isBlank()) {
        throw new RuntimeException("Action Description cannot be empty");
    }

           return BoardRepo.save(data);
    }
    public List<BoardActivity>getAllData()
    {
        return BoardRepo.findAll();
    }
    public BoardActivity getUserDetails(Long id)
    {
        return BoardRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Activity not found"));
    }
    public BoardActivity updateDatabase(Long id,BoardActivity data)
    {
        BoardActivity viewData=BoardRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Activity not found"));
        viewData.setId(data.getId());
        viewData.setActionDescription(data.getActionDescription());
        viewData.setTimestamp(data.getTimestamp());
        return BoardRepo.save(viewData);
    }
    public BoardActivity getDelete(Long id)
    {
        BoardActivity stu=BoardRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Activity Not Found"));
        BoardRepo.delete(stu);
        return stu;
    }
     public BoardActivity patchActivity(Long id, BoardActivity data) {

        BoardActivity a = BoardRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activity Not Found"));

        if (data.getActionDescription() != null) {
             a.setActionDescription(data.getActionDescription());
        }

        if (data.getTimestamp() != null) {
            a.setTimestamp(data.getTimestamp());
        }

        return BoardRepo.save(a);
    }
}
