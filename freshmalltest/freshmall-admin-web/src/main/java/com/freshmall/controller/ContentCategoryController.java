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

    @RequestMapping("page/{pageIndex}")
    public String page(@PathVariable("pageIndex") Long pageIndex,
                       @RequestParam(required = false,defaultValue = "8") Long pageSize,
                       @RequestParam(required = false,defaultValue = "") String name, Model model){
        PageQuery pageQuery = new PageQuery(pageIndex,pageSize,name);
        ResultCommon<PageUtils<TbContentCategory>> resultCommon = contentCategoryClient.page(pageQuery);
        PageUtils pageUtils = (PageUtils) resultCommon.getData();
        model.addAttribute("pageUtils",pageUtils);
        model.addAttribute("name",name);
        return "tbContentCategory";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultCommon add(TbContentCategory tbContentCategory){
        return contentCategoryClient.add(tbContentCategory);
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public ResultCommon findOne(@PathVariable("id") Long id){
        System.out.println(id);return contentCategoryClient.findOne(id);
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultCommon update(TbContentCategory tbContentCategory){
        System.out.println(tbContentCategory);
        return contentCategoryClient.update(tbContentCategory);
    }

    @RequestMapping("/deleteOne/{id}")
    @ResponseBody
    public ResultCommon deleteOne(@PathVariable("id") Long id){
        return contentCategoryClient.deleteOne(id);
    }

    @RequestMapping("/deleteSelectList/{ids}")
    @ResponseBody
    public ResultCommon deleteSelectList(@PathVariable("ids") String ids){
        return contentCategoryClient.deleteSelectList(ids);
    }
}
