package stu.inflp.flairer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.ReplyVO;

@Mapper
public interface BoardMapper {

	 //전체 게시판 조회
	public List<BoardVO> selectAllBoard();
	//패이징 및 리스트 조회
	public List<BoardVO> boardCount(@Param("page")String page, @Param("pageDataCount")String pageDataCount); 
	// 페이징 카운트
	public int boardPageCount();
	// 게시판 작성 
	public void insertBoard(BoardVO board); 
	// 게시판 상세페이지 
	public BoardVO selectOneBoard(int bcode);
	// 조회수증가 
	public void upHit(int bcode); 
	// 업데이트 
	public void modifyBoard(BoardVO board); 
	//수정 삭제시 비밀번호 체크
	public BoardVO checkpassWord(BoardVO board); 
	//삭제
	public void deleteBoard(BoardVO board);
	
	//댓글
	public List<ReplyVO> getBoardReply(int bcode);
	public ReplyVO selectOneBoardReply(int rcode);
	public void insertBoardReply(@Param("bcode") int bcode, @Param("rcontent") String rcontent, @Param("rbid") String rbid);
	
	public void modifyBoardReply(ReplyVO reply);
	
	public void deleteBoardReply(int rcode);
	
}
//파라미터 하나 하나 전부 입력해주거나,VO로 입력