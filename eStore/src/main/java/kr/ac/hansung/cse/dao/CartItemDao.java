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
		// Cart Ŭ������ List<CartItem> cartItems�� fetch=FetchType.EAGER�� ��� ������
		// Cart���� �� �ڵ������� CartItem�� ���� �б� ������, cart.getCartItems() �����ϸ� ��� list�� ����
		// => ���� FetchType.LAZY�� ��ٸ� ��ȯ�� cartItems��ü�� null���� �� ���� ��!
		List<CartItem>cartItems = cart.getCartItems(); // Cart Ŭ���� List<CartItem> cartItems ��ü�� getter
		
		for(CartItem item : cartItems) {
			removeCartItem(item); // ���� �޼ҵ� ȣ��
		}
	}

	@SuppressWarnings("unchecked")
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		// TypedQuery<> : ��ȯ�Ǵ� ��ƼƼ�� ������ ���� �� ����ϴ� ��ü Ÿ��
		TypedQuery<CartItem> query = session.createQuery("from CartItem where cart.id=? and product.id=?");
		query.setParameter(0, cartId); //0��° ?�� cartId ����
		query.setParameter(1, productId); //�ι�° ?�� productId ����
		
		return (CartItem)query.getSingleResult(); //�ϳ��� ����� ����
	}
	
}
