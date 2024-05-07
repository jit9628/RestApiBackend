package com.ecw.backendapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecw.backendapi.entity.ProductImage;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}
