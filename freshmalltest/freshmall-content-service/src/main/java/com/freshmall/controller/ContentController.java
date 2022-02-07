package com.freshmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshmall.pojo.TbContent;
import com.freshmall.service.TbContentService;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCode;
import com.freshmall.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("content")
@Slf4j
public class ContentController {
    @Autowired
    TbContentService tbContentService;
    @GetMapping("list")
    public ResultCommon list(){
//        log.info("我是广告服务，端口是:" + port);
        return ResultCommon.success(ResultCode.ADD_FAV_SUCCESS,tbContentService.list(null));
    }

    @GetMapping("listByCategoryId/{category_id}")
    public ResultCommon listByCategoryId(@PathVariable("category_id") Integer category_id){
//        log.info("我是广告服务，端口是:" + port);
        return ResultCommon.success(ResultCode.SUCCESS,tbContentService.list(new QueryWrapper<TbContent>().eq("category_id",category_id)));
    }

    @RequestMapping("page")
    public ResultCommon<PageUtils<TbContent>> page(@RequestBody PageQuery pageQuery){
//        log.info("服务端--查询的参数是:" + pageQuery);
        QueryWrapper<TbContent> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",pageQuery.getQueryDatas().get(0));
        queryWrapper.orderByDesc("id");
        IPage<TbContent> page=tbContentService.page(new Page<TbContent>(pageQuery.getPageIndex(),pageQuery.getPageSize()),queryWrapper);
        long total = page.getTotal();
        long pages = page.getPages();
        List<TbContent> records = page.getRecords();
        PageUtils<TbContent> pageUtils = new PageUtils<>(pageQuery.getPageIndex(),pageQuery.getPageSize(),total,pages,records);
        return ResultCommon.success(ResultCode.SUCCESS,pageUtils);
    }
}
