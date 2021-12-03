package stu.inflp.flairer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.ProductMapper;
import stu.inflp.flairer.vo.ProductVO;

//DB로 보내주기 위해서
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper mapper;
	
	@Override
	public List<ProductVO> prods() {
		
		return mapper.products();
	}
	
	
	public List<ProductVO> getProds(String category){
		
		int p = 0;
		
		switch(category) {
		case "man":
			p = 1;
			break;
		case "woman":
			p = 2;
			break; 
		case "defuser":
			p = 3;
			break;
		case "candle":
			p = 4;
			break;
		}
		return mapper.selectProducts(p);
	}


	@Override
	public ProductVO getdetailProducts(String pcode) {
		ProductVO pVo  = mapper.selectdetailProducts(pcode);

		mapper.selectdetailProducts(pcode);
		

		return pVo;
	}

	//검색
	@Override
	public List<ProductVO> getsearchProduct(String name) {
		List<ProductVO> listpVo = mapper.searchProduct(name);
	

		
		return listpVo;
	}
	

}
