package stu.inflp.flairer.service;

import java.util.List;

import stu.inflp.flairer.vo.BPageVO;
import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.ReplyVO;



public interface BoardService {

	public List<BoardVO> getselectBoard(); // 전체게시판
	
	public List<BoardVO> getboardCount(String page, String pageDataCount); //페이징 및 리스트 조회
	public BPageVO getboardPageCount(String page, String pageDataCount);
	
	public void getinsertBoard(BoardVO board); //작성 
	public BoardVO selectOne(int bcode); // 게시판 선택
	public void getmodifyBoard(BoardVO board); // 게시판 수정 	
	public BoardVO getcheckWord(int bcode);
	public void getdeleteBoard(BoardVO board);
	
	//댓글
	public List<ReplyVO> getBoardReply(int bcode);
	
	public void getinsertBoardReply(int bcode, String rcontent, String rbid);
	
	public ReplyVO getselectOneBoardReply(int rcode);
	public ReplyVO getreplyCheckPass(int rcode);
	
	public void getmodifyBoardReply(ReplyVO reply);
	
	public void getdeleteBoardReply(int rcode);
}

