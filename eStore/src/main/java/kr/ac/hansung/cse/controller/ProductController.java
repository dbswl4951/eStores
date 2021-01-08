package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

//컨테이너가 이 클래스를 자동적으로 빈으로 등록 해 줌
@Controller
public class ProductController { //controller -> service -> dao 호출
	// 컨트롤러는 서비스를 DI 받고, 서비스는 dao를 DI 받는다.
	
	@Autowired
	private ProductService productService;
	
	// /products라고 url요청이 들어오면 이 메소드를 실행
	@RequestMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products",products); // 리스트를 모델에 담아 보냄
		
		return "products"; // view의 logical name
	}
	
	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		
		return "viewProduct";
	}
}
