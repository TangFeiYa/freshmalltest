package com.freshmall.controller;

import com.freshmall.client.ContentClient;
import com.freshmall.pojo.TbContent;

import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("content")
@Slf4j
public class ContentController {
    @Autowired
    ContentClient contentClient;

    @RequestMapping("page/{pageIndex}")
    String page(@PathVariable("pageIndex") Long pageIndex,
                @RequestParam(required = false,defaultValue = "8") Long pageSize,
                @RequestParam(required = false,defaultValue = "") String title, Model model){
        PageQuery pageQuery=new PageQuery(pageIndex,pageSize,title);
        ResultCommon<PageUtils<TbContent>> resultCommon = contentClient.page(pageQuery);
        PageUtils<TbContent> pageUtils = (PageUtils<TbContent>) resultCommon.getData();
        model.addAttribute("pageUtils",pageUtils);
        model.addAttribute("title",title);
        return "tbContent";
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    ResultCommon<Object> findOne(@PathVariable("id") Long id){
        return contentClient.findOne(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    ResultCommon<Object> delete(@PathVariable("id") Long id){
        return contentClient.delete(id);
    }

    @PutMapping("/update")
    @ResponseBody
    ResultCommon<Object> update(TbContent tbContent){
        return contentClient.update(tbContent);
    }

    @PostMapping("add")
    @ResponseBody
    ResultCommon<Object> add(TbContent tbContent){
        System.out.println(tbContent);return contentClient.add(tbContent);
    }
}
