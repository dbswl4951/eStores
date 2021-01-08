package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.Queryable;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

//Dao에 자동적으로 빈을 주입(DI)시키는 어노테이션!
//Service는 @Service, controller는 @Controller 사용
@Repository
@Transactional // 클래스 내의 모든 메소드에 적용 됨
public class ProductDao {
	@Autowired
	private SessionFactory sessionFactory; // DI 받음
	
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product"); // Product는 DB테이블명 X, java 클래스명임
		List<Product> productList = query.list(); // 모든 Product 반환
		
		return productList;
	}

	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush(); // 해도그만 안해도 그만. Transcational이 알아서 commit해주기 때문
	}

	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
	}

	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);
		
		return product;
	}

	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
	}
}
