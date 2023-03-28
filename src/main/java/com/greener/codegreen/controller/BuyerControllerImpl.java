package com.greener.codegreen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greener.codegreen.common.PageMaker;
import com.greener.codegreen.common.SearchCriteria;
import com.greener.codegreen.dto.BuyerDTO;
import com.greener.codegreen.dto.OAuthToken;
import com.greener.codegreen.service.BuyerService;

//-----------------------------------------------------------------------------------------------------------
// 회원 정보 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller("buyerController")
@RequestMapping("/buyer")
public class BuyerControllerImpl implements BuyerController {
	private static final Logger logger = LoggerFactory.getLogger(BuyerControllerImpl.class);
	@Autowired
	private	BuyerDTO	 buyerDTO;
	@Autowired
	private	BuyerService buyerService;
	
	// SiHoonChris(이시훈)
	//-----------------------------------------------------------------------------------------------------------
	// 카카오 API 로그인
	@GetMapping(value="/KAKAOlogin")
	public @ResponseBody String KakaoCallback(String code) {
		
		// 발급된 인가코드로 Access_Token 생성
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		params.add("redirect_uri", "http://localhost:8086/buyer/KAKAOlogin");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		
		// 생성된 토큰으로 사용자 정보 추출
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch(JsonMappingException e) {
			e.printStackTrace();
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = new HttpEntity<>(headers2);
		
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoTokenRequest2,
			String.class
		);
		
		System.out.println(response2.getBody());
		
		return response2.getBody();
	} // KakaoCallback()
	
    // 로그인(vue.js에서 입력값 DB로 전송, 결과 조회)
	@PostMapping(value="/login")
	@CrossOrigin(origins="http://localhost:8080")
	@ResponseBody
	@Override
	public BuyerDTO login(@RequestBody BuyerDTO buyerIdPwd) throws Exception {
		BuyerDTO buyerInfo = buyerService.login(buyerIdPwd);
		return buyerInfo;
	} // login()
	
	// 회원가입 화면으로 이동
	@Override
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/buyer/signUp1Start");
		return mav;
	} // singIn()

	// 약관 동의 페이지로 이동
	@Override
	@RequestMapping(value="/Agreement", method=RequestMethod.GET)
	public ModelAndView toAgreementPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/buyer/signUp2Agree");
		return mav;
	} // toAgreementPage()
	
	// 정보 입력 페이지로 이동
	@Override
	@RequestMapping(value="/Info", method=RequestMethod.GET)
	public ModelAndView toMemberInfoPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/buyer/signUp3Information");
		return mav;
	} // toMemberInfoPage()
	
	// 회원가입 완료 페이지로 이동
	@Override
	@RequestMapping(value="/done", method=RequestMethod.POST)
	public ModelAndView signUpFinish(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/buyer/signUp4Finish");
		return mav;
	} // singInFinish()
	//-----------------------------------------------------------------------------------------------------------
	
	
	
	// alsdn4498(김민준)
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사
	@Override
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(BuyerDTO buyerDTO) throws Exception {
		logger.info("MemberControllerImpl �븘�씠�뵒 以묐났 寃��궗 (Ajax) �떆�옉");
		int result = buyerService.idCheck(buyerDTO);
		logger.info("MemberControllerImpl �븘�씠�뵒 以묐났 寃��궗 (Ajax) �썑 諛쏆� 媛� : " + result);
		return result;
	} // idCheck()
	
	// 회원가입 처리하기
	@Override
	@RequestMapping(value = "/addBuyer", method = RequestMethod.POST)
	public int addBuyer(BuyerDTO buyerDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("MemberControllerImpl �쉶�썝媛��엯 泥섎━�븯湲�() �떆�옉");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		return buyerService.addBuyer(buyerDTO); // �뜲�씠�꽣 泥섎━ �셿猷� 嫄댁닔瑜� ���옣�븷 蹂��닔
	} // addBuyer()
	
	// 소비자 리스트 조회
	@Override
	@RequestMapping(value = "/buyerList", method = RequestMethod.GET)
	public ModelAndView buyerList(SearchCriteria scri) throws Exception {
		
		ModelAndView mav = new ModelAndView("/buyer/buyerList");
		mav.addObject("keyword",scri.getKeyword());
		mav.addObject("searchType",scri.getSearchType());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(buyerService.totalCount(scri));
		
		List<BuyerDTO> buyerList = buyerService.buyerList(scri);
		
		mav.addObject("buyerList",buyerList);
		mav.addObject("pageMaker",pageMaker);
		
		return mav;
	} // buyerList()

	// 소비자 상세 조회
	@RequestMapping(value ="/buyerDetail", method = RequestMethod.GET)
	@Override
	public String buyerDetail(Model model, HttpServletRequest request) throws Exception {
		String b_id = 	request.getParameter("b_id");
		logger.info("b_id =>"+b_id);
		int flag = Integer.parseInt((String)request.getParameter("flag"));
		
		BuyerDTO buyerDTO = buyerService.buyerDetail(b_id,flag);
		model.addAttribute("buyerDetail", buyerDTO);
		
		return "/buyer/buyerDetail";
	} // buyerDetail()
	//-----------------------------------------------------------------------------------------------------------
	
	
} // End - public class BuyerControllerImpl
