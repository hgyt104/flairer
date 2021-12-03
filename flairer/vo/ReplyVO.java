package stu.inflp.flairer.vo;
import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
   
   private int bcode;
   private int rcode;
   private String rcontent;
   private String rbid;
   private Date regdate;

}
