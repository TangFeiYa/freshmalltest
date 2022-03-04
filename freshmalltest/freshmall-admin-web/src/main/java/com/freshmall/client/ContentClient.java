package com.freshmall.client;

import com.freshmall.pojo.TbContent;
import com.freshmall.utils.PageQuery;
import com.freshmall.utils.PageUtils;
import com.freshmall.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("ContentService")
public interface ContentClient {
    @RequestMapping("/content/page")
    ResultCommon<PageUtils<TbContent>> page(@RequestBody PageQuery pageQuery);

    @GetMapping("/content/findOne/{id}")
    ResultCommon<Object> findOne(@PathVariable("id") Long id);

    @DeleteMapping("/content/delete/{id}")
    ResultCommon<Object> delete(@PathVariable("id") Long id);

    @PutMapping("/content/update")
    ResultCommon<Object> update(@RequestBody TbContent tbContent);

    @PostMapping("/content/add")
    ResultCommon<Object> add(@RequestBody TbContent tbContent);
}
