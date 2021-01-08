package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
//service단은 dao이용 (2주차 정리 참고)
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	public List<Product> getProducts(){
		return productDao.getProducts();
	}

	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	// ID값으로 product 조회
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}

	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}
}
