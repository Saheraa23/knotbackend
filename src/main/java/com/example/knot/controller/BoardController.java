package com.example.knot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.knot.entity.BrainstormingBoard;
import com.example.knot.service.BoardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Board")
public class BoardController {
    @Autowired
    BoardService Bser;
    @PostMapping("/postData")
    public BrainstormingBoard saveData(@Valid @RequestBody BrainstormingBoard data)
   {
       return Bser.saveData(data);
   }     
   @GetMapping("/getData")
   public List<BrainstormingBoard> getData()
   {
       return Bser.getAllData();
   }
   @GetMapping("/getData/{id}")
   public BrainstormingBoard getUserData(@PathVariable Long id)
   {
       return Bser.getUserDetails(id);
   }
   @PutMapping("/putData/{id}")
   public BrainstormingBoard updateData(@PathVariable Long id,@RequestBody BrainstormingBoard data)
   {
      return Bser.updateDatabase(id,data);
   }
   @DeleteMapping("/deleteData/{id}")
   public BrainstormingBoard getDeleteData(@PathVariable Long id)
   {
       return Bser.getDelete(id);
   }
   @PatchMapping("/patchData/{id}")
   public BrainstormingBoard patchData(@PathVariable Long id,@RequestBody BrainstormingBoard data)
   {
    return Bser.patchBoard(id, data);
   }
}
