package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}

	public void removeCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}

	public void removeAllCartItems(Cart cart) {
		// Cart 클래스의 List<CartItem> cartItems를 fetch=FetchType.EAGER로 줬기 때문에
		// Cart읽을 때 자동적으로 CartItem도 같이 읽기 때문에, cart.getCartItems() 실행하면 결과 list로 나옴
		// => 만약 FetchType.LAZY로 줬다면 반환된 cartItems객체에 null값이 들어가 있을 것!
		List<CartItem>cartItems = cart.getCartItems(); // Cart 클래스 List<CartItem> cartItems 객체의 getter
		
		for(CartItem item : cartItems) {
			removeCartItem(item); // 위의 메소드 호출
		}
	}

	@SuppressWarnings("unchecked")
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		// TypedQuery<> : 반환되는 엔티티가 정해져 있을 때 사용하는 객체 타입
		TypedQuery<CartItem> query = session.createQuery("from CartItem where cart.id=? and product.id=?");
		query.setParameter(0, cartId); //0번째 ?에 cartId 넣음
		query.setParameter(1, productId); //두번째 ?에 productId 넣음
		
		return (CartItem)query.getSingleResult(); //하나의 결과만 받음
	}
	
}
