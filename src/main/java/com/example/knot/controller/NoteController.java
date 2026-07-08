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

import com.example.knot.entity.StickyNote;
import com.example.knot.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Note")
public class NoteController {
   @Autowired 
   NoteService NSer;
   @PostMapping("/postNote")
   public StickyNote saveData(@Valid @RequestBody StickyNote data)
   {  
       return NSer.saveData(data);
   } 
   @GetMapping("/getNote")
   public List<StickyNote> getData()
   {
      return NSer.getAllData();
   }
   @GetMapping("/getNote/{id}")
   public StickyNote getUserData(@PathVariable Long id)
   {
       return NSer.getUserDetails(id);
   }
   @PutMapping("/putNote/{id}")
   public StickyNote updateData (@PathVariable Long id,@RequestBody StickyNote data)
   {
      return NSer.updateDatabase(id,data);
   }
   @DeleteMapping("/deleteNote/{id}")
   public StickyNote getDeleteData(@PathVariable Long id)
   {
       return NSer.getDelete(id);
   }
   @PatchMapping("/patchNote/{id}")
   public StickyNote patchData(@PathVariable Long id,@RequestBody StickyNote data) 
   {
       return NSer.patchNote(id, data);
   }
}
