package stu.inflp.flairer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.MypageMapper;
import stu.inflp.flairer.vo.OrderVO;


@Service
@AllArgsConstructor
public class MyPageServiceImpl implements MypageService {
	
	@Autowired
	MypageMapper mapper;
	
	@Override
	public List<List<OrderVO>> myList(String mcode) {
			
		List<List<OrderVO>> outlist = new ArrayList<List<OrderVO>>();
		List<OrderVO> inlist = new ArrayList<OrderVO>();
		String str = null;
		List<OrderVO> lo = mapper.myList(mcode);
		if(lo == null) {
			return null;
			}
		for(OrderVO oVo : lo) {
			if(str != null && !(oVo.getPa_code().equals(str))){
				outlist.add(inlist);
				inlist = new ArrayList<OrderVO>();
			}
			inlist.add(oVo);
			str =oVo.getPa_code();
			//
		}
		outlist.add(inlist);
	
		return outlist;
	}

	@Override
	public List<Integer> listCount(String mcode) {
		Map<String, Object> map = mapper.payListCount(mcode);
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i < 5; i++) {
	         list.add(new Integer(((BigDecimal)(map.get(Integer.toString(i)))).intValue()));
	      }

		
		return list; 
	}
	

}
