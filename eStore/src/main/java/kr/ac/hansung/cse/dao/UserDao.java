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

@Repository //Dao�� @Repository => �� ���
@Transactional
public class UserDao {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// ���ο� User ����
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		// ����ڰ� �Է��� password�� �����ͼ� encoding�ؼ�  ����
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session.saveOrUpdate(user);
		session.flush();
	}
	
	// id�� User ��ȸ
	public User getUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		
		return (User)session.get(User.class, userId);
	}
	
	// name���� User ��ȸ
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User where username=?");
		query.setParameter(0, username);
		
		return query.getSingleResult(); //�ϳ��� ����� return
	}
	
	// ��� User ��ȸ
	@SuppressWarnings("unchecked")
	public List<User> getAllUser(){
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User");
		List<User> userList = query.getResultList();
		
		return userList;
	}
}
