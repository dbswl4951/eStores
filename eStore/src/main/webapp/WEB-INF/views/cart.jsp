<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container-wrapper">
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h2>Cart</h2>
				<p>All the selected products in your shopping cart</p>
			</div>
		</div>
		
		<!-- cartApp 모듈 로드  : AngularJS 사용영역임을 표시 -->
		<section class="container" ng-app="cartApp">
			<!-- cartCtrl controller 생성 -->
			<!-- CartController에서 model로 넘겨 준 cartId사용 => initCartId('${cartId}') 호출 -->
			<!-- initCartId()가 $scope.refreshCart() 호출 => $scope.cart에 cart에 있는 item들 모두 담김 -->
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
				<a class="btn btn-warning pull-left" ng-click="clearCart()">
					<i class="fa fa-trash"></i> Clear Cart
				</a>
				
				<br/>
				
				<table class="table table-hover">
					<tr>
						<th>Product</th>
						<th>UnitInStock</th>
						<th>Quantity</th>
						<th>Total</th>
						<th>Action</th>
					</tr>
					
					<!-- ng-repeat : 반복문 (item의 갯수만 큼 루프 돔) -->
					<!-- cart.cartItems은 controller.js의 $scope.cart.cartItems -->
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td>
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						
						<td><a class="btn btn-danger" ng-click="removeFromCart(item.product.id)"> 
						<i class="fa fa-minus"></i>remove
						</a></td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
					</tr>
				</table>
				
				<a class="btn btn-info" href="<c:url value="/products"/>" class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>
</div>
