package stu.inflp.flairer.service;

import java.util.List;

import stu.inflp.flairer.vo.OrderVO;



public interface MypageService {
	
//	
//	public List<List<OrderVO>> getorderList(String mcode);
//	public List<Integer> getcountActive(String mcode);
	public List<List<OrderVO>> myList(String mcode);
	
	public List<Integer> listCount(String mcode);

} 
