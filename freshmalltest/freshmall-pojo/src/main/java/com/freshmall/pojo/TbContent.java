package com.freshmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 内容
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_content")
public class TbContent {
    /**
     * 内容主键（1）
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 内容标题（你好，游戏玩家，超级笔记本！）
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容地址（http://item.shop.com/1.html）
     */
    @TableField(value = "url")
    private String url;

    /**
     * 内容图片（http://image.shop.com/image/activity.jpg）
     */
    @TableField(value = "image")
    private String image;

    /**
     * 内容排序（0）
     */
    @TableField(value = "`order`")
    private Integer order;

    /**
     * 内容状态（0：禁用、1：启用）
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 类目主键（1）
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 忽略字段
     */
    @TableField(exist = false)
    private String categoryName;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_URL = "url";

    public static final String COL_IMAGE = "image";

    public static final String COL_ORDER = "order";

    public static final String COL_STATUS = "status";

    public static final String COL_CATEGORY_ID = "category_id";
}