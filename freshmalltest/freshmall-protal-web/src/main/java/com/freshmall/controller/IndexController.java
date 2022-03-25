package com.freshmall.controller;

import com.freshmall.client.ContentClient;
import com.freshmall.client.ItemCatClient;
import com.freshmall.pojo.TbItemCat;
import com.freshmall.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    ContentClient contentClient;
    @Autowired
    ItemCatClient itemCatClient;

    @GetMapping({"index","/"})
    public String index(Model model){
        //顶部轮播图
        ResultCommon<Object> top_loop_contents_resultCommon=contentClient.listByCategoryId(1);
        model.addAttribute("top_loop_contents",top_loop_contents_resultCommon.getData());

        //中部轮播图
        ResultCommon<Object> middle_loop_contents_resultCommon=contentClient.listByCategoryId(2);
        model.addAttribute("middle_loop_contents",middle_loop_contents_resultCommon.getData());

        //获取所有商品类目
        List<TbItemCat> itemCats =  itemCatClient.list();
        //获取所有一级商品类目
        List<TbItemCat> itemCats1 = extractItemCat(1,itemCats);
        //获取所有二级商品类目
        List<TbItemCat> itemCats2 = extractItemCat(2,itemCats1,itemCats);
        model.addAttribute("itemCats1", itemCats1);
        model.addAttribute("itemCats2", itemCats2);

        return "index";
    }

    @SafeVarargs
    private final List<TbItemCat> extractItemCat(int type, List<TbItemCat>... itemCatList){
        List<TbItemCat> emptyList = new ArrayList<>();
        if(type == 1) {
            itemCatList[0].forEach(itemCat0 -> {
                if(itemCat0.getParentId() == 0){
                    emptyList.add(itemCat0);
                }
            });
        } else if(type == 2){
            itemCatList[1].forEach(itemCat1 -> itemCatList[0].forEach(itemCat0 -> {
                if(itemCat1.getParentId().equals(itemCat0.getId())){
                    emptyList.add(itemCat1);
                }
            }));
        }else if(type == 3){
            itemCatList[2].forEach(itemCat2 -> itemCatList[1].forEach(itemCat1 -> {
                if(itemCat2.getParentId().equals(itemCat1.getId())){
                    emptyList.add(itemCat2);
                }
            }));
        }
        return emptyList;
    }
}
