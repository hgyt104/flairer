package stu.inflp.flairer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stu.inflp.flairer.vo.ProductVO;

@Mapper
public interface IndexMapper {

	//메인페이지 신상품
	public List<ProductVO> newproduct(int category);

	
	
}
