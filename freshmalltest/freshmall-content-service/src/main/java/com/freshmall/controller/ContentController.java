package com.freshmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.freshmall.pojo.TbContent;
import com.freshmall.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("content")
public class ContentController {
    @Autowired
    TbContentService tbContentService;
    @Value("${server.port}")
    String port;
    @GetMapping("list")
    public List<TbContent> list(){
        System.out.println("我被调用了，我的端口号是:"+port);
        return tbContentService.list(null);
    }

    @GetMapping("listByCategoryId/{category_id}")
    public List<TbContent> listByCategoryId(@PathVariable("category_id") Integer category_id){
        return tbContentService.list(new QueryWrapper<TbContent>().eq("category_id",category_id));
    }
}
