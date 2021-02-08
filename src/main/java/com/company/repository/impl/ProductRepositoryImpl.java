package com.company.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.company.model.Product;
import com.company.repository.ProductRepository;

import rowmapper.ProductRowMapper;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean createProductTable() {
		String sql = "CREATE TABLE IF NOT EXISTS product(product_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(35), price DOUBLE, "
				+ "aviable INT, add_date DATETIME)";
		try {
			this.jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	

	@Override
	public void insertProduct(Product product) {
		String sql = "INSERT INTO product(name, price, aviable, add_date) VALUES(?, ?, ?, ?)";
		try {
			Object[] obj = new Object[]{product.getName(), product.getPrice(), product.getAviable(), product.getAddDate()};
			this.jdbcTemplate.update(sql, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertProductBatch(List<Product> products) {
		final String sql = "INSERT INTO product(name, price, aviable, add_date) VALUES(?, ?, ?, ?)";
		try {
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Product product = products.get(i);
					ps.setString(1, product.getName());
					ps.setDouble(2, product.getPrice()); 
					ps.setInt(3, product.getAviable());
					ps.setTimestamp(4, Timestamp.from(product.getAddDate().toInstant()));
				}
				
				@Override
				public int getBatchSize() {
					return products.size();
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());			
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product findProductById(int id) {
		String query = "SELECT * FROM product WHERE product_id = ?";
		Product p = null;
		try {
			p = this.jdbcTemplate.queryForObject(query, new Object[] {id}, new ProductRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		return p;
	}

	@Override
	public List<Product> findProducts() {
		String query = "SELECT * FROM product";
		List<Product> lists = this.jdbcTemplate.query(query, new ProductRowMapper());
		return lists;
	}

	@Override
	public boolean updateProduct(Product product) {
		String query = "UPDATE product SET name = ?, price =?, aviable = ?, add_date = ? WHERE product_id = ?";
		try {
			this.jdbcTemplate.update(query, new Object[] {product.getName(), product.getPrice(), product.getAviable(), product.getAddDate(), 
					product.getProductId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean updateProductByPreparedSetter(Product product) {
		String query = "UPDATE product SET name = ?, price =?, aviable = ? WHERE product_id = ?";
		try {
			this.jdbcTemplate.update(query, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, product.getName());	
					ps.setDouble(2, product.getPrice());
					ps.setInt(3, product.getAviable());
//					ps.setTimestamp(4, Timestamp.from(product.getAddDate().toInstant()));
					ps.setInt(4, product.getProductId());
				}});
		} catch (Exception e) {
			System.out.println("Error message : " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		String query = "DELETE FROM product WHERE product_id = ?";
		try {
			this.jdbcTemplate.update(query, new Object[] {id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Product findProductByBeanProperty(int id) {
		String query = "SELECT * FROM product WHERE product_id = ?";
		Product product = (Product) jdbcTemplate.queryForObject(query, new Object[] {id}, new BeanPropertyRowMapper(Product.class));
		return product;
	}

	@Override
	public List<Product> findProductByListMap() {
		String query = "SELECT * FROM product";
		List<Product> lists = new ArrayList<>();
		try {
			List<Map<String, Object>> list = this.jdbcTemplate.queryForList(query);
			for (Map<String, Object> map : list) {
				int product_id = (int) map.get("product_id");
				String name = (String) map.get("name");
				Double price = (Double) map.get("price");
				int aviable = (int) map.get("aviable");
				Date add_date = (Date) map.get("add_date");
				Product p = new Product(product_id, name, price, aviable, add_date);
				lists.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

}
