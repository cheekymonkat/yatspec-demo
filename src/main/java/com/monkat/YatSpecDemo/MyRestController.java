package com.monkat.YatSpecDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    private final BackendService backendService;

    public MyRestController(@Autowired final BackendService backendService){
        this.backendService = backendService;
    }

    @GetMapping(path = "/index")
    public Result getResult(){
        BackEndResult backEndResult = backendService.getResult();
        return new Result(backEndResult.getMessage());
    }


}
