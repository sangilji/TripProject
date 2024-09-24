package com.example.ryokouikitai.controller.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    @GetMapping("")
    public String store(){
        return "store/store";
    }
}
