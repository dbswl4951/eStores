<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container-wrapper">
	<div class="container">
		<h2>Add Products</h2>
		<p class="lead">Fill the below information to add a product:</p>
	
		<!-- submit 버튼이 눌리면 action의 url로 요청 보냄 -->
		<sf:form action="${pageContext.request.contextPath}/admin/productInventory/addProduct?${_csrf.parameterName}=${_csrf.token}"
			method="post" modelAttribute="product" enctype="multipart/form-data">
		
			<div class="form-group">
				<!-- label for=""과 input의 id가 일치 해야 함 -->
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control"/>
				<!-- class="form-control는 input의 넓이를 크게 주기 위함 (css) -->
				<sf:errors path="name" cssStyle="color:#ff0000;"/>
			</div>
			
			<div class="form-group">
				<label for="category">Category:</label>
				<sf:radiobutton path="category" id="category" value="컴퓨터"/>컴퓨터
				<sf:radiobutton path="category" id="category" value="가전"/>가전
				<sf:radiobutton path="category" id="category" value="잡화"/>잡화
			</div>
			
			<div class="form-group">
				<label for="description">Description</label>
				<sf:textarea path="description" id="description" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="price">Price</label>
				<sf:input path="price" id="price" class="form-control"/>
				<sf:errors path="price" cssStyle="color:#ff0000;"/>
			</div>
			
			<div class="form-group">
				<label for="unitInStock">Unit In Stock</label>
				<sf:input path="unitInStock" id="unitInStock" class="form-control"/>
				<sf:errors path="unitInStock" cssStyle="color:#ff0000;"/>
			</div>
			
			<div class="form-group">
				<label for="menufacture">Manufacture</label>
				<sf:input path="menufacture" id="menufacture" class="form-control"/>
				<sf:errors path="menufacture" cssStyle="color:#ff0000;"/>
			</div>
			
			<div class="form-group">
				<label for="productImage">Upload Picture</label>
				<sf:input path="productImage" id="productImage" type="file" class="form-control"/>
			</div>
	
			<input type="submit" value="submit" class="btn btn-default">
			<a href="<c:url value="/admin/productInventory"/>" class="btn btn-default">Cancle</a>
		</sf:form>
		<br />
	</div>
</div>
