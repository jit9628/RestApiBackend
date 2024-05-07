package com.ecw.backendapi.service;

import java.util.List;

import com.ecw.backendapi.entity.Category;

public interface CategoryService {
public boolean saveCategory(Category category);
public List<Category> listOfCategory();
public boolean removeCategory(int id);
public List<Category> findCategoryIsActive(boolean IsActive);
}
