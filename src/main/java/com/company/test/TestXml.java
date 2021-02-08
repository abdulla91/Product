package com.company.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.model.Product;
import com.company.repository.ProductRepository;
import com.company.repository.impl.ProductRepositoryImpl;

public class TestXml {

	public static void main(String[] args) {
		ConfigurableApplicationContext context  = new ClassPathXmlApplicationContext("springBeans.xml");
		ProductRepository repository = context.getBean("productRepositoryImpl", ProductRepositoryImpl.class);
//		repository.createProductTable();
		
//		Product product = new Product(1, "Note -8", 2200, 3, new Date());
//		repository.insertProduct(product);
		
//		Product product1 = new Product(1, "Note-7", 650, 2, new Date());
//		Product product2 = new Product(2, "Note-9", 700, 5, new Date());
//		List<Product> list = Arrays.asList(product1, product2);
//		repository.insertProductBatch(list);
		
//		Product p = repository.findProductById(2);
//		System.out.println(p);
		
//		List<Product> lists = repository.findProducts();
//		lists.forEach(System.out::println);
		
//		Product product = repository.findProductById(3);
//		product.setName("Note - 6");
//		repository.updateProduct(product);
//		product.setName("Note - 7");
//		repository.updateProductByPreparedSetter(product);
		
		repository.deleteProductById(2);
		
		context.close();
	}

}
