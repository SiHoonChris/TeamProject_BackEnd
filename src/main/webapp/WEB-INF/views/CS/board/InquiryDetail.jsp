<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-- 시간 기호 관련 처리 -->
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>1:1 문의 상세보기</title>
	<!--  부트스트랩 5.3 적용 -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
   <link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<!-- 제이쿼리 넣어주기 -->  
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   <!-- 부트스트랩 사이드바 css  -->
   <link href="../../resources/css/admin/bootstrap.min.css" rel="stylesheet">
	<script src="${contextPath}/resources/js/Eboard.js"></script>
	
</head>

<body>
<!-- 상단 사이드 바  -->
<div class="container">
	<form class="form-horizontal" id="frm">
		<div class="form-group">
			<div>
				<h2 align="center">1:1 문의 상세보기</h2>
			</div>
		</div>
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">제  목</label>
			<div class="col-sm-10">
				<input type="hidden" id="i_no" name="i_no" value='${InquiryDetail.i_no}'></input>
				<input type="text" class="form-control" id="i_title" name="i_title" maxlength="200" value="${InquiryDetail.i_title}" readonly/>
			</div>
		</div>
		<div class="form-group">
			<label for="type" class="col-sm-2 control-label">카테고리</label>
			<div class="col-sm-10">
			 <c:choose>
			 	<c:when test="${InquiryDetail.i_bc_code eq 14}">
				<input type="text" class="form-control" id="i_bc_code" name="i_bc_code" maxlength="200" value="회원/멤버십" readonly/>
				</c:when>
				<c:when test="${InquiryDetail.i_bc_code eq 15}">
				<input type="text" class="form-control" id="i_bc_code" name="i_bc_code" maxlength="200" value="주문/결제" readonly/>
				</c:when>
				<c:when test="${InquiryDetail.i_bc_code eq 16}">
				<input type="text" class="form-control" id="i_bc_code" name="i_bc_code" maxlength="200" value="배송" readonly/>
				</c:when>
				<c:when test="${InquiryDetail.i_bc_code eq 17}">
				<input type="text" class="form-control" id="i_bc_code" name="i_bc_code" maxlength="200" value="상품" readonly/>
				</c:when>
				<c:otherwise>
				<input type="text" class="form-control" id="i_bc_code" name="i_bc_code" maxlength="200" value="이벤트" readonly/>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="form-group">
			<label for="reg_date" class="col-sm-2 control-label">작성일자</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="i_regdate" name="i_regdate" value="<fmt:formatDate value='${InquiryDetail.i_regdate}' pattern='yyyy년 MM월 dd일 a hh시 mm분 ss초'/>" readonly/>
			</div>
		</div>
		<div class="form-group">
			<label for="writer" class="col-sm-2 control-label">작성자</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="i_b_id" name="i_b_id" maxlength="20" value="${InquiryDetail.i_b_id}" readonly/>
			</div>
		</div>
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">내  용</label>
			<div class="col-sm-10">
				<textarea rows="10" cols="160" class="form-control" id="i_content" name="i_content" readonly>${InquiryDetail.i_content}</textarea>
			</div>
		</div>
		
		
		<div class="form-group">
			<p align="center">
				<button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/CS/board/InquiryList?i_bc_code=0'">
					<span class="glyphicon glyphicon-list-alt"> 게시글 목록</span>
				</button>
				<button type="button" class="btn btn-warning" onclick="fn_InquiryUpdateForm(${InquiryDetail.i_no})">
					<span class="glyphicon glyphicon-pencil"> 답변등록 및 확인</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="fn_InquiryDelete(${InquiryDetail.i_no})">
					<span class="glyphicon glyphicon-erase"> 게시글 삭제</span>
				</button>
			</p>
		</div>
		
		
	</form>
</div>	
	


</body>
</html>