package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.ShippingAddress;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String registerUser(Model model) {
		User user = new User(); // new 생성 => 빈으로 등록 되지는 X. spring ioc 컨테이너에서 관리 되지 X
		ShippingAddress shippingAddress = new ShippingAddress();
		
		user.setShippingAddress(shippingAddress);
		
		model.addAttribute("user", user);
		
		return "registerUser";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	// form에서 넘어온 user 객체 바인딩 됨 -> 검증 (@Valid) -> 오류나면 BindingResult result에 그 결과 담김
	public String registerUserPost(@Valid User user,BindingResult result, Model model) {
		if(result.hasErrors()) {
			// 위에서 User user,BindingResult result가 있기 때문에
			// registerUser 페이지로 돌아가도 기본적으로 입력이 되어 있음 (여기서 추가적으로 error 메세지만 더 나오는 것)
			return "registerUser";
		}
		
		List<User> userList = userService.getAllUsers();
		
		// 중복 여부 체크
		for(int i=0; i<userList.size(); i++) {
			if(user.getUsername().equals(userList.get(i).getUsername())) {
				model.addAttribute("usernameMsg", "Username already exist");
				
				return "registerUser";
			}
		}
		
		user.setEnabled(true);
		
		if(user.getUsername().equals("admin")) {
			user.setAuthority("ROLE_ADMIN");
		}else {
			user.setAuthority("ROLE_USER");
		}
		
		// User의 Cart cart가 optional=false이기 때문에 null값 들어 갈 수 없음
		// 아래의 과정 안해주면 오류 생기기 때문에 임의로 Cart 생성 후, set해주자!
		Cart cart = new Cart();
		user.setCart(cart);
		
		userService.addUser(user);
		
		return "registerUserSuccess";
	}
}
