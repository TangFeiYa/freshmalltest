package com.freshmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
    * 分类名称（首页轮播）
    */
    private String name;
}