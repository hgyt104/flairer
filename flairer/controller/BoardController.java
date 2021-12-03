package stu.inflp.flairer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.service.BoardService;
import stu.inflp.flairer.vo.BPageVO;
import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.ReplyVO;



@Controller
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	BoardService service;
	
	
	@RequestMapping("/") //맵핑주소 리스트 페이지 보여줌 	
	public String boardController(Model model, HttpServletRequest request ,String page, String pageDataCount) {
		List<BoardVO> Vo = service.getboardCount(page,pageDataCount);
		BPageVO bpVo = new BPageVO();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signin";
		}
		
		if(page == null) {
			page = "1";
		}
		if(pageDataCount == null)  {
			pageDataCount = "5";
		}
		Vo = service.getboardCount(page,pageDataCount);
		model.addAttribute("dtos",Vo);
		
		bpVo = service.getboardPageCount(page,pageDataCount);
		model.addAttribute("bPageVO" ,bpVo);
//		List<BoardVO> boardlist = service.getselectBoard(); // productVO list 에  service.prods 	넣은거 
//		model.addAttribute("dtos", boardlist);

		
		
		return "boardList";
	}
	@RequestMapping("/boardwriteview") //작성 양식 페이지 뿌림
	public String boardWriteview(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		BoardVO bVo = new BoardVO();
		
		
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signin";
		}
		
		bVo.setBid(mVo.getUserid());
		model.addAttribute("board" , bVo);
		
		return "boardWrite";
	}
	
	
	
	@RequestMapping("/boardwrite") //작성하기 위함
	public String boardWrite(Model model, String bpwd, String btitle, String bcontent,HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		BoardVO bVo = new BoardVO();
		
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signin";
		}
		bVo.setBid(mVo.getUserid());
		bVo.setBpwd(bpwd);
		bVo.setBtitle(btitle);
		bVo.setBcontent(bcontent);
		
		
		service.getinsertBoard(bVo);
		model.addAttribute("board" ,bVo);
		
	
		
		return "redirect:/board/";
	}
	@RequestMapping("/{bcode}") //선택후 조회수 증가 	
	public String boardchice(  Model model, HttpServletRequest request , @PathVariable int bcode) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		
		
		
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signIn";
		}
		

		model.addAttribute("board" ,service.selectOne(bcode));
		//댓글
		List<ReplyVO> rlist = service.getBoardReply(bcode);
	    model.addAttribute("replyList", rlist);
		
		return "boardView" ;
	}
	
	@RequestMapping("/boardmodifyview") //수정양식 페이지 //안되는 이유 bcode를 안받고 userid만 받아와서 db에 입력이안됌 
	public String boardModifyview(HttpServletRequest request, Model model ,int bcode) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		BoardVO bVo = new BoardVO();
		
		
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signIn";
		}
		bVo.setBcode(bcode);
		bVo.setBid(mVo.getUserid());
		bVo = service.selectOne(bcode);
		model.addAttribute("board" , bVo);
		model.addAttribute("loginUser", mVo);
		return "boardUpdate";
	}
	@RequestMapping("/boardmodify") //수정작성을 위한것  //BoardVO를 가져왔을 때
	public String boardModify( int bcode , String bpwd, String btitle, String bcontent, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		BoardVO bmVo = new BoardVO();
		
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signIn";
		}
	
		bmVo.setBid(mVo.getUserid()); //BoardVO를 가져왔을 때
		bmVo.setBcode(bcode);
		bmVo.setBpwd(bpwd);
		bmVo.setBtitle(btitle);
		bmVo.setBcontent(bcontent);
		
		service.getmodifyBoard(bmVo);
		model.addAttribute("loginUser", mVo);
		model.addAttribute("board" , bmVo);
		
	
		
		return "redirect:/board/" + bcode ;
	}
	@RequestMapping("/boardcheckview") //수정양식박스 페이지 폼	
	public String boardcheckview() {

		
		return "boardCheckPass";
		
	}
	
	@RequestMapping("/boardcheck") //수정삭제시 비밀번호 체크
	public String boardCheckView(Model model, HttpServletRequest request,String bpwd, int bcode) {

	
	BoardVO bVo = new BoardVO();
	String url = null;
	
	bVo = service.getcheckWord(bcode);
	
	if(bVo.getBpwd().equals(bpwd)) {
		url = "checkSuccess";
	}else {
		url = "boardCheckPass";
		model.addAttribute("msg","비밀번호가 틀렸습니다.");
	}
	
	model.addAttribute("bcode" , bVo);
	
	return url;
	
	}
	@RequestMapping("/boarddelete")
	public String boardDelete(Model model, int bcode, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mVo = (MemberVO) session.getAttribute("loginUser");
		BoardVO bVo = new BoardVO();
		
		if(session.getAttribute("loginUser") == null) {
			model.addAttribute("msg","로그인이 필요합니다.");
			return "signIn";
		}
		bVo.setBid(mVo.getUserid());
		bVo.setBcode(bcode);
		service.getdeleteBoard(bVo);
		model.addAttribute("bcode", bVo);
	
		return "redirect:/board/";
	}
	
	//댓글
	@RequestMapping("/replyWrite")
	   public String reply(HttpServletRequest req, Model model, int bcode, String rcontent, String rbid) {
	      HttpSession session = req.getSession();
	      MemberVO mv = (MemberVO)session.getAttribute("loginUser");
	      
	      if(session.getAttribute("loginUser") == null) {
	         String message = "로그인 후 이용 가능한 서비스 입니다.";
	         model.addAttribute(message);
	         return "signIn";
	      } 
	      service.getinsertBoardReply(bcode, rcontent, rbid);
	      
	      return "redirect:/board/" + bcode;
	      
	     
	   }
	//댓글 수정 
	@PostMapping("/replymodify")
	@ResponseBody
	public void replyUpdate(int rcode,String rcontent, Model model) {
		
	     ReplyVO rVo = new ReplyVO();
	     rVo.setRcode(rcode);
	     rVo.setRcontent(rcontent);
	     service.getmodifyBoardReply(rVo);
	    
	}
	//댓글 삭제
	@RequestMapping("/replydelete")
	public String deleteBoardReply(int rcode, int bcode ) {
		
		service.getdeleteBoardReply(rcode);
		return "redirect:/board/" + bcode;
	}
	
	
	@RequestMapping("/replycheckpass")
	public String replyCheckPass() {
		return "replyCheckPass";
	}
	
	@RequestMapping("/boardreplycheckpass")
	public String replyCheckPass(HttpServletRequest req, int rcode, String pwd, Model model, int bcode) {
		
		HttpSession session = req.getSession();
		MemberVO mv = (MemberVO)session.getAttribute("loginUser");
		service.getreplyCheckPass(rcode);
		
		
		model.addAttribute("bcode", bcode);
		if(mv.getPwd().equals(pwd)) {
			return "replyCheckSuccess";
		} else {
			model.addAttribute("message", "비밀번호가 다릅니다.");
			return "replyCheckPass";
		}
	}
	
	
	
	
}
