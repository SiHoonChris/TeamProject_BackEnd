package com.greener.codegreen.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.greener.codegreen.common.SearchCriteria;
import com.greener.codegreen.dto.BuyerDTO;

//-----------------------------------------------------------------------------------------------------------
//	회원 정보 서비스
//-----------------------------------------------------------------------------------------------------------
public interface BuyerDAO {

	// SiHoonChris(이시훈)
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	public BuyerDTO loginByIdPwd(BuyerDTO buyerDTO) throws DataAccessException;
	//-----------------------------------------------------------------------------------------------------------
	
	
	// alsdn4498(김민준)
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사
	public int idCheck(BuyerDTO buyerDTO) throws DataAccessException;
	
	// 회원가입 처리하기
	public int addBuyer(BuyerDTO buyerDTO) throws DataAccessException;
	
	// 소비자 계정 총 개수 조회
	public int totalCount(SearchCriteria scri) throws DataAccessException;

	// 소비자 리스트 조회
	public List<BuyerDTO> buyerList(SearchCriteria scri) throws DataAccessException;

	// 소비자 상세 조회
	public BuyerDTO buyerDetail(String b_id) throws Exception;
	//-----------------------------------------------------------------------------------------------------------

	
} // End - public interface BuyerDAO
