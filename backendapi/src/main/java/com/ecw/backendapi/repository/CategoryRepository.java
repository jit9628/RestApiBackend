package com.ecw.backendapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecw.backendapi.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


boolean existsByCategoryname(String categoryname);
}
