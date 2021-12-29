package stu.inflp.flairer.controller;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.ProductService;
import stu.inflp.flairer.vo.ProductVO;

@Controller
@AllArgsConstructor
@RequestMapping("/prod/*") // prod에 관한 모든걸 받게 한다.
public class ProductController {

	@Autowired
	ProductService service;

	@RequestMapping("/") // 맵핑주소
	public String prodsController(Model model) {

		List<ProductVO> list = service.prods(); // productVO list 에 service.prods 넣은거

		model.addAttribute("product", list);

		return "product";
	}

	@RequestMapping("/{category}")
	public String prodlist(@PathVariable String category, Model model) {
		// PathVariable String category --> url로 받는 걸 변수에 집어넣는다.
		System.out.println(category);
		
		List<ProductVO> list = service.getProds(category); // productVO list 에 service.prods 넣은거
//		List<ProductVO> pVo = service.prods();

		switch (category) {
			case "man":
				category = "Man Perfume";
				break;
			case "woman":
				category = "Woman Perfume";
				break;
			case "defuser":
				category = "Defuser";
				break;
			case "candle":
				category = "Candle";
				break;
		}
		model.addAttribute("product", list);
		model.addAttribute("cateName", category);
//		System.out.println(list);
//		System.out.println(pVo);
		
		return "product";
	}

	@RequestMapping("/{category}/{pcode}")
	public String detailProd(@PathVariable String pcode, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		ProductVO pVo = new ProductVO();

		pVo.getPcode();
		pVo = service.getdetailProducts(pcode);

		CookieController cook = new CookieController(request, response);
		try {
			cook.addCookies(pVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("product", pVo);

		return "productDetail";
	}
	// 검색

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public Object Json(Model model, Locale locale, String name) {

		List<ProductVO> list = service.getsearchProduct(name);

		return list;

	}

}
