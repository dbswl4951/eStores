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
// 클래스에도 @RequestMapping설정이 가능하다! 메소드에 설정된 url은 모두 클래스의 서브 url이 된다
@RequestMapping("/admin") // admin이라는 url 요청이 들어왔을 때, 이 컨트롤러가 작동
public class AdminController {
	@Autowired
	private ProductService productService; // controller는 service단을 통해 DB에 접급

	@RequestMapping // @RequestMapping("/admin")와 연결 됨. @RequestMapping("/admin")가 실행될 때, 이 메소드도 같이
					// 실 행 됨
	public String adminPage() {
		return "admin"; // tiles.xml에 있는 definition name과 이름 일치해야 함
	}

	// /admin/productInventory로 가게 됨 (/admin은 클래스에서 정의해줬기 때문에 굳이 여기서 다시 쓸 필요X)
	@RequestMapping("/productInventory")
	public String getProduct(Model model) { // controller (DB에서 product정보 읽음) -> model -> view
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "productInventory"; // tiles.xml에 있는 definition name과 이름 일치해야 함
	}

	// addProduct 버튼 클릭 했을 때
	@RequestMapping(value="/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		product.setCategory("컴퓨터");
		model.addAttribute("product", product);

		return "addProduct";
	}

	@RequestMapping(value="/productInventory/addProduct", method = RequestMethod.POST)
	// @Valid : product의 요소들을 검사 함 -> BindingResult result에 그 결과 값 담음
	// 사용자가 jsp폼에서 입력한 product가 자동적으로 들어 옴
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
			List<ObjectError> errors = result.getAllErrors(); // 모든 에러 가져 옴

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // pojo에 적어놓은 메세지들을 출력
			}
			return "addProduct"; // product와 result가 같이 뷰로 넘어 감 -> jsp에서 받을 수 있음 (spring form 태그로 인해)
		}

		// jsp에서 Product product로 들어온 애를 이용해 resources/images에 image 저장하는 작업 +
		// imageFilename 설정하는 작업
		MultipartFile productImage = product.getProductImage(); // product에 바인딩 된 객체에서 이미지 얻어서 productImage에 저장
		// root의 실제 경로 가져 옴
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		System.out.println("rootDirectory : "+rootDirectory);
		System.out.println("productImage : "+productImage);
		
		// productImage에는 사진에 대한 content, fileName, size 같은 정보 모두 들어가 있음
		// productImage.getOriginalFilename()를 통해 파일 이름 가져와서 해당 경로에 저장
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		System.out.println("save : "+savePath);

		if (productImage != null && !productImage.isEmpty()) {
			try {
				// 실제 이미지 정보를 가진 productImage를 savePath의 이름으로 저장
				// transferTo : 업로드 한 파일 데이터를 지정한 파일에 저장
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// DB에 product저장하기 전, 실제 이름을 product에 set
		product.setImageFilename(productImage.getOriginalFilename());

		productService.addProduct(product);

		// redirect를 통해 /admin/productInventory url을 갖는 컨트롤러 호출
		// jsp에 출력 할 model인 product객체를 jsp에 넘겨 줄 수 있음
		// redirect 사용 안 할 경우 => 넘기는 model 객체 (product)가 없어서 jsp에 아무것도 출력 되지X
		return "redirect:/admin/productInventory";
	}

	// {id} : pathVariable. id가 넘어오면 deleteProduct의 인자로 넘어 감
	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id,HttpServletRequest request) {
		Product product = productService.getProductById(id);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// 실제 저장된 이미지의 경로명 저장
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());
		
		// 파일이 존재한다면, 삭제
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

	// 수정 폼을 눌렀을 때, 기존에 있던 정보를 가지고 보여주는 컨트롤러
	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		System.out.println("ID : "+product.getId());
		System.out.println("name : "+product.getName());
		System.out.println("price : "+product.getPrice());
		model.addAttribute("product", product);

		return "updateProduct";
	}

	// 수정폼에서 submit을 눌렀을 때, 실제로 바뀌는 것을 저장하는 컨트롤러
	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@Valid Product product, BindingResult result, HttpServletRequest request) {
		System.out.println("product content:" + product.getName());
		System.out.println("product content:" + product.getMenufacture());
		System.out.println("product content:" + product.getPrice());
		
		if (result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors(); // 모든 에러 가져 옴

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // pojo에 적어놓은 메세지들을 출력
			}
			return "updateProduct";
		}

		// jsp에서 Product product로 들어온 애를 이용해 resources/images에 image 저장하는 작업 +
		// imageFilename 설정하는 작업
		MultipartFile productImage = product.getProductImage(); // product에 바인딩 된 객체에서 이미지 얻어서 productImage에 저장
		// root의 실제 경로 가져 옴
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// productImage에는 사진에 대한 content, fileName, size 같은 정보 모두 들어가 있음
		// productImage.getOriginalFilename()를 통해 파일 이름 가져와서 해당 경로에 저장
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage != null && !productImage.isEmpty()) {
			try {
				// 실제 이미지 정보를 가진 productImage를 savePath의 이름으로 저장
				// transferTo : 업로드 한 파일 데이터를 지정한 파일에 저장
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// DB에 product저장하기 전, 실제 이름을 product에 set
		product.setImageFilename(productImage.getOriginalFilename());

		productService.updateProduct(product);

		return "redirect:/admin/productInventory";
	}
}
