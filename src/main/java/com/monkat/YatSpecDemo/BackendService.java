package com.monkat.YatSpecDemo;

import org.springframework.stereotype.Service;

@Service
public class BackendService {
    public BackEndResult getResult() {
        return new BackEndResult("id1", "Goodbye World");
    }
}
