package com.ecw.backendapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecw.backendapi.apiresponse.ApiRespinse;
import com.ecw.backendapi.entity.Product;
import com.ecw.backendapi.service.ProductService;
@RestController
@RequestMapping("/api/product/")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ApiRespinse apiRespinse;

	// save
	@PostMapping("saved")
	public ResponseEntity<?> saceProduct(@RequestBody Product product, @RequestParam("size") String size) {
		product.setSize(size);
		boolean saveProduct = this.productService.saveProduct(product);
		return (saveProduct) ? this.apiRespinse.response("Product Is Saved ", "Success", HttpStatus.OK)
				: this.apiRespinse.response("Product Is Exists", "Failled", HttpStatus.CONFLICT);

	}

//	read all
	@GetMapping("list")
	public ResponseEntity<?> productList() {
		List<Product> allProduct = this.productService.findAllProduct();
		return (allProduct.isEmpty())
				? apiRespinse.response("There Is No Product Available ", "Success", HttpStatus.NO_CONTENT)
				: apiRespinse.response(allProduct, "Success", HttpStatus.OK);
	}

	// read product is active
	@GetMapping("list/active")
	public ResponseEntity<?> findListProductIsActive() {
		List<Product> allProduct = this.productService.findProductIsActive(true);
		return (allProduct.isEmpty())
				? apiRespinse.response("There Is No Product Available ", "Success", HttpStatus.NO_CONTENT)
				: apiRespinse.response(allProduct, "Success", HttpStatus.OK);
	}
	
	// read product is active
		@GetMapping("cat/{id}")
		public ResponseEntity<?> findListProductByCategoryId(@PathVariable("id") int id) {
			List<Product> allProduct = this.productService.findProductByCategoryId(id);
			return (allProduct.isEmpty())
					? apiRespinse.response("There Is No Product Available ", "Success", HttpStatus.OK)
					: apiRespinse.response(allProduct, "Success", HttpStatus.OK);
		}
	
		@GetMapping("serial/{serialnumber}")
		public ResponseEntity<?> findListProductBySerialNumber(@PathVariable("serialnumber") String serialnumber) {
			List<Product> allProduct = this.productService.findProductBySerialNumber(serialnumber);
			return (allProduct.isEmpty())
					? apiRespinse.response("There Is No Product Available ", "Success", HttpStatus.OK)
					: apiRespinse.response(allProduct, "Success", HttpStatus.OK);
		}
	@GetMapping("remove/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable("id") int id) {
		boolean removeProduct = this.productService.removeProduct(id);
		return (removeProduct) ? apiRespinse.response("Product " + id + " Is Removed ", "Success", HttpStatus.OK)
				: apiRespinse.response("There Is No Content Available", "Failled", HttpStatus.NO_CONTENT);
	}

}
