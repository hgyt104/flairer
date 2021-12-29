package stu.inflp.flairer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import stu.inflp.flairer.mapper.BoardMapper;
import stu.inflp.flairer.vo.BPageVO;
import stu.inflp.flairer.vo.BoardVO;
import stu.inflp.flairer.vo.ReplyVO;


@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<BoardVO> getselectBoard() {
		
		return mapper.selectAllBoard();
	}
	
	@Override // 페이징 전체 조회 	
	public List<BoardVO> getboardCount(String page ,String pageDataCount) {
		List<BoardVO> bVo =  mapper.boardCount(page, pageDataCount);
		
		
		return bVo;
	}
	@Override
	public BPageVO getboardPageCount(String page ,String pageDataCount) {
		
		int totalDataCount = mapper.boardPageCount();
	      BPageVO bpVo = new BPageVO();
	      bpVo.makePage(Integer.parseInt(page),
					Integer.parseInt(pageDataCount), totalDataCount);
	   	
		return bpVo;
	}
	
	
	@Override
	public void getinsertBoard(BoardVO board){
		
		mapper.insertBoard(board);
		
	}
	@Override
	public BoardVO selectOne(int bcode){
		
		BoardVO bVo = mapper.selectOneBoard(bcode);
		
		
		mapper.upHit(bcode);
		
		
		return bVo;
	}   
	@Override
	public void getmodifyBoard(BoardVO board) {
		
		mapper.modifyBoard(board);
		
	}
	
	public BoardVO getcheckWord(int bcode) {
	
		BoardVO boardVO = mapper.selectOneBoard(bcode);
	
		
		return boardVO;
	}
	@Override
	public void getdeleteBoard(BoardVO board) {
		
		mapper.deleteBoard(board);
	}
	
	//댓글
	@Override
	   public List<ReplyVO> getBoardReply(int bcode) {
	      List<ReplyVO> list = mapper.getBoardReply(bcode);
	      return list;
	   }
	
	//댓글 작성
	@Override
	 	public void getinsertBoardReply(int bcode, String rcontent, String rbid) {
	      mapper.insertBoardReply(bcode, rcontent, rbid);
	      
	   }
	//댓글 수정 	
	@Override
	public void getmodifyBoardReply(ReplyVO reply) {
		mapper.modifyBoardReply(reply);
	}
   //댓글 삭제
	public void getdeleteBoardReply(int rcode) {
		mapper.deleteBoardReply(rcode);
	}
	
	//댓글 선택 후 체크패스 
	@Override
	public ReplyVO getselectOneBoardReply(int rcode) {
		ReplyVO rVo = mapper.selectOneBoardReply(rcode);
		return rVo;
	}

	@Override
	public ReplyVO getreplyCheckPass(int rcode) {
		ReplyVO rVo = mapper.selectOneBoardReply(rcode);
		return rVo;
	}
	
	



}
