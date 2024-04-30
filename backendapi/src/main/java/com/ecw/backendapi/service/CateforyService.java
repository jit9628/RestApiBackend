package com.ecw.backendapi.service;

import java.util.List;

import com.ecw.backendapi.entity.Category;

public interface CateforyService {
public boolean saveCategory(Category category);
public List<Category> listOfCategory();
}
