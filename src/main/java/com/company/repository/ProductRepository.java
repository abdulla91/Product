package com.company.repository;

import java.util.List;

import com.company.model.Product;

public interface ProductRepository {

	boolean createProductTable();
	
	void insertProduct(Product product);
	
	void insertProductBatch(List<Product> products);
	
	Product findProductById(int id);
	
	List<Product> findProducts();
	
	boolean updateProduct(Product product);
	
	boolean updateProductByPreparedSetter(Product product);
	
	boolean deleteProductById(int id);
	
	Product findProductByBeanProperty(int id);
	
	List<Product> findProductByListMap();
}
