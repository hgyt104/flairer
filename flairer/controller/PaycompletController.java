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
import stu.inflp.flairer.service.PaycompletService;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.OrderVO;

@Controller
@AllArgsConstructor
@RequestMapping("/paycomplet/*")
public class PaycompletController {
	@Autowired
	PaycompletService service;

	@RequestMapping("/paycompletlist")
	public String paycompletlist(HttpServletRequest req, Model model, String pids, String pa_code) {

		HttpSession session = req.getSession();
		List<OrderVO> oVo = new ArrayList<OrderVO>();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		if (session.getAttribute("loginUser") == null) {
			String message = "로그인이 필요한 서비스입니다.";
			model.addAttribute(message);
			return "signIn";
		}
		String mcode = mVo.getMcode();

		service.uppaycomplet(pa_code, mcode, pids);
		return "redirect:/";

	}
}
