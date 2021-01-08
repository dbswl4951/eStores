<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Register User</h2>
		<p class="lead">회원가입을 위한 정보를 입력하세요</p>
	
		<!-- submit 버튼이 눌리면 action의 url로 요청 보냄 -->
		<sf:form action="${pageContext.request.contextPath}/register"
			method="post" modelAttribute="user">
			
		<h3>기본 정보</h3>
		
		<div class="form-group">
			<!-- label for=""과 input의 id가 일치 해야 함 -->
			<label for="username">아이디</label>
			<!-- 회원가입 할려는 user가 이미 있는 username을 등록할 경우 (중복될 경우) 메세지 출력 -->
			<span style="color:#ff0000">${usernameMsg}</span>
			<sf:input path="username" id="username" class="form-control"/>
			<!-- class="form-control는 input의 넓이를 크게 주기 위함 (css) -->
			<sf:errors path="username" cssStyle="color:#ff0000;"/>
		</div>
		
		<div class="form-group">
			<!-- label for=""과 input의 id가 일치 해야 함 -->
			<label for="password">패스워드</label>
			<sf:password path="password" id="password" class="form-control"/>
			<!-- class="form-control는 input의 넓이를 크게 주기 위함 (css) -->
			<sf:errors path="password" cssStyle="color:#ff0000;"/>
		</div>
		
		<div class="form-group">
			<label for="email">이메일 주소</label><span style="color:#ff0000">${emailMsg}</span>
			<sf:input path="email" id="email" class="form-control"/>
			<sf:errors path="email" cssStyle="color:#ff0000;"/>
		</div>
		
		<br/>
		
		<h3>배송정보</h3>
		
		<div class="form-group">
			<label for="shippingStreet">주소</label>
			<!-- shippingAddress클래스안의  address 변수 -->
			<sf:input path="shippingAddress.address" id="shippingStreet" class="form-control"/>
		</div>
		
		<div class="form-group">
			<label for="shippingContry">국가</label>
			<sf:input path="shippingAddress.country" id="shippingContry" class="form-control"/>
		</div>
		
		<div class="form-group">
			<label for="shippingZip">우편번호</label>
			<sf:input path="shippingAddress.zipCode" id="shippingZip" class="form-control"/>
		</div>
		
		<br/>

		<input type="submit" value="submit" class="btn btn-default">
		<a href="<c:url value="/"/>" class="btn btn-default">Cancle</a>
		</sf:form>
		<br />
	</div>
</div>
