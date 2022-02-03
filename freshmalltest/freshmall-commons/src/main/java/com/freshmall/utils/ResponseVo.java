package com.freshmall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: Tu Xu
 * @CreateDate: 2021/4/9 15:45
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
public class ResponseVo<T> {
    //200正常  404 用户找不到资源  500 程序出错
    private String status;
    //数据
    private T t;

    private String message;


}
