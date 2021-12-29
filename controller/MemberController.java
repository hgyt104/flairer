package stu.inflp.flairer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.MemberService;
import stu.inflp.flairer.vo.MemberVO;


@Controller
@AllArgsConstructor

public class MemberController {

	@Autowired
	MemberService service;

	@RequestMapping("/sign")
	public String loginPage() {

		return "signIn";
	}

	@RequestMapping("/signin")
	public String memberLogin(String userid, String pwd, Model model, HttpServletRequest request) {

		String message = null;
		String url = "signIn";
		int usch = service.check(userid, pwd);
		switch (usch) {
			case -1:
				message = "아이디가 없습니다.";

				break;
			case 0:
				message = "패스워드가 틀렸습니다.";

				break;
			case 1:

				MemberVO mb = service.getmember(userid);
				message = "로그인이 성공적으로 되었습니다.";

				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mb);
				url = "index";
				break;
		}
		model.addAttribute("msg", message);

		return url;

	}

	@RequestMapping("/signout")
	public String memberLogout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.invalidate();// invalidate 는 세션값을 다 날림
		model.addAttribute("msg", "로그아웃 되었습니다.");

		return "index";
	}

	@RequestMapping("/signupview") // 작성 양식 페이지 뿌림
	public String signupview(Model model) {

		return "signUp";
	}

	@RequestMapping("/signup") // 작성 뿌림 회원가입만 아이디 체크안함
	public String signup(Model model, MemberVO member) {
		String message = null;
		
		member.getName();
		member.getUserid();
		member.getPwd();
		member.getAddress();
		member.getGender();
		member.getPhone();
		
		service.getsignupmember(member);

		model.addAttribute("member", member);
		return "signIn";
	}

	@RequestMapping("/idcheck") // 아이디비번 찾기 박스
	public String idcheck(Model model, String userid) {
		MemberVO mVo = new MemberVO();
		int result = service.getsignupIdCheck(userid);

		model.addAttribute("userid", userid);
		model.addAttribute("result", result);

		mVo.setUserid(mVo.getUserid());

		return "idCheck";
	}

	@RequestMapping("/idFind") // 아이디 비번
	@ResponseBody
	public String idfind(Model model, String name, String phone) {
		MemberVO mVo = new MemberVO();
		String result = service.getsignIdFind(name, phone);

		if (name.equals("") || phone.equals("")) {
			return "1";
		}
		// model.addAttribute("name",name);
		// model.addAttribute("phone",phone);
		// model.addAttribute("result", result);

		return result;
	}

	@RequestMapping("/idFindview")
	public String idfindview() {

		return "idFind";
	}

	@RequestMapping("/membermodfiy")
	public String membermodify(Model model, HttpServletRequest request, RedirectAttributes rttr, MemberVO member,
			String userid, String pwd, String address, String phone) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");

		if (mVo == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "signIn";
		}

		String message = "수정이 완료 되었습니다.";

		member.setPwd(pwd);
		member.setAddress(address);
		member.setPhone(phone);

		service.getmembermodify(member);
		model.addAttribute("mVo", member);
		model.addAttribute("loginUser", mVo);

		rttr.addFlashAttribute("msg", message);

		return "redirect:/";
	}

	@RequestMapping("/membermodfiyview") // view
	public String membermodifyview(Model model, HttpServletRequest request, MemberVO member, String userid, String pwd,
			String phone, String address) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");

		if (mVo == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "signIn";
		}

		return "memberUpdate";
	}

	@RequestMapping("/deletecheckview") // 삭제양식박스 페이지 폼
	public String deletemembercheckview() {

		return "memberDelete";
	}

	@RequestMapping("/deletecheck")
	public String deletemembercheck(Model model, HttpServletRequest request, String userid, String pwd) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");

		if (mVo == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "signIn";
		}

		int active = 1;

		String url = null;

		System.out.println(mVo.getPwd() + pwd);
		System.out.println(mVo.getUserid() + userid);
		mVo = service.getdeletecheckpassword(userid, pwd);

		if (mVo != null && mVo.getPwd().equals(pwd)) {
			active = 0;
			url = "deleteSuccess";
		} else {
			url = "memberDelete";
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
		}
		model.addAttribute("userid", mVo);
		return url;
	}

	@RequestMapping("/memberdelete")
	public String memberdelete(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");

		if (mVo == null) {
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "signIn";
		}

		service.getdeletemember(mVo);

		model.addAttribute("userid", mVo);

		request.getSession().invalidate();

		return "redirect:/";
	}

}
