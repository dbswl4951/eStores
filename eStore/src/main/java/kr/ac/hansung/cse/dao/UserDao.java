package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.User;

@Repository //Dao는 @Repository => 빈 등록
@Transactional
public class UserDao {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// 새로운 User 저장
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		// 사용자가 입력한 password를 가져와서 encoding해서  저장
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session.saveOrUpdate(user);
		session.flush();
	}
	
	// id로 User 조회
	public User getUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		
		return (User)session.get(User.class, userId);
	}
	
	// name으로 User 조회
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User where username=?");
		query.setParameter(0, username);
		
		return query.getSingleResult(); //하나의 결과값 return
	}
	
	// 모든 User 조회
	@SuppressWarnings("unchecked")
	public List<User> getAllUser(){
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User");
		List<User> userList = query.getResultList();
		
		return userList;
	}
}
