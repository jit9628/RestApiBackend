package com.ecw.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecw.backendapi.entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	boolean existsByProductid(int productid);

	boolean existsByProductname(String productname);

	List<Product> findByIsactive(boolean isactive);

	@Query(value = "select p.productid,p.productname,p.size,p.serialnumber,p.stocks,p.isactive,p.categoryid,c.categoryname from tbl_product as p\r\n"
			+ "left join tbl_category as c on\r\n" + "p.categoryid=c.categoryid\r\n"
			+ " where p.isactive=true and c.categoryid=:categoryid ORDER BY p.productid ASC", nativeQuery = true)
	
//	
//	@Query(value="select p.productid,p.productname,p.serialnumber,p.stocks,p.isactive,c.categoryname from public.tbl_product as p\r\n"
//			+ "left join public.tbl_category as c on\r\n"
//			+ "p.categoryid=c.categoryid\r\n"
//			+ " where p.isactive=true and c.categoryid=:categoryid ORDER BY p.productid ASC",nativeQuery = true)
	
	
	@Modifying
	public List<Product> findByCategoryid(int categoryid);
	
			@Query(value = "select * from tbl_product where serialnumber=:serialnumber and isactive=true ORDER BY productid ASC", nativeQuery = true)
			@Modifying
	public List<Product> findBySerialnumber(String serialnumber);

}
