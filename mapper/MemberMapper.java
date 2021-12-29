package  stu.inflp.flairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import stu.inflp.flairer.vo.MemberVO;

@Mapper
public interface MemberMapper {
	//유저 정보를 가져옴 
	public MemberVO getmember(String userid);
	
	//유저의 접속일을 업데이트 시켜줌
	public void upmember(String userid);
	
	//usercheck select 
	public String userch(String userid);
	
	//회원가입 	
	public void signupMember(MemberVO member);
	
	// 아이디 중복체크 
	public int signupIdCheck(String userid);	
	
	//아이디 번호로 찾기 
	public MemberVO signIdFind(@Param("name")String name, @Param("phone")String phone); 	
	
	//회원 정보를 변경하기 위함
	public void membermodify(MemberVO member); 
	
	// 회원 정보 삭제시 비밀번호 체크
	public MemberVO deletecheckpassword(@Param("userid")String userid,@Param("pwd") String pwd);
	
	//회원 정보를 휴먼상태로 바꾸기 위한 
	public void deletemember(MemberVO member); 
}
