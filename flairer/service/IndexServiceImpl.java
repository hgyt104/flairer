package stu.inflp.flairer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.IndexMapper;
import stu.inflp.flairer.vo.ProductVO;



@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

	
	@Autowired
	IndexMapper mapper;
	
	
	@Override
	public List<List<ProductVO>> newprod() {
		
		List<List<ProductVO>> newList = new ArrayList<List<ProductVO>>();
		
		int cateMaxNum = 5;
		
		for(int i = 1; i < cateMaxNum; i++) {
			
			newList.add(mapper.newproduct(i));
		}
		
		return newList;
	}

}
