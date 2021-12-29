package stu.inflp.flairer.service;

import java.util.List;

import stu.inflp.flairer.vo.OrderVO;



public interface PaymentService {

	
	public List<OrderVO> getPayList(int active, String mcode, String pids);
}
