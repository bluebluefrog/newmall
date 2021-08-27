package com.wjb.newmall.service.impl;

import com.wjb.newmall.constant.Constant;
import com.wjb.newmall.dao.CategoryMapper;
import com.wjb.newmall.pojo.Category;
import com.wjb.newmall.service.CategoryService;
import com.wjb.newmall.vo.CategoryVo;
import com.wjb.newmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
//        List<CategoryVo> categoryVoList = new ArrayList<>();
        List<Category> categories = categoryMapper.selectAll();
        //查出parent_id=0
//        for (Category category:categories) {
//            if (category.getParentId().equals(Constant.ROOT_PARENT_ID)) {
//                CategoryVo categoryVo = new CategoryVo();
//                BeanUtils.copyProperties(category, categoryVo);
//            }
//        }
        //lambda+stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(Constant.ROOT_PARENT_ID))//filter()中是需要的数据
                .map(e -> categoryToCategoryVo(e))//这次遍历来转换成vo
                /*stream只能被“消费”一次，
                一旦遍历过就会失效，就像容器的迭代器那样
                想要再次遍历必须重新生成。
                stream().map()方法?*/
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())//按从小到大的顺序排序
                .collect(Collectors.toList());//最后转换成集合

        //查询子目录
        findSubCategory(categoryVoList, categories);
        
        return ResponseVo.success(categoryVoList);
    }

    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categoryList) {
        for (CategoryVo categoryVo : categoryVoList) {
           List<CategoryVo> subCategoryVoList = new ArrayList();

            for (Category category : categoryList) {
                //如果查到内容，设置subCategory，继续往下查
                if (categoryVo.getId().equals(category.getParentId())) {
                    CategoryVo subCategoryVo = categoryToCategoryVo(category);
                    subCategoryVoList.add(subCategoryVo);
                    findSubCategory(subCategoryVoList,categoryList);
                }
            }
            subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());//按从小到大的顺序排序
            categoryVo.setSubCategories(subCategoryVoList);
        }
    }

    private CategoryVo categoryToCategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
