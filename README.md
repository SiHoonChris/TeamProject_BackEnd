# TeamProject_BackEnd  

## Description  
1) 학원 교육과정 수강 중 진행한 팀 프로젝트(23.02.10 ~ 23.03.20)에서   
제가 작업한 내용들에 대해 올려 놓은 공간입니다. (팀 작업물 : https://github.com/DaEunPark/Greener)  
2) 이 Repo에서는 Spring MVC 기반의 백엔드 작업물과 JSP, jQuery 등을 활용한 프론트엔드 작업물을 볼 수 있습니다.  
3) https://github.com/SiHoonChris/TeamProject_FrontEnd 와 동일한 프로젝트입니다.  

## Environment & Tech Stack  
1) Environment  
Spring Tool Suite 3  
Apache Tomcat 9.0  
MySQL 8.0.31 - AWS(DB)  
2) Tech Stack  
Java(SE11)  
Spring Legacy  
SQL(MySQL)  
JSP, CSS3, JavaScript(jQuery)  

## Works - Functions  
1) 로그인  
2) 카카오 API 로그인 (작업 중)  
3) 회원가입 (페이지 이동 : 회원구분 - 약관동의 - 회원정보 - 가입완료)  
4) 회원가입 (회원구분 : 구매 회원, 개인/사업자 판매 회원 선택)  
5) 회원가입 (약관동의 : 전체 동의 버튼, 필수 항목 선택 여부에 따라 다음 페이지 이동 버튼 활성화)  
6) 회원가입 (회원정보 : 회원정보 입력값 유효성 검사 - 입력 조건 일치 여부 확인)  
7) 회원가입 (가입완료 : 입력된 회원정보 DB에 저장)  
8) 인기 상품 추출 (구매량 기준 상위 10개 품목 & 각 상품에 대한 대분류 카테고리)  

## Works - Files  
1) signUp1Start.jsp    
2) signUp2Agree.jsp  
3) signUp3Information.jsp    
4) signUp4Finish.jsp  
5) cSellSignUp1Verify.jsp    
6) pSellSignUp1Verify.jsp  
7) AdminController.java  
8) AdminService.java  
9) AdminServiceImpl.java  
10) AdminDAO.java  
11) AdminDAOImpl.java  
12) ProductDTO.java  
13) adminMapper.xml  
14) BuyerController.java  
15) BuyerControllerImpl.java  
16) BuyerService.java  
17) BuyerServiceImpl.java  
18) BuyerDAO.java  
19) BuyerDAOImpl.java  
20) BuyerDTO.java  
21) buyerMapper.xml  

## etc.  
1) Works - Files에서 3, 14~21은 김민준님(@alsdn4498)과 공동으로 작업했습니다.  
작업 내용의 구분은 주석으로 명시했습니다.  
2) Works - Functions에서 2(카카오 API를 활용한 로그인)는 팀 프로젝트 내용에 반영되지 않았습니다.  
