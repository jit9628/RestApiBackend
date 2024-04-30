package com.ecw.backendapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecw.backendapi.apiresponse.ApiRespinse;
import com.ecw.backendapi.entity.Category;
import com.ecw.backendapi.service.CategoryService;

@RestController
@RequestMapping("/api/category/")

public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ApiRespinse apiRespinse;

	@PostMapping("saved")
	private ResponseEntity<?> saveCategory(@RequestBody Category category) {

		boolean saveCategory = this.categoryService.saveCategory(category);

		if(saveCategory) {
			return apiRespinse.response("Saved Category", "Success", HttpStatus.OK);
			
		}
		return apiRespinse.response("Category Is Existed..", "Failled", HttpStatus.CONFLICT);

	}
	
	@GetMapping("list")
	@CrossOrigin(origins = "*")
	public  ResponseEntity<?> allListOfData(){
		List<Category> listOfCategory = this.categoryService.listOfCategory();
		return apiRespinse.response(listOfCategory, "Success", HttpStatus.OK);
	}

}
