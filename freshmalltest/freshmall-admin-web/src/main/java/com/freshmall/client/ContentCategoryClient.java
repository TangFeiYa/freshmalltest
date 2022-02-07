package com.freshmall.client;

import com.freshmall.pojo.TbContentCategory;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ContentService")
public interface ContentCategoryClient {
    @RequestMapping("/content_category/page")
    public ResultCommon<PageUtils<TbContentCategory>> page(@RequestBody PageQuery pageQuery);

    @RequestMapping("/content_category/add")
    public ResultCommon add(@RequestBody TbContentCategory tbContentCategory);
}
