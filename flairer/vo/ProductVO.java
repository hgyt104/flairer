package  stu.inflp.flairer.vo;

import lombok.Data;

@Data
public class ProductVO {

	private String pcode;
	private String name;
	private int price;
	private int stock;
	private int category;
	private String pictureurl;
	private String des1;
	private String des2;
	private String des3;
}
