<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--메뉴바 (네비바)에 관련 돰-->
<header>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#">Carousel</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="<c:url value="/"/>">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/products"/>">Product</a>
				</li>
				
				<!-- 누군가가 로그인을 했으면 (name!=null이면) -->
				<c:if test="${pageContext.request.userPrincipal.name!=null}">
					<!-- 만약 로그인 한 사람이 admin이면 admin 페이지 보여 줌 -->
					<c:if test="${pageContext.request.userPrincipal.name=='admin'}">
						<li class="nav-item"><a class="nav-link" href="<c:url value="/admin"/>">AdminPage</a></li>
					</c:if>
					
					<!-- 일반 사용자일 경우에만 Cart 보여 줌 -->
					<c:if test="${pageContext.request.userPrincipal.name!='admin'}">
						<li class="nav-item"><a class="nav-link" href="<c:url value="/cart"/>">Cart</a></li>
					</c:if>
					
					<!-- 로그아웃 보여 줌 -->
					<li class="nav-item">
						<!-- javascript가 실행되면 자동적으로 id가 logout인 form이 실행되서 post방식으로 /logout을 컨트롤러에게 전달 해 준다 -->
						<a class="nav-link" href="javascript:document.getElementById('logout').submit()">Logout</a>
					</li>
					<form id="logout" action="<c:url value="/logout" />" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</c:if>
				
				<!-- 만약 로그인이 되어 있지 않다면 (name==null이면) -->
				<c:if test="${pageContext.request.userPrincipal.name==null}">
					<li class="nav-item"><a class="nav-link" href="<c:url value="/login"/>">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value="/register"/>">Register</a></li>
				</c:if>
			</ul>
			
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</header>
