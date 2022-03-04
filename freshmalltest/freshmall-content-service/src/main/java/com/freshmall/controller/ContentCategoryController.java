package com.freshmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshmall.pojo.TbContent;
import com.freshmall.pojo.TbContentCategory;
import com.freshmall.service.TbContentCategoryService;
import com.freshmall.service.TbContentService;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCode;
import com.freshmall.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("content_category")
public class ContentCategoryController {
    @Autowired
    TbContentCategoryService tbContentCategoryService;
    @Autowired
    TbContentService tbContentService;

    @GetMapping("list")
    public ResultCommon list(){
        return  ResultCommon.success(ResultCode.SUCCESS,tbContentCategoryService.list(null));
    }

    @RequestMapping("page")
    public ResultCommon page(@RequestBody PageQuery pageQuery){
        //log.info("服务端--查询的参数是:" + pageQuery);
        QueryWrapper<TbContentCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",pageQuery.getQueryDatas().get(0));
        queryWrapper.orderByDesc("id");
        IPage<TbContentCategory> page=tbContentCategoryService.page(new Page<>(pageQuery.getPageIndex(),pageQuery.getPageSize()),queryWrapper);
        long total = page.getTotal();
        long pages = page.getPages();
        List<TbContentCategory> records = page.getRecords();
        PageUtils<TbContentCategory> pageUtils = new PageUtils<>(pageQuery.getPageIndex(),pageQuery.getPageSize(),total,pages,records);
        return ResultCommon.success(ResultCode.SUCCESS,pageUtils);
    }

    @PostMapping("add")
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

    @PutMapping("update")
    public ResultCommon update(@RequestBody TbContentCategory tbContentCategory){
        boolean flag = tbContentCategoryService.updateById(tbContentCategory);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.success(ResultCode.FAIL);
        }
    }

    @DeleteMapping("deleteOne/{id}")
    public ResultCommon deleteOne(@PathVariable("id") Long id){
        List<TbContent> tbContents = tbContentService.list(new QueryWrapper<TbContent>().eq("category_id",id));
        if(tbContents.size()>0){
            return ResultCommon.fail(ResultCode.NO_DELETE);
        }else{
            boolean flag = tbContentCategoryService.removeById(id);
            if(flag){
                return ResultCommon.success(ResultCode.SUCCESS);
            }else{
                return ResultCommon.fail(ResultCode.FAIL);
            }
        }
    }

    @DeleteMapping("deleteSelectList/{ids}")
    public ResultCommon deleteSelectList(@PathVariable("ids") String ids){
        String[] idArrays = ids.split(",");
        List<String> idList = Arrays.asList(idArrays);
        List<Long> tbContentsID = new ArrayList<>();
        TbContent tbContent;
        for (String s : idList) {
            tbContent = tbContentService.getOne(new QueryWrapper<TbContent>().eq("category_id", s));
            if (tbContent != null) {
                tbContentsID.add(tbContent.getCategoryId());
            }
        }
        if(tbContentsID.size()>0){
            return ResultCommon.success(ResultCode.NO_DELETE,"id为"+tbContentsID+"的数据在使用中，勿删除!");
        }else{
            tbContentCategoryService.removeByIds(idList);
            return ResultCommon.success(ResultCode.SUCCESS,tbContentCategoryService.removeByIds(idList));
        }
    }
}
