package com.freshmall.controller;

import com.freshmall.service.TbContentCategoryService;
import com.freshmall.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("content_category")
public class ContentCategoryController {
    @Autowired
    TbContentCategoryService tbContentCategoryService;

    @GetMapping("list")
    public ResponseVo list(){
        return new ResponseVo("",tbContentCategoryService.getById(1),"");
    }
}
