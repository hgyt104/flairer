package stu.inflp.flairer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.OrderMapper;
import stu.inflp.flairer.vo.OrderVO;
import stu.inflp.flairer.vo.PaymentVO;


@Service
@AllArgsConstructor
public class OrderServiceServiceImpl implements OrderService {
	
	@Autowired
		OrderMapper mapper;
	
	@Override
	public List<OrderVO> getselectPayList(int active, String mcode) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		list = mapper.selectPayList(active, mcode);
		
		return list;
	}

	@Override
	public int getcartCheck(String mcode, String pcode, int amount) {
		
		List<PaymentVO> plist = mapper.cartCheck(mcode, pcode, amount);
		
		if(plist.isEmpty()) {
			mapper.cartCheck1(mcode, pcode, amount);
		}else {
			int pl = 0;
			String pa_code = "";
			
			for(PaymentVO pVo : plist){
				pa_code = pVo.getPa_code();
				if(pVo.getMcode().equals(mcode) && pVo.getPcode().equals(pcode)) {
					mapper.cartCheck3(amount,pcode,mcode );
				
					pl++;
				}
				
			}
			if(pl == 0) {
				
			mapper.cartCheck2(pa_code,mcode, pcode, amount);
		}
			return Integer.parseInt(pa_code);
	}
		//plist 비어있으면 첫번째 쿼리를 실행 , 비어있으면 -1 로 리턴 
		//int pl =0 그리고 PaymentVO pVo : plist foreach써서 mcode가 같을 때 그리고 pcode가 같을때 3번째 쿼리를 실행하고 ,
		//		pl 가 증가 했을 때 두번쨰 쿼리를 실행
		return  -1;
	}

	
	

}
