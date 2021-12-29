package  stu.inflp.flairer.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PaymentVO {
	private String pa_code;
	private String mcode;
	private String pcode;
	private int amount;
	private int active;
	private Timestamp paydate;
}
