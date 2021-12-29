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

//
//@Service
//@AllArgsConstructor
//public class AdminServiceImpl implements AdminService {
//
//	AdminMapper mapper;
//	
//	public List<?> getList(String param_target) {
//
//		int _category_number = 4;
//
//		String _table_name = "member";
//		String _code_name = "mcode";
//		String _category_name = null;
//
//		List<?> _result = null;
//
//		switch(param_target) {
//
//			case "member":
//				List<MemberVO> _members = new ArrayList<MemberVO>();
//				for(Map<String, Object> _map : mapper.getList(_table_name, _code_name, _category_name)) {
//					MemberVO _vo = new MemberVO();
//					_vo.setMcode((String)_map.get("MCODE"));
//					_vo.setUserid((String)_map.get("USERID"));
//					_vo.setPwd((String)_map.get("PWD"));
//					_vo.setAddress((String)_map.get("ADDRESS"));
//					_vo.setPhone((String)_map.get("PHONE"));
//					_vo.setAdmin(((BigDecimal)_map.get("ADMIN")).intValue());
//					_vo.setRegdate((Timestamp)_map.get("REGDATE"));
//					_vo.setActive(((BigDecimal)_map.get("ACTIVE")).intValue());
//					_vo.setGender(((BigDecimal)_map.get("GENDER")).intValue());
//					_vo.setName((String)_map.get("NAME"));
//					_members.add(_vo);
//				}
//				_result = _members;
//				break;
//
//			case "man":
//				_category_number--;
//			case "woman":
//				_category_number--;
//			case "defuser":
//				_category_number--;
//			case "candle":
//				_table_name = "product";
//				_code_name = "pcode";
//				_category_name = Integer.toString(_category_number);
//
//				List<ProductVO> _products = new ArrayList<ProductVO>();
//				for(Map<String, Object> _map : mapper.getList(_table_name, _code_name, _category_name)) {
//					ProductVO _vo = new ProductVO();
//					_vo.setPcode((String)_map.get("PCODE"));
//					_vo.setName((String)_map.get("NAME"));
//					_vo.setPrice(((BigDecimal)_map.get("PRICE")).intValue());
//					_vo.setStock(((BigDecimal)_map.get("STOCK")).intValue());
//					_vo.setCategory(((BigDecimal)_map.get("CATEGORY")).intValue());
//					_vo.setPictureURL((String)_map.get("PICTUREURL"));
//					_vo.setDes1((String)_map.get("DES1"));
//					_vo.setDes2((String)_map.get("DES2"));
//					_vo.setDes3((String)_map.get("DES3"));
//					_products.add(_vo);
//				}
//				_result = _products;
//				break;
//
//			case "board":
//				_table_name = param_target;
//				_code_name = "bcode";
//
//				List<BoardVO> _boards = new ArrayList<BoardVO>();
//				for(Map<String, Object> _map : mapper.getList(_table_name, _code_name, _category_name)) {
//					BoardVO _vo = new BoardVO();
//					_vo.setBcode(((BigDecimal)_map.get("BCODE")).intValue());
//					_vo.setBid((String)_map.get("BID"));
//					_vo.setBpwd((String)_map.get("BPWD"));
//					_vo.setBtitle((String)_map.get("BTITLE"));
//					_vo.setBcontent((String)_map.get("BCONTENT"));
//					_vo.setBhit(((BigDecimal)_map.get("BHIT")).intValue());
//					_vo.setBdate((Timestamp)_map.get("BDATE"));
//					_boards.add(_vo);
//				}
//				_result = _boards;
//				break;
//				
//			case "payment":
//				_table_name = param_target;
//				_code_name = "bcode";
//				
//				List<PaymentVO> _payments = new ArrayList<PaymentVO>();
//				for(Map<String, Object> _map : mapper.getList(_table_name, _code_name, _category_name)) {
//					PaymentVO _vo = new PaymentVO();
//					_vo.setPa_Code((String)_map.get("PA_CODE"));
//					_vo.setMcode((String)_map.get("MCODE"));
//					_vo.setPcode((String)_map.get("PCODE"));
//					_vo.setAmount(((BigDecimal)_map.get("AMOUNT")).intValue());
//					_vo.setActive(((BigDecimal)_map.get("ACTIVE")).intValue());
//						try { _vo.setPaydate(((TIMESTAMP)_map.get("PAYDATE")).timestampValue()); } 
//						catch (SQLException e) { e.printStackTrace(); }
//				_payments.add(_vo);
//				}
//				_result = _payments;
//				break;
//		}
//		
//		return _result;
//	}
//
//	@Override
//	public List<?> getList(String tname, String cname, String cate) {
//		List<?> result = null;
//		switch(tname) {
//		case "member":
//			List<MemberVO> members = new ArrayList<MemberVO>();
//			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
//				MemberVO vo = new MemberVO();
//						vo.setMcode((String)map.get("MCODE"));
//						vo.setUserid((String)map.get("USERID"));
//						vo.setPwd((String)map.get("PWD"));
//						vo.setAddress((String)map.get("ADDRESS"));
//						vo.setPhone((String)map.get("PHONE"));
//						vo.setAdmin(((BigDecimal)map.get("ADMIN")).intValue());
//						vo.setRegdate((Timestamp)map.get("REGDATE"));
//						vo.setActive(((BigDecimal)map.get("ACTIVE")).intValue());
//						vo.setGender(((BigDecimal)map.get("GENDER")).intValue());
//						vo.setName((String)map.get("NAME"));
//				members.add(vo);
//			}
//			result = members;
//			break;
//		case "product":
//			List<ProductVO> products = new ArrayList<ProductVO>();
//			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
//				ProductVO vo = new ProductVO();
//						vo.setPcode((String)map.get("PCODE"));
//						vo.setName((String)map.get("NAME"));
//						vo.setPrice(((BigDecimal)map.get("PRICE")).intValue());
//						vo.setStock(((BigDecimal)map.get("STOCK")).intValue());
//						vo.setCategory(((BigDecimal)map.get("CATEGORY")).intValue());
//						vo.setPictureURL((String)map.get("PICTUREURL"));
//						vo.setDes1((String)map.get("DES1"));
//						vo.setDes2((String)map.get("DES2"));
//						vo.setDes3((String)map.get("DES3"));
//				products.add(vo);
//			}
//			result = products;
//			break;
//		case "board":
//			List<BoardVO> boards = new ArrayList<BoardVO>();
//			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
//				BoardVO vo = new BoardVO();
//						vo.setBcode(((BigDecimal)map.get("BCODE")).intValue());
//						vo.setBid((String)map.get("BID"));
//						vo.setBpwd((String)map.get("BPWD"));
//						vo.setBtitle((String)map.get("BTITLE"));
//						vo.setBcontent((String)map.get("BCONTENT"));
//						vo.setBhit(((BigDecimal)map.get("BHIT")).intValue());
//						vo.setBdate((Timestamp)map.get("BDATE"));
//				boards.add(vo);
//			}
//			result = boards;
//			break;
//		case "payment":
//			List<PaymentVO> payments = new ArrayList<PaymentVO>();
//			for(Map<String, Object> map : mapper.getList(tname, cname, cate)) {
//				PaymentVO vo = new PaymentVO();
//						vo.setPa_Code((String)map.get("PA_CODE"));
//						vo.setMcode((String)map.get("MCODE"));
//						vo.setPcode((String)map.get("PCODE"));
//						vo.setAmount(((BigDecimal)map.get("AMOUNT")).intValue());
//						vo.setActive(((BigDecimal)map.get("ACTIVE")).intValue());
//						try { vo.setPaydate(((TIMESTAMP)map.get("PAYDATE")).timestampValue()); } 
//						catch (SQLException e) { e.printStackTrace(); }
//				payments.add(vo);
//			}
//			result = payments;
//			break;
//		}
//		
//		return result;
//	}
//	
//	@Override
//	public List<MemberVO> getMemberList() {
//		List<MemberVO> list = mapper.getMemberList();
//		return list;
//	}
//	
//	@Override
//	public List<ProductVO> getProductList(String category) {
//		List<ProductVO> list = mapper.getProductList(category);
//		return list;
//	}
//	
//	
//	@Override
//	public String insertProduct(ProductVO pv) {
//		mapper.insertProduct(pv);
//		return mapper.getSeq();
//	}
//
//	@Override
//	public List<BoardVO> getBoardList() {
//		List<BoardVO> list = mapper.getBoardList();
//		return list;
//	}
//
//
//	@Override
//	public List<PaymentVO> getPaymentList() {
//		List<PaymentVO> list = mapper.getPaymentList();
//		return list;
//	}
//	public void methodFactory(String name, String value, String type, String code, String _method) {
//		String codename = "";
//		
//		if(type.equals("member")) {
//			codename = "mcode";
//		} else if(type.equals("product")) {
//			codename = "pcode";
//		} else if(type.equals("board")) {
//			codename = "bcode";
//		} else if(type.equals("payment")) {
//			codename = "pa_code";
//		}
//		
//		if(_method.equals("put")) {
//			mapper.adminUpdate(type, codename, name, value, code);
//		} else if(_method.equals("delete")) {
//			mapper.adminDelete(type, codename, code);
//		}
//		
//	}
//
//
//}
