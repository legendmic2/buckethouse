package com.clone.buckethouse.service;

import org.springframework.stereotype.Service;

@Service
public class StoreContentService {

    public String testService() {
        String res = "Test Service";
        return res + "~~~";
    }
}
