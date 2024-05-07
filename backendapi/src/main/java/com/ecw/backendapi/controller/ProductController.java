package com.ecw.backendapi.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.activation.FileTypeMap;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecw.backendapi.apiresponse.ApiRespinse;
import com.ecw.backendapi.entity.Product;
import com.ecw.backendapi.entity.ProductImage;
import com.ecw.backendapi.service.ProductImageService;
import com.ecw.backendapi.service.ProductService;
import com.ecw.backendapi.utils.FileUploadHelper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/product/")

public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ApiRespinse apiRespinse;
	@Autowired
	private ProductImageService imageService;
	@Autowired
	private FileUploadHelper fileUploadHelper;
	// save

	@CrossOrigin(origins = "http://localhost:9999")
	@PostMapping("saved")
	public ResponseEntity<?> saceProduct(@RequestBody Product product) {
		boolean saveProduct = this.productService.saveProduct(product);
		return (saveProduct) ? this.apiRespinse.response("Product Is Saved ", "Success", HttpStatus.OK)
				: this.apiRespinse.response("Product Is Exists", "Failled", HttpStatus.CONFLICT);

	}

	@GetMapping("getimage")
	public byte[] saveProductImage() throws IOException {
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);
		Path path = Paths.get(currentDir + File.separator + "uploadfiles" + File.separator + "flower.jpg");
		File img = new File(path.toString());
//		return ResponseEntity.ok()
//				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
//				.body(Files.readAllBytes(img.toPath()));
		
		return Files.readAllBytes(img.toPath());
		
	
 
//		if (file.isEmpty()) {
//			return apiRespinse.response("Please Select One File", "Failled", HttpStatus.NO_CONTENT);
//
//		} else {
//			String doFileUpload = fileUploadHelper.doFileUpload(file, httpServletRequest);
//			System.out.println("Your File Path Is :" + doFileUpload);
//			ProductImage productimage = new ProductImage();
//			productimage.setImagename(file.getOriginalFilename());
//			productimage.setImagesize(file.getSize());
//			productimage.setImagetype(file.getContentType());
//			productimage.setImageurl(doFileUpload);
//
//			boolean save = this.imageService.save(productimage);
//
//			return apiRespinse.response("Saved Your Product Image. ", "Success", HttpStatus.OK);
//		}

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
		return (allProduct.isEmpty()) ? apiRespinse.response("There Is No Product Available ", "Success", HttpStatus.OK)
				: apiRespinse.response(allProduct, "Success", HttpStatus.OK);
	}

	@GetMapping("serial/{serialnumber}")
	public ResponseEntity<?> findListProductBySerialNumber(@PathVariable("serialnumber") String serialnumber) {
		List<Product> allProduct = this.productService.findProductBySerialNumber(serialnumber);
		return (allProduct.isEmpty()) ? apiRespinse.response("There Is No Product Available ", "Success", HttpStatus.OK)
				: apiRespinse.response(allProduct, "Success", HttpStatus.OK);
	}

	@GetMapping("remove/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable("id") int id) {
		boolean removeProduct = this.productService.removeProduct(id);
		return (removeProduct) ? apiRespinse.response("Product " + id + " Is Removed ", "Success", HttpStatus.OK)
				: apiRespinse.response("There Is No Content Available", "Failled", HttpStatus.NO_CONTENT);
	}

}
