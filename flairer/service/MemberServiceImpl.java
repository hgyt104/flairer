package stu.inflp.flairer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.MemberMapper;
import stu.inflp.flairer.vo.MemberVO;


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	
	@Override
	public MemberVO getmember(String userid) {
		MemberVO mVo = mapper.getmember(userid);
		
		
		mapper.upmember(userid); // 마지막접속일 업데이트 하는 문장 
		
		return mVo;
	}

	
	
	public int check(String userid, String pwd) {
		
		String ch = mapper.userch(userid);
		
		int result = -1; // id가 없는것 
		
		if(ch != null) {
			if(pwd.equals(ch)) {
				result = 1; // 아이디 패스워드가 둘다 맞는 경우 앞에 pwd가 사용자가 입력한 pwd , ch는 디비에서 userid로 가져온 pwd	
			}else {
				result = 0; // 비밀번호만 틀린경우 
			}
		}
		
		return result ;
		
	}

	@Override //회원가입
	public void getsignupmember(MemberVO member) {
		
		mapper.signupMember(member);
		
	}



	@Override // 회원가입 아이디  중복 체크 	
	public int getsignupIdCheck(String userid) {
		int result = -1;
		
		if(mapper.getmember(userid) != null) {
			result = 1;
		}else {
			result = -1;
		}
		
		
		return result;
	}



	@Override
	public String getsignIdFind(String name, String phone) {
		String result = "";
		MemberVO mVo = mapper.signIdFind(name, phone);
		if(mVo!=null) {
			
			result += mVo.getUserid() + "/";
			result += mVo.getPwd();
			
		}
		
		return result;
	}
	@Override // 회원 정보 수정 
	public void getmembermodify(MemberVO member) {
		
		mapper.membermodify(member);
		
	}
	@Override
	public MemberVO getdeletecheckpassword(String userid, String pwd) {
		
		MemberVO mVo = mapper.deletecheckpassword(userid, pwd);
		
		return mVo;
	}
	

	@Override
	public void getdeletemember(MemberVO member) {
		
		mapper.deletemember(member);
		
	}
	
	
	

}
