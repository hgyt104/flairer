package stu.inflp.flairer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import stu.inflp.flairer.vo.OrderVO;
import stu.inflp.flairer.vo.PaymentVO;

//주문하는것들

@Mapper
public interface OrderMapper {
	// 장바구니 상품리스트 조회 장바구니 리스트 카트 (장바구니 )
	public List<OrderVO> selectPayList(@Param("active")int active , @Param("mcode")String mcode);
	
	//장바구니에 데이터 유무 체크	
	public List<PaymentVO> cartCheck(@Param("mcode")String mcode, String pcode, int amount); 
	public void cartCheck1(@Param("mcode")String mcode, @Param("pcode")String pcode ,@Param("amount")int amount);
	public void cartCheck2(@Param("pa_code")String pa_code,@Param("mcode")String mcode,@Param("pcode") String pcode ,@Param("amount")int amount);
	public void cartCheck3(@Param("amount")int amount,@Param("pcode")String pcode,@Param("mcode")String mcode  );
}
