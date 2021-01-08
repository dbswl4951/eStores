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
	
	// cart에 담겨있는 상품 조회
	@RequestMapping(value="/{cartId}",method=RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value="cartId")int cartId){
		Cart cart = cartService.getCartById(cartId);
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	// cartId를 통해 cart 상품 모두 삭제
	@RequestMapping(value="/{cartId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value="cartId")int cartId){
		Cart cart = cartService.getCartById(cartId);
		CartItemService.removeAllCartItems(cart);
		
		System.out.println("delete!");
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// cart에 productId에 해당하는 상품 추가
	@RequestMapping(value="/add/{productId}",method=RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value="productId")int productId){
		Product product = productService.getProductById(productId);
		
		// 현재 사용자 정보 가져 옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		// 사용자와 사용자의 cart 가져 옴
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();

		List<CartItem> cartItems = cart.getCartItems();
		
		// 이미 cart에 담겨진 상품인지 확인 (기존에 있는 상품이면 갯수만 증가시키면 됨)
		for(int i=0;i<cartItems.size();i++) {
			if(product.getId()==cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				// 아이템 갯수 1 증가
				cartItem.setQuantity(cartItem.getQuantity()+1);
				cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
				
				CartItemService.addCartItem(cartItem);
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		// 만약 cart에 없는 새로운 아이템이라면
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		
		// 양방향 이기 때문에 cartItem <-> cart
		cart.getCartItems().add(cartItem);
		
		CartItemService.addCartItem(cartItem);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// cart에서 productId에 해당되는 아이템 삭제 (하나)
	@RequestMapping(value="/cartitem/{productId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value="productId")int productId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = CartItemService.getCartItemByProductId(cart.getId(), productId);
		CartItemService.removeCartItem(cartItem);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204번
	}
}
