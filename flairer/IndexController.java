package stu.inflp.flairer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.IndexService;
import stu.inflp.flairer.vo.ProductVO;





@Controller
@AllArgsConstructor

public class IndexController {

	@Autowired
	IndexService service;
	
	@RequestMapping("/")
	public String nproduct(Model model,String msg) {
		 //PathVariable String category --> url로 받는 걸 변수에 집어넣는다. 
		
		List<List<ProductVO>> newList = service.newprod();
	
		model.addAttribute("nproduct", newList); //아웃리스트 이름이 nproduct
		if(msg != null) {
			model.addAttribute("msg", msg);
		}
	
		return "index";
		
	}
	
	
}
