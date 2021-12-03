package stu.inflp.flairer.service;

import java.util.List;

import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.PaymentVO;
import stu.inflp.flairer.vo.ProductVO;


public interface AdminService {
	public List<?> getList(String tname, String cname, String cate);
	public List<MemberVO> getadminMember(); // 관리자 페이지 회원 조회
	public List<ProductVO> getadminProduct(String category); // 관리자 페이지 상품 조회
	public List<BoardVO> getadminBoard(); // 관리자 페이지 게시판 조회
	public List<PaymentVO> getadminPayment(); // 관리자 페이지 결제정보  조회
	
	public String inserProduct(ProductVO pVo);
	
	public void methodFactory(String name, String value, String type, String code, String _method);
	//하나의 서비스에서 작성(수정,삭제)	
}
