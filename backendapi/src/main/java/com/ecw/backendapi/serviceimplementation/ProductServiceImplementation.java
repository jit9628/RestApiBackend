package com.ecw.backendapi.serviceimplementation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecw.backendapi.entity.Product;
import com.ecw.backendapi.repository.ProductRepository;
import com.ecw.backendapi.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public boolean saveProduct(Product product) {
		if (!this.productRepository.existsByProductname(product.getProductname())) {
			this.productRepository.save(product);
			return true;
		}
		return false;
	}

	@Override
	public List<Product> findAllProduct() {
		List<Product> all = this.productRepository.findAll();
		if (all.isEmpty()) {
			return null;
		}
		return all;
	}

	@Override
	public List<Product> findProductIsActive(boolean IsActive) {
		List<Product> byIsactive = this.productRepository.findByIsactive(IsActive);
		return (byIsactive.isEmpty()) ? null : byIsactive;

	}

	@Override
	public boolean removeProduct(int id) {
		boolean existsById = this.productRepository.existsById(id);
		if (existsById) {
			Optional<Product> byId = this.productRepository.findById(id);
			if (byId.isPresent() && !byId.isEmpty()) {
				Product product = byId.get();
				product.setIsactive(false);
				this.productRepository.save(product);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Product> findProductByCategoryId(int id) {
		List<Product> byCategoryid = this.productRepository.findByCategoryid(id);
		return byCategoryid;
	}

	@Override
	public List<Product> findProductBySerialNumber(String serialnumber) {
		List<Product> bySerialnumber = this.productRepository.findBySerialnumber(serialnumber);
		return bySerialnumber;
	}

}
