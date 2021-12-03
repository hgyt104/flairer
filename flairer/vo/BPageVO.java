package  stu.inflp.flairer.vo;

import lombok.Data;

@Data
public class BPageVO {

	private int pageDataCount;
	private int currentPageNum;
	private int totalDataCount;
	private int pageSize;
	
	private int firstPageNum;
	private int lastPageNum;
	private int prevPageNum;
	private int nextPageNum;
	private int startPageNum;
	private int endPageNum;
	
	
	public String toSting() {
		return "BPageVO [pageDataCount = " + pageDataCount + ", currentPageNum=" + currentPageNum
				+ ", totalDataCount = " + totalDataCount + ", pageSize = " + pageSize
				+ ", firstPageNum = " + firstPageNum + ", lastPageNum = " + lastPageNum
				+ ", prevPageNum = " + prevPageNum + ", nextPageNum = " + nextPageNum
				+ ", startPageNum = " + startPageNum + ", endPageNum = " + endPageNum + "]";
	}
	
	public void makePage(int page, int pageDataCount, int totalDataCount) {
		
		if(totalDataCount==0) return;
		this.totalDataCount = totalDataCount;
		this.currentPageNum = page;
		this.pageDataCount = pageDataCount;
		
		this.pageSize = 10;
		
		this.firstPageNum = 1;
		this.lastPageNum = (totalDataCount-1)/pageDataCount+1;
		
		this.startPageNum = ((this.currentPageNum-1)/this.pageSize)*10+1;
		this.endPageNum = this.startPageNum+9;
		
		if(this.endPageNum>this.lastPageNum) {
			this.endPageNum = this.lastPageNum;
		}
		
		this.prevPageNum = this.startPageNum-this.pageSize;
		if(this.prevPageNum<1) {
			prevPageNum = 1;
		}
		
		this.nextPageNum = this.endPageNum + this.pageSize;
		if(this.nextPageNum > this.lastPageNum) {
			this.nextPageNum = this.lastPageNum;
		}
	}
}
