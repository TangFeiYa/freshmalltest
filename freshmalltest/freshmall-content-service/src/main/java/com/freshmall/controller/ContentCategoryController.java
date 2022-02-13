package com.freshmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshmall.pojo.TbContentCategory;
import com.freshmall.service.TbContentCategoryService;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCode;
import com.freshmall.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("content_category")
public class ContentCategoryController {
    @Autowired
    TbContentCategoryService tbContentCategoryService;

    @GetMapping("list")
    public ResultCommon list(){
        return  ResultCommon.success(ResultCode.SUCCESS,tbContentCategoryService.list(null));
    }

    @RequestMapping("page")
    public ResultCommon<PageUtils<TbContentCategory>> page(@RequestBody PageQuery pageQuery){
//        log.info("服务端--查询的参数是:" + pageQuery);
        QueryWrapper<TbContentCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",pageQuery.getQueryDatas().get(0));
        queryWrapper.orderByDesc("id");
        IPage<TbContentCategory> page=tbContentCategoryService.page(new Page<TbContentCategory>(pageQuery.getPageIndex(),pageQuery.getPageSize()),queryWrapper);
        long total = page.getTotal();
        long pages = page.getPages();
        List<TbContentCategory> records = page.getRecords();
        PageUtils<TbContentCategory> pageUtils = new PageUtils<>(pageQuery.getPageIndex(),pageQuery.getPageSize(),total,pages,records);
        return ResultCommon.success(ResultCode.SUCCESS,pageUtils);
    }

    @RequestMapping("add")
    public ResultCommon add(@RequestBody TbContentCategory tbContentCategory){
        boolean flag = tbContentCategoryService.save(tbContentCategory);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.success(ResultCode.FAIL);
        }
    }

    @GetMapping("findOne/{id}")
    public ResultCommon findOne(@PathVariable("id") Long id){
        System.out.println(id);
        return ResultCommon.success(ResultCode.SUCCESS,tbContentCategoryService.getById(id));
    }

    @RequestMapping("update")
    public ResultCommon update(@RequestBody TbContentCategory tbContentCategory){
        boolean flag = tbContentCategoryService.updateById(tbContentCategory);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.success(ResultCode.FAIL);
        }
    }

    @RequestMapping("deleteOne/{id}")
    public ResultCommon deleteOne(@PathVariable("id") Long id){
        return ResultCommon.success(ResultCode.SUCCESS,tbContentCategoryService.removeById(id));
    }

    @RequestMapping("deleteSelectList/{ids}")
    public ResultCommon deleteSelectList(@PathVariable("ids") String ids){
        String[] idArrays = ids.split(",");
        List<String> idList = Arrays.asList(idArrays);
        return ResultCommon.success(ResultCode.SUCCESS,tbContentCategoryService.removeByIds(idList));
    }
}
