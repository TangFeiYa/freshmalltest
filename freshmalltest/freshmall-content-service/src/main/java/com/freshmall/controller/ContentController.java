package com.freshmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshmall.pojo.TbContent;
import com.freshmall.service.TbContentCategoryService;
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

    @Value("${server.port}")
    String port;

    @Autowired
    TbContentCategoryService tbContentCategoryService;
    @GetMapping("list")
    public ResultCommon list(){
//        log.info("我是广告服务，端口是:" + port);
        return ResultCommon.success(ResultCode.ADD_FAV_SUCCESS,tbContentService.list(null));
    }

    @GetMapping("listByCategoryId/{category_id}")
    public ResultCommon listByCategoryId(@PathVariable("category_id") Integer category_id){
//        log.info("我是广告服务，端口是:" + port);
        System.out.println("我是广告服务，端口是:" + port);
        return ResultCommon.success(ResultCode.SUCCESS,tbContentService.list(new QueryWrapper<TbContent>().eq("category_id",category_id)));
    }

    /**
     * 分页请求
     * @param pageQuery 分页信息
     * @return 返回分页
     */
    @RequestMapping("page")
    public ResultCommon page(@RequestBody PageQuery pageQuery){
//        log.info("服务端--查询的参数是:" + pageQuery);
        QueryWrapper<TbContent> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("title",pageQuery.getQueryDatas().get(0));
        queryWrapper.orderByDesc("id");
        IPage<TbContent> page=tbContentService.page(new Page<>(pageQuery.getPageIndex(),pageQuery.getPageSize()),queryWrapper);
        long total = page.getTotal();
        long pages = page.getPages();
        List<TbContent> records = page.getRecords();
        for (TbContent record : records) {
            Long categoryId = record.getCategoryId();
            String categoryName = tbContentCategoryService.getById(categoryId).getName();
            record.setCategoryName(categoryName);
        }
        PageUtils<TbContent> pageUtils = new PageUtils<>(pageQuery.getPageIndex(),pageQuery.getPageSize(),total,pages,records);
        return ResultCommon.success(ResultCode.SUCCESS,pageUtils);
    }

    /**
     * 增加单个广告
     * @param tbContent 广告类
     * @return 返回成功或失败
     */
    @PostMapping("add")
    public ResultCommon add(@RequestBody TbContent tbContent){
        boolean flag = tbContentService.save(tbContent);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    /**
     * 通过id查询广告
     * @param id 广告id
     * @return 返回广告信息
     */
    @GetMapping("/findOne/{id}")
    public ResultCommon findOne(@PathVariable("id") Long id){
        return ResultCommon.success(ResultCode.SUCCESS,tbContentService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResultCommon delete(@PathVariable("id") Long id){
        TbContent tbContent = tbContentService.getById(id);
        if(tbContent.getStatus().equals("1")){
            return ResultCommon.fail(ResultCode.FAIL);
        }else{
            boolean flag = tbContentService.removeById(id);
            if(flag){
                return ResultCommon.success(ResultCode.SUCCESS);
            }else{
                return ResultCommon.fail(ResultCode.FAIL);
            }
        }
    }

    @PutMapping("update")
    public ResultCommon update(@RequestBody TbContent tbContent){
        boolean flag = tbContentService.updateById(tbContent);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }
}
