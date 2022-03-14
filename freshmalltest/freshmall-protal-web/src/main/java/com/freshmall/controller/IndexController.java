package com.freshmall.controller;

import com.freshmall.client.ContentClient;
import com.freshmall.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    ContentClient contentClient;

    @GetMapping({"index","/"})
    public String index(Model model){
        ResultCommon<Object> top_loop_contents_resultCommon=contentClient.listByCategoryId(1);
        model.addAttribute("top_loop_contents",top_loop_contents_resultCommon.getData());

        ResultCommon<Object> middle_loop_contents_resultCommon=contentClient.listByCategoryId(2);
        model.addAttribute("middle_loop_contents",middle_loop_contents_resultCommon.getData());

        return "index";
    }

}
