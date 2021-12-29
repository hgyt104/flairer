package stu.inflp.flairer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.AdminService;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.ProductVO;

@Controller
@AllArgsConstructor

public class AdminController {
	 //관리자 페이지
	@Autowired
	AdminService service;
	
	@GetMapping("/admin")
	public String admin(HttpServletRequest req, Model model, String menu, String category) {
		HttpSession session = req.getSession();
		MemberVO mv = (MemberVO)session.getAttribute("loginUser");
		
		if(mv == null) {
			String message = "로그인 후 이용 가능한 서비스 입니다.";
			model.addAttribute(message);
			return "signIn";
		} 
		
		if(mv.getAdmin() != 1) {
			return "redirect:/mypage";
		}
		
		List<?> list = null;
		
		String key = "members";
		String codeName = "mcode";
		
		if(menu == null) {
			menu = "member";
		} else if(menu.equals("product")) { 
			codeName = "pcode";
			key = "products";
		} else if(menu.equals("board")) {
			codeName = "bcode";
			key = "boards";
		} else if(menu.equals("payment")) {
			codeName = "pa_code";
			key = "payments";
		}		
		list = service.getList(menu, codeName, category);
		
		/*****************************************************
		if(menu == null) {
			list = service.getList("member", "mcode", null);
		} else if (menu.equals("product")) {
			list = service.getProductList(category);
			key = "products";
		} else if (menu.equals("board")) {
			list = service.getBoardList();
			key = "boards";
		} else if (menu.equals("payment")) {
			list = service.getPaymentList();
			key = "payments";
		}
		*****************************************************/
		model.addAttribute(key, list);
		return "adminPage";
	}
	
	@PostMapping("/admin")
	public ModelAndView admin(HttpServletRequest req,String _method, String type, String name, String value, String code, RedirectAttributes rttr) {
		HttpSession session = req.getSession();
		MemberVO mv = (MemberVO)session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("admin/index");
		
		if(mv == null) {
			String message = "로그인 후 이용 가능한 서비스 입니다.";
			mav.addObject(message);
			mav.setViewName("login");
		} 
		

		if(type != null) {
			service.methodFactory(name, value, type, code, _method);
		}
		if(_method.equals("delete")) {
			rttr.addAttribute("menu", type);
			if(type.equals("product")) {
				String category = "";
				if(Integer.parseInt(code) <= 20) {
					category = "1";
				} else if (Integer.parseInt(code) <= 40) {
					category = "2";
				} else if (Integer.parseInt(code) <= 60) {
					category = "3";
				} else  {
					category = "4";
				}
				rttr.addAttribute("category", category);
			}
			mav.setViewName("redirect:/admin");
		}else {
			return null;
		}
		return mav;
	}
	
	@PostMapping("/adminproduct")
	@ResponseBody
	public String admin(ProductVO pVo) {
		
		return service.inserProduct(pVo);
	}
	
}


//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import lombok.AllArgsConstructor;

//
//@Controller
//@AllArgsConstructor
//@RequestMapping("/admin")
//public class AdminControllerCopy {
//	
//	AdminService service;
//
//	@GetMapping("/{path_type}")
//	public ModelAndView loadPage(@PathVariable String path_type) {
//		
//		ModelAndView _mav = new ModelAndView("admin/index");
//		
//		String _key = "members";
//
//		switch(path_type) {
//			case "member":
//				break;
//			case "man":
//			case "woman":
//			case "defuser":
//			case "candle":
//				_key = "products";
//				break;
//			case "board":
//				_key = "boards";
//				break;
//			case "payment":
//				_key = "payments";
//				break;
//		}
//
//		_mav.addObject(_key, service.getList(path_type));
//
//		return _mav;
//	}
//
//	@PostMapping("/{path_type}/{path_code}")
//	@ResponseBody
//	public String createRecord(@PathVariable String path_type, @PathVariable String path_code, ProductVO command_vo) {
//		command_vo.setPcode(path_code);
//		return service.insertProduct(command_vo);
//	}
//
//	@PatchMapping("/{path_type}/{path_code}")
//	@ResponseBody
//	public String modifyRecord(@PathVariable String path_type, @PathVariable String path_code) {
//		return null;
//	}
//
//	@DeleteMapping("/{path_type}/{path_code}")
//	public ModelAndView deleteRecord(@PathVariable String path_type, @PathVariable String path_code) {
//		return null;
//	}
//
//
//
//	@GetMapping("/admin")
//	public String admin(HttpServletRequest req, Model model, String menu, String category) {
//		HttpSession session = req.getSession();
//		MemberVO mv = (MemberVO)session.getAttribute("loginUser");
//		
//		if(mv == null) {
//			String message = "로그인 후 이용 가능한 서비스 입니다.";
//			model.addAttribute(message);
//			return "login";
//		} 
//		
//		if(mv.getAdmin() != 1) {
//			return "redirect:/mypage";
//		}
//		
//		List<?> list = null;
//		String key = "members";
//		String codeName = "mcode";
//		
//		if(menu == null) {
//			menu = "member";
//		} else if(menu.equals("product")) { 
//			codeName = "pcode";
//			key = "products";
//		} else if(menu.equals("board")) {
//			codeName = "bcode";
//			key = "boards";
//		} else if(menu.equals("payment")) {
//			codeName = "pa_code";
//			key = "payments";
//		}
//		list = service.getList(menu, codeName, category);
//		/*****************************************************
//		if(menu == null) {
//			list = service.getList("member", "mcode", null);
//		} else if (menu.equals("product")) {
//			list = service.getProductList(category);
//			key = "products";
//		} else if (menu.equals("board")) {
//			list = service.getBoardList();
//			key = "boards";
//		} else if (menu.equals("payment")) {
//			list = service.getPaymentList();
//			key = "payments";
//		}
//		*****************************************************/
//		model.addAttribute(key, list);
//
//		return "adminpage";
//	}
//	
//	@PostMapping("/admin")
//	public ModelAndView admin(HttpServletRequest req,String _method, String type, String name, String value, String code, RedirectAttributes rttr) {
//		HttpSession session = req.getSession();
//		MemberVO mv = (MemberVO)session.getAttribute("loginUser");
//		ModelAndView mav = new ModelAndView();
//		
//		if(mv == null) {
//			String message = "로그인 후 이용 가능한 서비스 입니다.";
//			mav.addObject(message);
//			mav.setViewName("login");
//		} 
//		
//
//		if(type != null) {
//			service.methodFactory(name, value, type, code, _method);
//		}
//		if(_method.equals("delete")) {
//			rttr.addAttribute("menu", type);
//			if(type.equals("product")) {
//				String category = "";
//				if(Integer.parseInt(code) <= 20) {
//					category = "1";
//				} else if (Integer.parseInt(code) <= 40) {
//					category = "2";
//				} else if (Integer.parseInt(code) <= 60) {
//					category = "3";
//				} else  {
//					category = "4";
//				}
//				rttr.addAttribute("category", category);
//			}
//			mav.setViewName("redirect:/admin");
//		}else {
//			return null;
//		}
//		return mav;
//	}
//	
//	@PostMapping("/adminproduct")
//	@ResponseBody
//	public String admin(ProductVO pv) {
//		
//		
//		return service.insertProduct(pv);
//	}
//}
//
//
//	
//	
//
