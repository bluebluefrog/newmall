package com.wjb.newmall.service;

import com.wjb.newmall.vo.CategoryVo;
import com.wjb.newmall.vo.ResponseVo;

import java.util.List;

public interface CategoryService {

    ResponseVo<List<CategoryVo>> selectAll();
}
