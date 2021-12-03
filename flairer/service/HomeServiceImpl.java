package stu.inflp.flairer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.HomeMapper;

//@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService {
	@Autowired
	HomeMapper mapper;
	
	@Override
	public List<Map<String, Object>> printMembers() {
		
		
		return mapper.getUser();
	}

}
