package com.group16.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group16.domain.po.Category;
import com.group16.mapper.CategoryMapper;
import com.group16.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
