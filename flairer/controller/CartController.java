package stu.inflp.flairer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.OrderService;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.OrderVO;

@Controller
@AllArgsConstructor
@RequestMapping("/cart/*")
public class CartController {
	@Autowired
	OrderService service;

	@RequestMapping("/cartlist") // 장바구니
	public String cartlist(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		List<OrderVO> oVo = new ArrayList<OrderVO>();
		int active = 1;

		if (mVo == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "signIn";
		}
		String mcode = mVo.getMcode();
	
		int totalCnt = 0;
		oVo = service.getselectPayList(1, mcode);

		for (OrderVO ov : oVo) {
			int amount = ov.getAmount();
			int price = ov.getPrice();
			totalCnt += amount * price;
		}

		model.addAttribute("payments", oVo);
		model.addAttribute("totalCnt", totalCnt);

		return "cart";
	}

}
