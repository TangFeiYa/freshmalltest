package com.freshmall.client;

import com.freshmall.pojo.TbContentCategory;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("ContentService")
public interface ContentCategoryClient {
    @RequestMapping("/content_category/page")
    public ResultCommon<PageUtils<TbContentCategory>> page(@RequestBody PageQuery pageQuery);

    @RequestMapping("/content_category/add")
    public ResultCommon add(@RequestBody TbContentCategory tbContentCategory);

    @GetMapping("/content_category/findOne/{id}")
    public ResultCommon findOne(@PathVariable("id") Long id);

    @RequestMapping("/content_category/update")
    public ResultCommon update(@RequestBody TbContentCategory tbContentCategory);

    @RequestMapping("/content_category/deleteOne/{id}")
    public ResultCommon deleteOne(@PathVariable("id") Long id);

    @RequestMapping("/content_category/deleteSelectList/{ids}")
    public ResultCommon deleteSelectList(@PathVariable("ids") String ids);
}
