package stu.inflp.flairer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stu.inflp.flairer.vo.ProductVO;

@Mapper
public interface ProductMapper {
	
	//쿼리 만들기 위해서  리스트형식으로 
	public List<ProductVO> products(); 
	
	// 카테고리별로 나눠서 하기 위함
	public List<ProductVO> selectProducts(int category); 
	
	//pcode 별로 상세 
	public ProductVO selectdetailProducts(String pcode); 
	
	//검색
	public List<ProductVO> searchProduct(String name);
}
//mapper가 DB에서 데이터를 받아오는 
//객체 지향의 5원칙  개방폐쇄의원칙(수정에는 폐쇄적 , 확장에선 개방적)