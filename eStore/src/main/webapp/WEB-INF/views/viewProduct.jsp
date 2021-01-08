<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container-wrapper">
	<div class="container" ng-app="cartApp">
		<h2>Product Detail</h2>
		<p class="lead">Here is the detail information of the product</p>
	
		<div class="row" ng-controller="cartCtrl">
			<!-- 12개의 칸을 6개 6개로 분할 -->
			<!-- md(medium) : 768px 이상일 때 반반 나눠짐 -->
			<!-- sm(small) : 576px이상일때 적용 => 작아지면 쌓이는 형태로 변경 됨 -->
			<div class="col-md-6">
				<img src="<c:url value="/resources/images/${product.imageFilename}"/>" 
					alt="image" style="width:80%"/>
			</div>
			
			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p><strong>Description : </strong>${product.description}</p>
				<p><strong>Menufacture : </strong>${product.menufacture}</p>
				<p><strong>Category : </strong>${product.category}</p>
				<p><strong>Price : </strong>${product.price}</p>
				
				<br/>
				
				<!-- 로그인을 했을 경우 -->
				<c:if test="${pageContext.request.userPrincipal.name!=null}">
					<p>
						<a href="<c:url value="/products"/>" class="btn btn-danger">Back</a>
						<!-- ProductController의 viewProduct메소드에서 product를 model을 사용해 넘겨 주므로, ${product.id}사용 가능 -->
						<button class="btn btn-warning btn-large" ng-click="addToCart('${product.id}')">
							<i class="fa fa-shopping-cart"></i>Order Now
						</button>
					
						<!-- cart 조회 -->
						<a href="<c:url value="/cart"/>" class="btn btn-info">
							<i class="fa fa-eye"></i>View Cart
						</a>
					</p>
				</c:if>	
			</div>
		</div>
	</div>
</div>