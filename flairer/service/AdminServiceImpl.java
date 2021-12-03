package stu.inflp.flairer.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import oracle.sql.TIMESTAMP;
import stu.inflp.flairer.mapper.AdminMapper;
import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.MemberVO;
import stu.inflp.flairer.vo.PaymentVO;
import stu.inflp.flairer.vo.ProductVO;


@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper mapper;
	
	@Override
	public List<?> getList(String tname, String cname, String cate) {
		List<?> result = null;
		switch(tname) {
		case "member":
			List<MemberVO> members = new ArrayList<MemberVO>();
			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
				MemberVO vo = new MemberVO();
						vo.setMcode((String)map.get("MCODE"));
						vo.setUserid((String)map.get("USERID"));
						vo.setPwd((String)map.get("PWD"));
						vo.setAddress((String)map.get("ADDRESS"));
						vo.setPhone((String)map.get("PHONE"));
						vo.setAdmin(((BigDecimal)map.get("ADMIN")).intValue());
						vo.setRegdate((Timestamp)map.get("REGDATE"));
						vo.setActive(((BigDecimal)map.get("ACTIVE")).intValue());
						vo.setGender(((BigDecimal)map.get("GENDER")).intValue());
						vo.setName((String)map.get("NAME"));
				members.add(vo);
			}
			result = members;
			break;
		case "product":
			List<ProductVO> products = new ArrayList<ProductVO>();
			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
				ProductVO vo = new ProductVO();
						vo.setPcode((String)map.get("PCODE"));
						vo.setName((String)map.get("NAME"));
						vo.setPrice(((BigDecimal)map.get("PRICE")).intValue());
						vo.setStock(((BigDecimal)map.get("STOCK")).intValue());
						vo.setCategory(((BigDecimal)map.get("CATEGORY")).intValue());
						vo.setPictureurl((String)map.get("PICTUREURL"));
						vo.setDes1((String)map.get("DES1"));
						vo.setDes2((String)map.get("DES2"));
						vo.setDes3((String)map.get("DES3"));
				products.add(vo);
			}
			result = products;
			break;
		case "board":
			List<BoardVO> boards = new ArrayList<BoardVO>();
			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
				BoardVO vo = new BoardVO();
						vo.setBcode(((BigDecimal)map.get("BCODE")).intValue());
						vo.setBid((String)map.get("BID"));
						vo.setBpwd((String)map.get("BPWD"));
						vo.setBtitle((String)map.get("BTITLE"));
						vo.setBcontent((String)map.get("BCONTENT"));
						vo.setBhit(((BigDecimal)map.get("BHIT")).intValue());
						vo.setBdate((Timestamp)map.get("BDATE"));
				boards.add(vo);
			}
			result = boards;
			break;
		case "payment":
			List<PaymentVO> payments = new ArrayList<PaymentVO>();
			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
				PaymentVO vo = new PaymentVO();
						
						vo.setPa_code((String)map.get("PA_CODE"));
						vo.setMcode((String)map.get("MCODE"));
						vo.setPcode((String)map.get("PCODE"));
						vo.setAmount(((BigDecimal)map.get("AMOUNT")).intValue());
						vo.setActive(((BigDecimal)map.get("ACTIVE")).intValue());
						try { vo.setPaydate(((TIMESTAMP)map.get("PAYDATE")).timestampValue()); } 
						catch (SQLException e) { e.printStackTrace(); }
				payments.add(vo);
			}
			result = payments;
			break;
		}
		
		return result;
	}
	@Override
	public List<MemberVO> getadminMember() {
		
		
		return mapper.adminMember();
	}

	@Override
	public List<ProductVO> getadminProduct(String category) {
		List<ProductVO> pVo = mapper.adminProduct(category);
		
		
		
		return mapper.adminProduct(category);
	}

	@Override
	public List<BoardVO> getadminBoard() {
		
		
		return mapper.adminBoard();
	}

	@Override
	public List<PaymentVO> getadminPayment() {
		
		
		return mapper.adminPayment();
	}
	 //수정,삭제 관련해서 	하나로 끝내기 
	@Override
	public void methodFactory(String name, String value, String type, String code, String _method) {
	      String codename = "";
	      
	      if(type.equals("member")) {
	         codename = "mcode";
	      } else if(type.equals("product")) {
	         codename = "pcode";
	      } else if(type.equals("board")) {
	         codename = "bcode";
	      } else if(type.equals("payment")) {
	         codename = "pa_code";
	      }
	      
	      if(_method.equals("put")) {
	         mapper.adminUpdate(type, codename, name, value, code);
	      } else if(_method.equals("delete")) {
	         mapper.adminDelete(type, codename, code);
	      }
	      
	   }
	@Override
	public String inserProduct(ProductVO pVo) {
			mapper.insertProduct(pVo);
			
		return mapper.getSeq();
	}
	
	

}
