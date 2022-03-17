package com.freshmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品类目
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_item_cat")
public class TbItemCat {
    /**
     * 类目主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类目名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 类目图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 上级类目
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 模板类型
     */
    @TableField(value = "type_id")
    private Long typeId;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_IMAGE = "image";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_TYPE_ID = "type_id";
}