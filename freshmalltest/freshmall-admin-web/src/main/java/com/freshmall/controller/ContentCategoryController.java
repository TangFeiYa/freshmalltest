package com.freshmall.controller;

import com.freshmall.client.ContentCategoryClient;
import com.freshmall.pojo.TbContentCategory;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("content_category")
public class ContentCategoryController {
    @Autowired
    ContentCategoryClient contentCategoryClient;

    @GetMapping("list")
    @ResponseBody
    public ResultCommon<Object> list(){
        return contentCategoryClient.list();
    }

    @RequestMapping("page/{pageIndex}")
    public String page(@PathVariable("pageIndex") Long pageIndex,
                       @RequestParam(required = false,defaultValue = "8") Long pageSize,
                       @RequestParam(required = false,defaultValue = "") String name, Model model){
        PageQuery pageQuery = new PageQuery(pageIndex,pageSize,name);
        ResultCommon<PageUtils<TbContentCategory>> resultCommon = contentCategoryClient.page(pageQuery);
        PageUtils<TbContentCategory> pageUtils = (PageUtils<TbContentCategory>) resultCommon.getData();
        model.addAttribute("pageUtils",pageUtils);
        model.addAttribute("name",name);
        return "tbContentCategory";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultCommon<Object> add(TbContentCategory tbContentCategory){
        return contentCategoryClient.add(tbContentCategory);
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public ResultCommon<Object> findOne(@PathVariable("id") Long id){
        return contentCategoryClient.findOne(id);
    }

    @PutMapping("update")
    @ResponseBody
    public ResultCommon<Object> update(TbContentCategory tbContentCategory){
        return contentCategoryClient.update(tbContentCategory);
    }

    @DeleteMapping("/deleteOne/{id}")
    @ResponseBody
    public ResultCommon<Object> deleteOne(@PathVariable("id") Long id){
        return contentCategoryClient.deleteOne(id);
    }

    @DeleteMapping("/deleteSelectList/{ids}")
    @ResponseBody
    public ResultCommon<Object> deleteSelectList(@PathVariable("ids") String ids){
        return contentCategoryClient.deleteSelectList(ids);
    }
}
