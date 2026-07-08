package com.example.knot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.example.knot.entity.BoardMember;
import com.example.knot.exception.ResourceNotFoundException;
import com.example.knot.repository.BoardMemberRepository;

@Service
public class MembershipService {
    @Autowired
    BoardMemberRepository BoardRepo;
    public BoardMember saveData(BoardMember data)
    {
        if (data.getAccessLevel().isBlank()) {
            throw new RuntimeException("AccessLevel cannot be empty");
    }
           return BoardRepo.save(data);
    }
    public List<BoardMember>getAllData()
    {
        return BoardRepo.findAll();
    }
    public BoardMember getUserDetails(Long id)
    {
        return BoardRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Member not found"));
    }
    public BoardMember updateDatabase(Long id,BoardMember data)
    {
        BoardMember viewData=BoardRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Member not found"));
        viewData.setId(data.getId());
        viewData.setAccessLevel(data.getAccessLevel());
        viewData.setJoinedAt(data.getJoinedAt());
        return BoardRepo.save(viewData);
    }
    public BoardMember getDelete(Long id)
    {
        BoardMember stu=BoardRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Member Not Found"));
        BoardRepo.delete(stu);
        return stu;
    }
    public BoardMember patchMember(Long id, BoardMember data) {

    BoardMember member = BoardRepo.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Member Not Found"));

    if (data.getAccessLevel() != null) {
        member.setAccessLevel(data.getAccessLevel());
    }

    if (data.getJoinedAt() != null) {
        member.setJoinedAt(data.getJoinedAt());
    }

    return BoardRepo.save(member);
    }
}
