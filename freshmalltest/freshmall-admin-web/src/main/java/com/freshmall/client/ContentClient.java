package com.freshmall.client;

import com.freshmall.pojo.TbContent;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ContentService")
public interface ContentClient {
    @RequestMapping("/content/page")
    public ResultCommon<PageUtils<TbContent>> page(@RequestBody PageQuery pageQuery);
}
