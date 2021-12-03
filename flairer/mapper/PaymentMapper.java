package stu.inflp.flairer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import stu.inflp.flairer.vo.OrderVO;

@Mapper
public interface PaymentMapper {
	//상품 결제시 
	public List<OrderVO> getPayList(@Param("active") int active, @Param("mcode") String mcode, @Param("pcodes") List<String> pcodes); 
	}
