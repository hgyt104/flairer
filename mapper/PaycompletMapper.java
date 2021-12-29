package stu.inflp.flairer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaycompletMapper {
    //상품결제 완료시 
	public String paycomplet();
	
	//상품결제 완료 후
	public void uppaycomplet(@Param("pa_code") String pa_code, @Param("mcode") String mcode, @Param("pcodes") List<String> pcodes);
	
}
