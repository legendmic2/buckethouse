package com.clone.buckethouse.controller;

import com.clone.buckethouse.dto.ResponseDTO;
import com.clone.buckethouse.service.StoreContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreContentService service;

    @GetMapping("/contents")
    public ResponseEntity<?> testContents() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response.getData());
    }
}
