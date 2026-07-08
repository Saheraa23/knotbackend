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

import com.example.knot.entity.BoardActivity;
import com.example.knot.service.AnalyticsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Activities")
public class InsightController {
   @Autowired
   AnalyticsService aser;
   @PostMapping("/Post")
   public BoardActivity saveData(@Valid @RequestBody BoardActivity data)
   {
    return aser.saveData(data);

   } 
   @GetMapping("/Get")
   public List<BoardActivity> getData()
   {
      return aser.getAllData();
   }
   @GetMapping("/Get/{id}")
   public BoardActivity getUserData (@PathVariable Long id)
   {
       return aser.getUserDetails(id);
   }
   @PutMapping("/Put/{id}")
   public BoardActivity updateData (@PathVariable Long id,@RequestBody BoardActivity data)
   {
      return aser.updateDatabase(id,data);
   }
   @DeleteMapping("/Delete/{id}")
   public BoardActivity getDeleteData(@PathVariable Long id)
   {
       return aser.getDelete(id);
   }
   @PatchMapping("/Patch/{id}")
   public BoardActivity patchData(@PathVariable Long id,@RequestBody BoardActivity data) 
   {
        return aser.patchActivity(id, data);
   }
}
