package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;
import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.CartItemService;
import kr.ac.hansung.cse.service.CartService;
import kr.ac.hansung.cse.service.ProductService;
import kr.ac.hansung.cse.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/cart")
public class CartRestContoller {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService CartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	// cart�� ����ִ� ��ǰ ��ȸ
	@RequestMapping(value="/{cartId}",method=RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value="cartId")int cartId){
		Cart cart = cartService.getCartById(cartId);
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	// cartId�� ���� cart ��ǰ ��� ����
	@RequestMapping(value="/{cartId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value="cartId")int cartId){
		Cart cart = cartService.getCartById(cartId);
		CartItemService.removeAllCartItems(cart);
		
		System.out.println("delete!");
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// cart�� productId�� �ش��ϴ� ��ǰ �߰�
	@RequestMapping(value="/add/{productId}",method=RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value="productId")int productId){
		Product product = productService.getProductById(productId);
		
		// ���� ����� ���� ���� ��
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		// ����ڿ� ������� cart ���� ��
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();

		List<CartItem> cartItems = cart.getCartItems();
		
		// �̹� cart�� ����� ��ǰ���� Ȯ�� (������ �ִ� ��ǰ�̸� ������ ������Ű�� ��)
		for(int i=0;i<cartItems.size();i++) {
			if(product.getId()==cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				// ������ ���� 1 ����
				cartItem.setQuantity(cartItem.getQuantity()+1);
				cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
				
				CartItemService.addCartItem(cartItem);
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		// ���� cart�� ���� ���ο� �������̶��
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		
		// ����� �̱� ������ cartItem <-> cart
		cart.getCartItems().add(cartItem);
		
		CartItemService.addCartItem(cartItem);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// cart���� productId�� �ش�Ǵ� ������ ���� (�ϳ�)
	@RequestMapping(value="/cartitem/{productId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value="productId")int productId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = CartItemService.getCartItemByProductId(cart.getId(), productId);
		CartItemService.removeCartItem(cartItem);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204��
	}
}
