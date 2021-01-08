package kr.ac.hansung.cse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private UserService userService;
	
	@RequestMapping // /cart로 요청 오면 이 메소드 실행 됨
	public String getCart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		int cartId = user.getCart().getId(); // 1명의 user당 1개의 cart 가지고 있음.
		
		model.addAttribute("cartId", cartId);
		
		return "cart";
	}
}
