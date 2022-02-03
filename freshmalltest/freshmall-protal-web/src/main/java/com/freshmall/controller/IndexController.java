package com.freshmall.controller;

import com.freshmall.client.ContentClient;
import com.freshmall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    ContentClient contentClient;

    @GetMapping({"index","/"})
    public String index(Model model){
        List<TbContent> contents=contentClient.listByCategoryId(1);
        model.addAttribute("top_loop_contents",contents);
        return "index";
    }



}
