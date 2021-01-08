package kr.ac.hansung.cse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.CartItemDao;
import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Service
public class CartItemService {
	@Autowired
	private CartItemDao cartItemDao;
	
	// 새로운 아이템을 cart에 넣기
	public void addCartItem(CartItem cartItem) {
		cartItemDao.addCartItem(cartItem);
	}
	
	public void removeCartItem(CartItem cartItem) {
		cartItemDao.removeCartItem(cartItem);
	}
	
	public void removeAllCartItems(Cart cart) {
		cartItemDao.removeAllCartItems(cart);
	}
	
	public CartItem getCartItemByProductId(int cartId,int productId) {
		return cartItemDao.getCartItemByProductId(cartId,productId);
	}
}
