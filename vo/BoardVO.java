package stu.inflp.flairer.vo;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bcode;
	private String bid;
	private String bpwd;
	private String btitle;
	private String bcontent;
	private int bhit;
	private Timestamp bdate;
}
