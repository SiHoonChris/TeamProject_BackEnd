package com.greener.codegreen.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.greener.codegreen.common.SearchCriteria;
import com.greener.codegreen.dao.BuyerDAO;
import com.greener.codegreen.dto.BuyerDTO;

//-----------------------------------------------------------------------------------------------------------
// 회원 정보 서비스
//-----------------------------------------------------------------------------------------------------------
@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {
	private static final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
	@Autowired
	private BuyerDAO buyerDAO;

	// SiHoonChris(이시훈)
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	@Override
	public BuyerDTO login(BuyerDTO buyerIdPwd) throws DataAccessException {
		return buyerDAO.loginByIdPwd(buyerIdPwd);
	} // login()
	//-----------------------------------------------------------------------------------------------------------
	
	
	// alsdn4498(김민준)
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사
	@Override
	public int idCheck(BuyerDTO buyerDTO) throws Exception {
		int result = buyerDAO.idCheck(buyerDTO);
		return result;
	} // idCheck()
	
	// 회원가입 처리하기
	@Override
	public int addBuyer(BuyerDTO buyerDTO) throws DataAccessException {
		return buyerDAO.addBuyer(buyerDTO);
	} // addBuyer()

	// 소비자 계정 총 개수 조회
	@Override
	public int totalCount(SearchCriteria scri) throws Exception {		
		return buyerDAO.totalCount(scri);
	} // totalCount()
	
	// 소비자 리스트 조회
	@Override
	public List<BuyerDTO> buyerList(SearchCriteria scri) throws Exception {		
		return buyerDAO.buyerList(scri);
	} // buyerList()
	
	// 소비자 상세 조회
	@Override
	public BuyerDTO buyerDetail(String b_id , int flag) throws Exception {
		return buyerDAO.buyerDetail(b_id);
	} // buyerDetail()
	//-----------------------------------------------------------------------------------------------------------
	
} // End - public class BuyerServiceImpl
