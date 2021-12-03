package stu.inflp.flairer.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface PaycompletService {

	
	
	public void uppaycomplet(String pa_code,  String mcode, String pids);
}
