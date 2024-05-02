package com.ecw.backendapi.service;

import java.util.List;

import com.ecw.backendapi.entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product);
	public List<Product> findAllProduct();
	public List<Product> findProductIsActive(boolean IsActive);
	public boolean removeProduct(int id);
	public List<Product> findProductByCategoryId(int id);
	public List<Product> findProductBySerialNumber(String serialnumber);
	
	
}
