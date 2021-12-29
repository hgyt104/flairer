package stu.inflp.flairer.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private String mcode;
	private String userid;
	private String pwd;
	private String address;
	private String phone;
	private int admin;
	private Date regdate;
	private int active;
	private int gender;
	private String name;
    
}
