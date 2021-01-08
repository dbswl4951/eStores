package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
// Ŭ�������� @RequestMapping������ �����ϴ�! �޼ҵ忡 ������ url�� ��� Ŭ������ ���� url�� �ȴ�
@RequestMapping("/admin") // admin�̶�� url ��û�� ������ ��, �� ��Ʈ�ѷ��� �۵�
public class AdminController {
	@Autowired
	private ProductService productService; // controller�� service���� ���� DB�� ����

	@RequestMapping // @RequestMapping("/admin")�� ���� ��. @RequestMapping("/admin")�� ����� ��, �� �޼ҵ嵵 ����
					// �� �� ��
	public String adminPage() {
		return "admin"; // tiles.xml�� �ִ� definition name�� �̸� ��ġ�ؾ� ��
	}

	// /admin/productInventory�� ���� �� (/admin�� Ŭ�������� ��������� ������ ���� ���⼭ �ٽ� �� �ʿ�X)
	@RequestMapping("/productInventory")
	public String getProduct(Model model) { // controller (DB���� product���� ����) -> model -> view
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "productInventory"; // tiles.xml�� �ִ� definition name�� �̸� ��ġ�ؾ� ��
	}

	// addProduct ��ư Ŭ�� ���� ��
	@RequestMapping(value="/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		product.setCategory("��ǻ��");
		model.addAttribute("product", product);

		return "addProduct";
	}

	@RequestMapping(value="/productInventory/addProduct", method = RequestMethod.POST)
	// @Valid : product�� ��ҵ��� �˻� �� -> BindingResult result�� �� ��� �� ����
	// ����ڰ� jsp������ �Է��� product�� �ڵ������� ��� ��
	public String addProductPost(@Valid Product product, BindingResult result, 
			HttpServletRequest request) {

		/*
		System.out.println("ID : "+product.getId());
		System.out.println("name : "+product.getName());
		System.out.println("price : "+product.getPrice());
		System.out.println("unitInStock : "+product.getUnitInStock());
		*/

		System.out.println("in addProductPost: " + product);
		
		if (result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors(); // ��� ���� ���� ��

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // pojo�� ������� �޼������� ���
			}
			return "addProduct"; // product�� result�� ���� ��� �Ѿ� �� -> jsp���� ���� �� ���� (spring form �±׷� ����)
		}

		// jsp���� Product product�� ���� �ָ� �̿��� resources/images�� image �����ϴ� �۾� +
		// imageFilename �����ϴ� �۾�
		MultipartFile productImage = product.getProductImage(); // product�� ���ε� �� ��ü���� �̹��� �� productImage�� ����
		// root�� ���� ��� ���� ��
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		System.out.println("rootDirectory : "+rootDirectory);
		System.out.println("productImage : "+productImage);
		
		// productImage���� ������ ���� content, fileName, size ���� ���� ��� �� ����
		// productImage.getOriginalFilename()�� ���� ���� �̸� �����ͼ� �ش� ��ο� ����
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		System.out.println("save : "+savePath);

		if (productImage != null && !productImage.isEmpty()) {
			try {
				// ���� �̹��� ������ ���� productImage�� savePath�� �̸����� ����
				// transferTo : ���ε� �� ���� �����͸� ������ ���Ͽ� ����
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// DB�� product�����ϱ� ��, ���� �̸��� product�� set
		product.setImageFilename(productImage.getOriginalFilename());

		productService.addProduct(product);

		// redirect�� ���� /admin/productInventory url�� ���� ��Ʈ�ѷ� ȣ��
		// jsp�� ��� �� model�� product��ü�� jsp�� �Ѱ� �� �� ����
		// redirect ��� �� �� ��� => �ѱ�� model ��ü (product)�� ��� jsp�� �ƹ��͵� ��� ����X
		return "redirect:/admin/productInventory";
	}

	// {id} : pathVariable. id�� �Ѿ���� deleteProduct�� ���ڷ� �Ѿ� ��
	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id,HttpServletRequest request) {
		Product product = productService.getProductById(id);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// ���� ����� �̹����� ��θ� ����
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());
		
		// ������ �����Ѵٸ�, ����
		if(Files.exists(savePath)) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		productService.deleteProduct(product);

		return "redirect:/admin/productInventory";
	}

	// ���� ���� ������ ��, ������ �ִ� ������ ������ �����ִ� ��Ʈ�ѷ�
	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		System.out.println("ID : "+product.getId());
		System.out.println("name : "+product.getName());
		System.out.println("price : "+product.getPrice());
		model.addAttribute("product", product);

		return "updateProduct";
	}

	// ���������� submit�� ������ ��, ������ �ٲ�� ���� �����ϴ� ��Ʈ�ѷ�
	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@Valid Product product, BindingResult result, HttpServletRequest request) {
		System.out.println("product content:" + product.getName());
		System.out.println("product content:" + product.getMenufacture());
		System.out.println("product content:" + product.getPrice());
		
		if (result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors(); // ��� ���� ���� ��

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // pojo�� ������� �޼������� ���
			}
			return "updateProduct";
		}

		// jsp���� Product product�� ���� �ָ� �̿��� resources/images�� image �����ϴ� �۾� +
		// imageFilename �����ϴ� �۾�
		MultipartFile productImage = product.getProductImage(); // product�� ���ε� �� ��ü���� �̹��� �� productImage�� ����
		// root�� ���� ��� ���� ��
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// productImage���� ������ ���� content, fileName, size ���� ���� ��� �� ����
		// productImage.getOriginalFilename()�� ���� ���� �̸� �����ͼ� �ش� ��ο� ����
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage != null && !productImage.isEmpty()) {
			try {
				// ���� �̹��� ������ ���� productImage�� savePath�� �̸����� ����
				// transferTo : ���ε� �� ���� �����͸� ������ ���Ͽ� ����
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// DB�� product�����ϱ� ��, ���� �̸��� product�� set
		product.setImageFilename(productImage.getOriginalFilename());

		productService.updateProduct(product);

		return "redirect:/admin/productInventory";
	}
}
