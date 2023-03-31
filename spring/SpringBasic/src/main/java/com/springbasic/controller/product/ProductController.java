package com.springbasic.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbasic.domain.ProductVO;

@Controller
public class ProductController {
	
	// Model 객체를 이용하여 view 단으로 바인딩하기
	@RequestMapping("viewProduct")
	public void viewProduct(Model model) {
		ProductVO product = new ProductVO("A001" , "새우깡", 2000);
		
		model.addAttribute("product", product); // 바인딩 후에 viewProduct.jsp로 forwarding
	}
	
	// ModelAndView : view단에 바인딜 할 객체 정보와 응답될 view단의 정보를 가지고 있는 객체
	@RequestMapping("viewProduct2")
	public ModelAndView viewProduct() {
		ProductVO product = new ProductVO("A001" , "새우깡", 2000);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("product" , product); // 바인딩할 객체 추가
		mav.setViewName("viewProduct"); // 응답될 view 지정
		
		return mav;
	}
	
	// 주소가 바뀌지 않으면 forwarding 바뀌면 redirect
	// 페이지 redirect 하기 "redirect:(리다이렉트할경로)"
	@RequestMapping("viewProduct3")
	public String redirectView() {
	//	return "redirect:/home.jsp"; // view단 바로 호출 불가 반드시 컨트롤러의 매핑주소를 거쳐서 접근해야한다
		return "redirect:/"; // 
	}
	
	// 파라메터를 넘겨주면서 페이지 redirect 하기
	@RequestMapping("viewProduct4")
	public String redirectView2(RedirectAttributes rttr) {
		//hello에 넘겨줄 파라메터를 RedirectAttributes 객체에 저장
		// rttr.addAttribute("name", "엉겅퀴");
		// rttr.addAttribute("age", "1");
		
		// addFlashAttribute()를 사용하면 넘겨지는 파라메터가 쿼리스트링 형태로 보여지지 않는다.
		rttr.addFlashAttribute("name", "씀바귀"); // 쿼리스트링이 나타나지 않는다 보안상의 이유 (임시로 저장 X)
		rttr.addAttribute("age", "1");
		
		return "redirect:/sayHello";
		
	}
	
	@RequestMapping("viewProduct5")
	public @ResponseBody ProductVO viewProduct3() { // json으로 반환 해줌
		ProductVO product = new ProductVO("A001" , "새우깡", 2000);
		
		return product;
	}
	
	@RequestMapping("savePrd")
	public String saveProductPage() {
		
		return "inputProduct";
	}

	
//	// view에서 입력한 데이터를 어떻게 가져올 수 있을까
//	// @RequestParam 어노테이션을 이용한다
//	@RequestMapping( value ="savePrd", method = RequestMethod.POST )
//	public void saveProduct(@RequestParam("productNo") String prodNo,
//			@RequestParam("productName") String prodName, @RequestParam("price") int price) {
//		
//		System.out.println("상품 저장!");
//		
//		ProductVO product = new ProductVO(prodNo, prodName, price);
//		
//		System.out.println(product.toString());
//		
//	}
	
	// 만약, view에서 입력받는 데이터들의 객체가 있다면 그 객체로 파라메터의 값들을 가져올 수 있다.
	// 이때 Spring은 기본 생성자를 이용하여 ProductVO 객체를 만든다. (오버라이딩한 생성자 메서드만 있으면 오류가 난다)
	// input 태그의 name 값을 가져와 그 name 값에 해당하는 setter를 호출하여 객체에 값을 저장
	@RequestMapping( value ="savePrd", method = RequestMethod.POST )
	public void saveProduct(ProductVO product) { // DTO 가 있다면 입력받은 데이터로 DTO를 생성해 준다
		
		
		System.out.println("상품 저장!");
		
//		ProductVO product = new ProductVO(prodNo, prodName, price);
		
		System.out.println(product.toString());
		
	}
	
}
