package stu.inflp.flairer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.PaymentMapper;
import stu.inflp.flairer.vo.OrderVO;


@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentMapper mapper;
	
	@Override
	   public List<OrderVO> getPayList(int active, String mcode, String pids) {
	      List<String> pcodes = null;
	      if(pids != null) {
	         pcodes = new ArrayList<String>();
	         for(String pcode : pids.split("\\|")) {
	            pcodes.add(pcode);
	         } 
	      }
	      return mapper.getPayList(active, mcode, pcodes);
	   }

}
