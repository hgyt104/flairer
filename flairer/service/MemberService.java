package stu.inflp.flairer.service;

import stu.inflp.flairer.vo.MemberVO;

public interface MemberService {

	
	public MemberVO getmember(String userid); //컨트롤러로 보내기 
	public int check(String userid, String pwd);
	public void getsignupmember(MemberVO member);
	public int getsignupIdCheck(String userid);	
	public String getsignIdFind(String name, String phone);
	public void getmembermodify(MemberVO member);
	
	public MemberVO getdeletecheckpassword(String userid, String pwd);
	public void getdeletemember(MemberVO member);
}
