// cartApp 모듈 정의
var cartApp = angular.module('cartApp', []);

// 모듈 안에 cartCtrl 컨트롤러 정의 
cartApp.controller("cartCtrl", function($scope,$http){ //$scope(model), $http(service) 의존성 주입에 의해 주입 됨
	// 여기 쭉 생성자
	
	// scope에 property, method 있을 수 있음
	// method 등록
	$scope.initCartId = function(cartId){ // view에서 받은 cartId
		$scope.cartId = cartId;
		$scope.refreshCart();
	};
	
	// cart 내용 조회
	$scope.refreshCart = function(){
		// $http를 사용해 서버에 접근
		// CartRestController의 getCartById메소드 호출
		$http.get('/eStore/api/cart/'+$scope.cartId).then( // 성공 할 경우 아래 함수 호출
				function successCallback(response){ // controller가 넘겨 준 ResponseEntity<Cart> 객체 response에 받음
					$scope.cart = response.data; // $scope.cart에 cart 저장
				});
	};
	
	// cart안의 아이템 모두 삭제
	$scope.clearCart = function(){
		$scope.setCsrfToken();
		/*
		$http.delete('/eStrore/api/cart/'+$scope.cartId).then(
				function successCallback(){ // 서버에 삭제 요청 성공 했을 경우
					$scope.refreshCart(); // 삭제 후 cart 조회
				},function errorCallback(response){
					console.log(response.data);
				});
		*/
		
		// CartRestController의 clearCart메소드 호출
		$http({ // $scope.refreshCart 메소드의 $http.get(...)대신 이렇게 쓸 수 있음
			method : 'DELETE',
			url : '/eStrore/api/cart/'+$scope.cartId
		}).then(function successCallback(){ // 서버에 삭제 요청 성공 했을 경우
			$scope.refreshCart(); // 삭제 후 cart 조회
		},function errorCallback(response){
			console.log(response.data);
		});
	};
	
	// item cart에 추가
	$scope.addToCart = function(productId){
		$scope.setCsrfToken();
		
		// put : update
		// CartRestController의 addItem메소드 호출
		$http.put('/eStore/api/cart/add/'+productId).then(
				function successCallback(){
					alert("Product successfully added to the cart!");
				},function errorCallback(){
					alert("Adding to the cart failed!")
				});
	};
	
	// item cart에서 제거
	$scope.removeFromCart = function(productId){
		$scope.setCsrfToken();
		
		// CartRestController의 removeItem메소드 호출
		$http({
			method:'DELETE',
			url:'/eStore/api/cart/cartitem/'+productId
		}).then(function successCallback(){
			$scope.refreshCart();
		},function errorCallback(response){
			console.log(response.data);
		});
	};
	
	// 현재 cart안의 item들의 총 가격 알려 줌
	$scope.calGrandTotal = function(){
		var grandTotal = 0;
		
		for(var i=0;i<$scope.cart.cartItems.length;i++){ // cart는 Cart model이라고 생각하자!
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}
		return grandTotal;
	};
	
	$scope.setCsrfToken = function(){
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		// 실제 http 헤더에 csrfToken값이 담김
		$http.defaults.headers.common[csrfHeader] = csrfToken;
	}
});

