<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">

<!--index.html에 대한 상대적인 경로 -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/carousel.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">

</head>
<body>
	<div class="container-wrapper">
		<div class="container">
			<h2>Product Inventory</h2>
			<p>제품 제고 현황입니다.</p>
			<table class="table table-striped">
				<thead>
					<tr class="bg-warning">
						<th>Photo Thumb</th>
						<th>Name</th>
						<th>Category</th>
						<th>Price</th>
						<th>Menufacture</th>
						<th>UnitInStock</th>
						<th>Description</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${products}">
						<!-- 모델(products)에 있는 값을 가져오기 위함 -->
						<tr>
							<!-- JAVA 변수명과 동일 해야 함 -->
							<td>
								<img src="<c:url value="/resources/images/${product.imageFilename}"/>"
								alt="image" style="width:60%"/>
							</td>
							<td>${product.name}</td>
							<td>${product.category}</td>
							<td>${product.price}</td>
							<td>${product.menufacture}</td>
							<td>${product.unitInStock}</td>
							<td>${product.description}</td>
							<td><a href="<c:url value="/admin/productInventory/deleteProduct/${product.id}"/>">
								<i class="fa fa-times"></i></a>
								<a href="<c:url value="/admin/productInventory/updateProduct/${product.id}"/>">
								<i class="fa fa-edit"></i></a>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<a href="<c:url value="/admin/productInventory/addProduct"/>"class="btn btn-primary"> Add Product</a>
		</div>
	</div>

	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<!--상대경로 지정 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
</html>
