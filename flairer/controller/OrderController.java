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
@RequestMapping("/order/*")
public class OrderController {
	@Autowired
	OrderService service;

	@RequestMapping("/orderlist")
	public String payment(Model model, HttpServletRequest request, String mcode, String pcode, int amount) {

		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		List<OrderVO> oVo = new ArrayList<OrderVO>();
		if (mVo == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "signIn";
		}
		mcode = mVo.getMcode();
		model.addAttribute(pcode);

		int pa_code = service.getcartCheck(mcode, pcode, amount);

		model.addAttribute(amount);

		if (pa_code != 1) {
			oVo = service.getselectPayList(1, mcode);
		}

		model.addAttribute("payments", oVo);

		return "redirect:/payment/paymentlist";
	}
}
