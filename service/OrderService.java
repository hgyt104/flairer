package stu.inflp.flairer.service;

import java.util.List;

import stu.inflp.flairer.vo.OrderVO;



public interface OrderService {

	public List<OrderVO> getselectPayList(int active , String mcode); // 장바구니 상품리스트 조회 장바구니 리스트
	
	public int getcartCheck(String mcode, String pcode, int amount);
	
	
	
	
}
