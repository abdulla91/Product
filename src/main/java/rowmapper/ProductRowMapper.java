package rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getInt("product_id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getDouble("price"));
		product.setAviable(rs.getInt("aviable"));
		product.setAddDate(rs.getDate("add_date"));
		return product;
	}

}
