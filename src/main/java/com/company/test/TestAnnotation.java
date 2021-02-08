package com.company.test;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.company.config.AppConfig;
import com.company.model.Product;
import com.company.repository.ProductRepository;
import com.company.repository.impl.ProductRepositoryImpl;

public class TestAnnotation {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ProductRepository repository = context.getBean(ProductRepositoryImpl.class);
//		Product product = repository.findProductByBeanProperty(4);
//		System.out.println(product);
		List<Product> list = repository.findProductByListMap();
		list.forEach(System.out::println);
	}

}
