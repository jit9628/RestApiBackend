package com.ecw.backendapi.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecw.backendapi.entity.Category;
import com.ecw.backendapi.repository.CategoryRepository;
import com.ecw.backendapi.service.CateforyService;
import com.ecw.backendapi.service.CategoryService;
@Service
public class CategoryServiceImplementation implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean saveCategory(Category category) {
		
//		check is existed or not
		if(this.categoryRepository.existsByCategoryname(category.getCategoryname())) {
			return false;
		}else {
			this.categoryRepository.save(category);
			return true;
		}
		
	}

	@Override
	public List<Category> listOfCategory() {
		List<Category> all = this.categoryRepository.findAll();
		return all;
	}

}
