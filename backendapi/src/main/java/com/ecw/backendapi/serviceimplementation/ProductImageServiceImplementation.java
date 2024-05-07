package com.ecw.backendapi.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecw.backendapi.entity.ProductImage;
import com.ecw.backendapi.repository.ProductImageRepository;
import com.ecw.backendapi.service.ProductImageService;

@Service
public class ProductImageServiceImplementation implements ProductImageService {
	@Autowired
	private ProductImageRepository imageRepository;

	@Override
	public boolean save(ProductImage productimage) {
	this.imageRepository.save(productimage);
		return true;
	}


}
