package stu.inflp.flairer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.PaycompletMapper;


@Service
@AllArgsConstructor
public class PaycompletServiceImpl implements PaycompletService {
	
	@Autowired
	PaycompletMapper mapper;
	

	@Override
	public void uppaycomplet(String pa_code, String mcode, String pids) {
		mapper.paycomplet();
		
		
		 List<String> pcodes= null;
	      if(pids != null) {
	         pcodes = new ArrayList<String>();
	         for(String pcode : pids.split("\\|")) {
	            pcodes.add(pcode);
	         } 
	      }
		
	 
		
		 mapper.uppaycomplet(pa_code, mcode, pcodes);
		
	
	}

}
