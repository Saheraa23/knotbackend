package com.example.knot.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knot.entity.BoardMember;
import com.example.knot.service.MembershipService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {
   @Autowired
   MembershipService memser;
   @PostMapping("/postmember")
   public BoardMember saveData(@Valid @RequestBody BoardMember data)
   {
      return memser.saveData(data);
   } 
   @GetMapping("/getmember")
   public List<BoardMember> getData(){
      return memser.getAllData();
   }
   @GetMapping("/getmember/{id}")
   public BoardMember getUserData(@PathVariable Long id)
   {
       return memser.getUserDetails(id);
   }  
   @PutMapping("/putmember/{id}")
   public BoardMember updateData (@PathVariable Long id,@RequestBody BoardMember data)
   {
      return memser.updateDatabase(id,data);
   }
   @DeleteMapping("/deletemember/{id}")
   public BoardMember  getDeleteData(@PathVariable Long id)
   {
      return memser.getDelete(id);
   }
   @PatchMapping("/patchmember/{id}")
   public BoardMember patchData(@PathVariable Long id,@RequestBody BoardMember data)
   {
       return memser.patchMember(id, data);
   }
}
