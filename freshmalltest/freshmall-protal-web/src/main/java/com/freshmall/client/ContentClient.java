package com.freshmall.client;

import com.freshmall.pojo.TbContent;
import com.freshmall.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient("ContentService")
public interface ContentClient {
    @GetMapping("/content/list")
    List<TbContent> list();

    @GetMapping("/content/listByCategoryId/{category_id}")
    ResultCommon<Object> listByCategoryId(@PathVariable("category_id") Integer category_id);
}
