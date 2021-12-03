package stu.inflp.flairer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.PaymentService;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.OrderVO;

@Controller
@AllArgsConstructor
@RequestMapping("/payment/*")
public class PaymentController {
	@Autowired
	PaymentService service;

	@RequestMapping("/paymentlist")
	public String cartToPay(HttpServletRequest req, Model model, String pids) {
		HttpSession session = req.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		if (session.getAttribute("loginUser") == null) {
			String message = "로그인이 필요한 서비스입니다.";
			model.addAttribute(message);
			return "signIn";
		}
		int totalCnt = 0;
		List<OrderVO> oVo = service.getPayList(1, mVo.getMcode(), pids);

		for (OrderVO ov : oVo) {
			int amount = ov.getAmount();
			int price = ov.getPrice();
			totalCnt += amount * price;
		}

		model.addAttribute("totalCnt", totalCnt);

		model.addAttribute("payments", oVo);
		model.addAttribute("member", mVo);

		return "payment";
	}
}
