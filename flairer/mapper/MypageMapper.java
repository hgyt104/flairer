package stu.inflp.flairer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import stu.inflp.flairer.vo.OrderVO;

@Mapper
public interface MypageMapper {
	
	//상품 리스트
	public List<OrderVO> myList(String mcode);
	
	//상품 출발,배송상태를 확인할수 있는 
	public Map<String, Object> payListCount(String mcode);
}
