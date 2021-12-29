package stu.inflp.flairer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.MypageService;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.OrderVO;

@Controller
@AllArgsConstructor
@RequestMapping("/mypage/*")
public class MypageController {
	@Autowired
	MypageService service;

	@RequestMapping("/mypage")
	public String mylist(HttpServletRequest req, HttpServletResponse res, Model model) {
		HttpSession session = req.getSession();
		MemberVO mv = (MemberVO) session.getAttribute("loginUser");
		if (mv == null) {
			String message = "로그인 후 이용 가능한 서비스 입니다.";
			model.addAttribute(message);
			return "signIn";
		}
		int isAdmin = mv.getAdmin();
		if (isAdmin == 0) {
			String mcode = mv.getMcode();
			List<Integer> list = service.listCount(mcode);
			model.addAttribute("countActive", list);
			List<List<OrderVO>> mylist = service.myList(mcode);
			model.addAttribute("outlists", mylist);
			model.addAttribute("loginUser", mv);

			List<Integer> totalCnts = new ArrayList<Integer>();
			int totalCnt = 0;

			for (List<OrderVO> oov : mylist) {
				for (OrderVO ov : oov) {
					int amount = ov.getAmount();
					int price = ov.getPrice();
					totalCnt += amount * price;
				}
				totalCnts.add(totalCnt);
				totalCnt = 0;
			}
			
			System.out.println(mv);
			model.addAttribute("totalCnt", totalCnts);

			CookieController ck = new CookieController(req, res);
			try {
				model.addAttribute("recent", ck.createRecent());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "myPage";
		} else {
			return "adminPage";
		}

	}

}
