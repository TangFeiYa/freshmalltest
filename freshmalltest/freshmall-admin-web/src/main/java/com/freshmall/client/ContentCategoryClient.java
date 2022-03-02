package com.freshmall.client;

import com.freshmall.pojo.TbContentCategory;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("ContentService")
public interface ContentCategoryClient {

    @GetMapping("/content_category/list")
    ResultCommon<Object> list();

    @RequestMapping("/content_category/page")
    ResultCommon<PageUtils<TbContentCategory>> page(@RequestBody PageQuery pageQuery);

    @RequestMapping("/content_category/add")
    ResultCommon<Object> add(@RequestBody TbContentCategory tbContentCategory);

    @GetMapping("/content_category/findOne/{id}")
    ResultCommon<Object> findOne(@PathVariable("id") Long id);

    @RequestMapping("/content_category/update")
    ResultCommon<Object> update(@RequestBody TbContentCategory tbContentCategory);

    @RequestMapping("/content_category/deleteOne/{id}")
    ResultCommon<Object> deleteOne(@PathVariable("id") Long id);

    @RequestMapping("/content_category/deleteSelectList/{ids}")
    ResultCommon<Object> deleteSelectList(@PathVariable("ids") String ids);
}
