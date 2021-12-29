package stu.inflp.flairer.service;

import java.util.List;

import stu.inflp.flairer.vo.ProductVO;



public interface ProductService {
	
	
	public List<ProductVO> prods(); // Products 에서받아온걸  가공하기위해 
	
	public List<ProductVO> getProds(String category); //category별로 받기 위해 가공 
	
	public ProductVO getdetailProducts(String pcode);
	
	// service 는 controller로 보내주는 역
	
	//검색
	public List<ProductVO> getsearchProduct(String name);
}
