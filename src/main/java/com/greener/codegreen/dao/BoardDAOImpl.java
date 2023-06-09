package com.greener.codegreen.dao;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.greener.codegreen.dto.BoardDTO;




@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	
	@Inject  //의존관계주입
	private SqlSession sqlSession;
	
	private static String Namespace = "com.greener.codegreen.board";  
	

	
//----------------------------------------------------------------------------	
// 공지사항, FAQ 등록처리 
//----------------------------------------------------------------------------			
	
	//공지사항 작성
	@Override
	public int NoticeUpForm(BoardDTO boardDTO) {
		logger.info("NoticeUpForm( )  DAOimpl  ");	
		
		return sqlSession.insert(Namespace + ".BnoticeUpform" , boardDTO);
	}
	//FAQ 작성
	@Override
	public int FaqUpForm(BoardDTO boardDTO) {
		logger.info("FaqUpForm( )  DAOimpl  ");	
		
		return sqlSession.insert(Namespace + ".BfaqUpform" , boardDTO);
	}
	
	
	
	

//----------------------------------------------------------------------------	
// 공지사항, FAQ ,1:1 목록보기 
//----------------------------------------------------------------------------	
	
	//공지사항 전체목록 
	@Override
	public List<BoardDTO> NoticeList(int n_bc_code) throws Exception {
		
		logger.info("NoticeList( )  DAOimpl ");	
		
		List<BoardDTO> NoticeList =
				sqlSession.selectList(Namespace + ".Bnoticelist" , n_bc_code);
		return NoticeList;
	}
	
	//FAQ 전체목록
	public List<BoardDTO> FaqList(int f_bc_code) throws Exception {
		
		logger.info("FaqList( )  DAOimpl  ");	
		
		List<BoardDTO> FaqList =
				sqlSession.selectList(Namespace + ".Bfaqlist", f_bc_code);
		return FaqList;
	}
	//1:1 전체목록
	@Override
	public List<BoardDTO> InquiryList(int i_bc_code ) throws Exception {
		
		logger.info("InquiryList( )  DAOimpl  ");	
		
		List<BoardDTO> InquiryList =
				sqlSession.selectList(Namespace + ".Binquirylist" ,i_bc_code);
		return InquiryList;
	}

//----------------------------------------------------------------------------	
// 공지사항, FAQ ,1:1 상세보기 (조회) + 수정후 중복 처리도 같이
//----------------------------------------------------------------------------
	
	//----------------------------------------------------------------------------	
	// 게시물 번호에 해당하는 게시글의 조회수를 증가
	//----------------------------------------------------------------------------	
	@Override
	public void nupdateCount(int n_no) {
		
		logger.info("BoardDAOImpl 조회수 증가 ");
		sqlSession.update(Namespace + ".nupdateCount", n_no);
	}
	@Override
	public void fupdateCount(int f_no) {
		
		logger.info("BoardDAOImpl 조회수 증가 ");
		sqlSession.update(Namespace + ".fupdateCount", f_no);
	}

	
//----------------------------------------------------------------------------	
// 공지사항, FAQ ,1:1 게시글 번호에 해당하는 게시글 삭제하기 
//----------------------------------------------------------------------------		
	//----------------------------------------------------------------------------	
	// 게시물 번호에 해당하는 게시글 정보 가져오기 
	//----------------------------------------------------------------------------		
	
	//공지사항 조회(상세정보)
	@Override
	public BoardDTO NoticeDetail(int n_no)  {
		
		return sqlSession.selectOne(Namespace + ".BnoticeDetail", n_no);
	}
	//FAQ  조회(상세정보)
	@Override
	public BoardDTO FaqDetail(int f_no)  {
		
		return sqlSession.selectOne(Namespace + ".BfaqDetail", f_no);
	}
	//1:1 조회(상세정보)
	@Override
	public BoardDTO InquiryDetail(int i_no) {
		
		return sqlSession.selectOne(Namespace + ".BinquiryDetail", i_no);
	}
	
	//-------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기 
	//-------------------------------------------------------------------------
	
	@Override
	public int NoticeDelete(int n_no) {
		logger.info("BoardDAOImpl NoticeDelete ");
		return sqlSession.delete(Namespace + ".ndelete", n_no);
	}
	@Override
	public int FaqDelete(int f_no) {
		logger.info("BoardDAOImpl FaqDelete");
		return sqlSession.delete(Namespace + ".fdelete", f_no);
		
	}
	@Override
	public int InquiryDelete(int i_no) {
		logger.info("BoardDAOImpl InquiryDelete");
		return sqlSession.delete(Namespace + ".idelete", i_no);
		
	}

//----------------------------------------------------------------------------	
// 공지사항, FAQ ,1:1 게시글 번호에 해당하는 게시글 내용(넣어준 컬럼들) 수정
//----------------------------------------------------------------------------
	
	@Override
	public int NoticeUpdate(BoardDTO boardDTO) {
		
		logger.info("BoardDAOImpl 수정시작 ");
		return sqlSession.update(Namespace + ".nupdate" , boardDTO ); 
		
	}
	@Override
	public int FaqUpdate(BoardDTO boardDTO) {
		
		logger.info("BoardDAOImpl 수정시작 ");
		return sqlSession.update(Namespace + ".fupdate" , boardDTO ); 
		
	}
	@Override
	public int InquiryUpdate(BoardDTO boardDTO) {
		
		logger.info("BoardDAOImpl 수정시작 ");
		return sqlSession.update(Namespace + ".iupdate" , boardDTO ); 
		
	}

//----------------------------------------------------------------------------	
// 공지사항, FAQ ,1:1  페이징처리 - 뺌 
//----------------------------------------------------------------------------	
//----------------------------------------------------------------------------
// 카테고리에 따른 게시판 보여주기 
//----------------------------------------------------------------------------

	
}// public class ManagerDAOImpl implements ManagerDAO 
