package com.freshmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 内容分类
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbContentCategory {
    /**
    * 分类主键（1）
    */
    private Long id;

    /**
    * 分类名称（首页轮播）
    */
    private String name;
}