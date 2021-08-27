package com.wjb.newmall.controller;

import com.wjb.newmall.service.CategoryService;
import com.wjb.newmall.vo.CategoryVo;
import com.wjb.newmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ResponseBody
    @GetMapping("/categories")
    public ResponseVo getCategory() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
        return ResponseVo.success(listResponseVo);
    }
}
