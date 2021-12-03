package stu.inflp.flairer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.PaymentVO;
import stu.inflp.flairer.vo.ProductVO;

@Mapper
public interface AdminMapper {
	public List<Map<String, Object>> getList(@Param("table") String tname, @Param("codeName") String cname, @Param("category") String cate);
	
	//admin페이지 회원조회
	public List<MemberVO> adminMember(); 
	
	 // admin페이지 상품 조회
	public List<ProductVO> adminProduct(String category);
	
	// admin페이지 게시판	 조회
	public List<BoardVO> adminBoard();
	
	// admin페이지 주문 조회
	public List<PaymentVO> adminPayment(); 
	
	//관리자페이지 총 수정 ,삭제
	public void adminUpdate(@Param("tname")String tname, @Param("codename")String codename, @Param("name")String name, @Param("value")String value, @Param("code")String code); 
	public void adminDelete(@Param("tname")String tname, @Param("codename")String codename, @Param("code")String code);
	
	//상품 추가 	
	public void insertProduct(ProductVO pv);
	//현재 시퀀스 가져오기 
	public String getSeq();	
}
