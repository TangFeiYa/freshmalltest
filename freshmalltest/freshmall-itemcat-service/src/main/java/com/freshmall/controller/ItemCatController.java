package com.freshmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshmall.pojo.TbItemCat;
import com.freshmall.service.TbItemCatService;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCode;
import com.freshmall.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item-cat")
public class ItemCatController {
    @Autowired
    TbItemCatService tbItemCatService;

    @RequestMapping("/page")
    ResultCommon page(@RequestBody PageQuery pageQuery){
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
        //分类名字
        queryWrapper.like("name", pageQuery.getQueryDatas().get(0));
        //级数
        queryWrapper.eq("parent_id", pageQuery.getQueryDatas().get(1));
        queryWrapper.orderByDesc("id");
        IPage<TbItemCat> page = tbItemCatService.page(new Page<TbItemCat>(pageQuery.getPageIndex(), pageQuery.getPageSize()), queryWrapper);
        long total = page.getTotal();
        long pages = page.getPages();
        List<TbItemCat> records = page.getRecords();
        PageUtils<TbItemCat> pageUtils = new PageUtils<>(pageQuery.getPageIndex(), pageQuery.getPageSize(), total, pages, records);
        return ResultCommon.success(ResultCode.SUCCESS,pageUtils);
    }

    @GetMapping("/list")
    List<TbItemCat> list(){
        return tbItemCatService.list(null);
    }

    @PostMapping("/add")
    ResultCommon add(@RequestBody TbItemCat tbItemCat){
        boolean flag = tbItemCatService.save(tbItemCat);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    @DeleteMapping("/delete")
    ResultCommon delete(@RequestBody TbItemCat tbItemCat){
        //查询是否有子节点
        List<TbItemCat> tbItemCats = tbItemCatService.list(new QueryWrapper<TbItemCat>().eq("parent_id",tbItemCat.getId()));
        if (tbItemCats.size() > 0){
            //如果有子节点则不能删除
            return ResultCommon.fail(ResultCode.NO_SON_DELETE);
        }else{
            boolean flag = tbItemCatService.removeById(tbItemCat.getId());
            if(flag){
                //删除成功
                return ResultCommon.success(ResultCode.SUCCESS);
            }else{
                //删除失败
                return ResultCommon.fail(ResultCode.FAIL);
            }
        }
    }

    @PutMapping("update")
    ResultCommon update(@RequestBody TbItemCat tbItemCat){
        boolean flag = tbItemCatService.updateById(tbItemCat);
        if(flag){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    @GetMapping("findByid/{id}")
    ResultCommon findByid(@PathVariable("id") Long id){
        return ResultCommon.success(ResultCode.SUCCESS,tbItemCatService.getById(id));
    }
}
