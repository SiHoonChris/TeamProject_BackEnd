package com.greener.codegreen.controller;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.greener.codegreen.common.SearchCriteria;
import com.greener.codegreen.dto.BuyerDTO;

//-----------------------------------------------------------------------------------------------------------
// public interface BuyerController
//-----------------------------------------------------------------------------------------------------------
public interface BuyerController {
	
	// SiHoonChris(이시훈)
	//-----------------------------------------------------------------------------------------------------------
    // 로그인(vue.js에서 입력값 DB로 전송, 결과 조회)
	public BuyerDTO login (@RequestBody BuyerDTO buyerDTO) throws Exception;

	// 회원가입 화면으로 이동
	public ModelAndView signUp
	(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 약관 동의 페이지로 이동
	public ModelAndView toAgreementPage
	(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 정보 입력 페이지로 이동
	public ModelAndView toMemberInfoPage
	(HttpServletRequest request, HttpServletResponse response) throws Exception;	 

	// 회원가입 완료 페이지로 이동
	public ModelAndView signUpFinish
	(HttpServletRequest request, HttpServletResponse response) throws Exception;		
	//-----------------------------------------------------------------------------------------------------------
	
	
	// alsdn4498(김민준)
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사
	public int idCheck(BuyerDTO buyerDTO) throws Exception;
	
	// 회원가입 처리하기
	public int addBuyer
	(@ModelAttribute("buyer") BuyerDTO buyerDTO,HttpServletRequest request, HttpServletResponse response)
	throws Exception;
	
	// 소비자 리스트 조회
	public ModelAndView buyerList(SearchCriteria scri) throws Exception;
	
	// 소비자 상세 조회
	public String buyerDetail(Model model, HttpServletRequest request) throws Exception;
	//-----------------------------------------------------------------------------------------------------------
		
} // End - public interface BuyerController
