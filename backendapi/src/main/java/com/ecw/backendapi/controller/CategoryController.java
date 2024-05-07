package com.ecw.backendapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecw.backendapi.apiresponse.ApiRespinse;
import com.ecw.backendapi.entity.Category;
import com.ecw.backendapi.service.CategoryService;

@RestController
@RequestMapping("/api/category/")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ApiRespinse apiRespinse;

	@CrossOrigin(origins = "http://localhost:9999")
	@PostMapping("saved")
	private ResponseEntity<?> saveCategory(@RequestBody Category category) {
		boolean saveCategory = this.categoryService.saveCategory(category);
		if (saveCategory) {
			return apiRespinse.response("Saved Category", "Success", HttpStatus.OK);
		}
		return apiRespinse.response("Category Is Existed..", "Failled", HttpStatus.CONFLICT);
	}

	@GetMapping("list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> allListOfData() {
		List<Category> listOfCategory = this.categoryService.listOfCategory();
		return apiRespinse.response(listOfCategory, "Success", HttpStatus.OK);
	}

	@GetMapping("list/active")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> findCategoryIsActive() {
		List<Category> listOfCategory = this.categoryService.findCategoryIsActive(true);
		return apiRespinse.response(listOfCategory, "Success", HttpStatus.OK);
	}

	@GetMapping("remove/{id}")
	public ResponseEntity<?> removeCategory(@PathVariable("id") int id) {
		boolean removeCategory = this.categoryService.removeCategory(id);
		return (removeCategory) ? apiRespinse.response("Removed Your Category ..", "Success", HttpStatus.OK)
				: apiRespinse.response("Category Not Exists ...", "Failled", HttpStatus.NO_CONTENT);
	}

}
