package  stu.inflp.flairer.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderVO {
	private String pa_code;
	private int amount;
	private int active;
	private Timestamp paydate;
	
	private String mcode;
	private String mname;
	private String address;
	private String phone;
	
	private String pcode;
	private String pname;
	private int price;
	private int stock;
	private String pictureurl;
	
//	public OrderVO() {
//		super();
//		this.pa_code = pa_code;
//		this.pictureurl = pictureurl;
//		this.pname = pname;
//		this.amount = amount;
//		this.price = price;
//		this.active = active;
//		this.paydate = paydate;
	}

