package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

//�����̳ʰ� �� Ŭ������ �ڵ������� ������ ��� �� ��
@Controller
public class ProductController { //controller -> service -> dao ȣ��
	// ��Ʈ�ѷ��� ���񽺸� DI �ް�, ���񽺴� dao�� DI �޴´�.
	
	@Autowired
	private ProductService productService;
	
	// /products��� url��û�� ������ �� �޼ҵ带 ����
	@RequestMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products",products); // ����Ʈ�� �𵨿� ��� ����
		
		return "products"; // view�� logical name
	}
	
	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		
		return "viewProduct";
	}
}
