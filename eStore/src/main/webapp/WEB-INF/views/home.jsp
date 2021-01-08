<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>

	<!-- carousel(슬라이딩)에 관련된 내용 67~115까지 -->

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<!-- 첫번째 슬라이드 -->
				<svg class="bd-placeholder-img" width="100%" height="100%"
					xmlns="http://www.w3.org/2000/svg"
					preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
					<rect width="100%" height="100%" fill="#777" /></svg>
				<div class="container">
					<div class="carousel-caption text-left">
						<h1>Example headline.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Sign
								up today</a>
						</p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<!-- 두번째 슬라이드 -->
				<svg class="bd-placeholder-img" width="100%" height="100%"
					xmlns="http://www.w3.org/2000/svg"
					preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
					<rect width="100%" height="100%" fill="#777" /></svg>
				<div class="container">
					<div class="carousel-caption">
						<h1>Another example headline.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Learn
								more</a>
						</p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<!-- 세번째 슬라이드 -->
				<svg class="bd-placeholder-img" width="100%" height="100%"
					xmlns="http://www.w3.org/2000/svg"
					preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
					<rect width="100%" height="100%" fill="#777" /></svg>
				<div class="container">
					<div class="carousel-caption text-right">
						<h1>One more for good measure.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Browse
								gallery</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#myCarousel" role="button"
			data-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#myCarousel" role="button"
			data-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="sr-only">Next</span>
		</a>
	</div>


	<!-- Marketing messaging and featurettes
  ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->

	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<!-- 하나의 행에 4그리드씩 분할 하여 3개의 동그라미가 들어감 -->
			<div class="col-lg-4">
				<svg class="bd-placeholder-img rounded-circle" width="140"
					height="140" xmlns="http://www.w3.org/2000/svg"
					preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
					aria-label="Placeholder: 140x140">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#777" />
					<text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
				<h2>Heading</h2>
				<p>Donec sed odio dui. Etiam porta sem malesuada magna mollis
					euismod. Nullam id dolor id nibh ultricies vehicula ut id elit.
					Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
					Praesent commodo cursus magna.</p>
				<p>
					<a class="btn btn-secondary" href="#" role="button">View
						details &raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<svg class="bd-placeholder-img rounded-circle" width="140"
					height="140" xmlns="http://www.w3.org/2000/svg"
					preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
					aria-label="Placeholder: 140x140">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#777" />
					<text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
				<h2>Heading</h2>
				<p>Duis mollis, est non commodo luctus, nisi erat porttitor
					ligula, eget lacinia odio sem nec elit. Cras mattis consectetur
					purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo,
					tortor mauris condimentum nibh.</p>
				<p>
					<a class="btn btn-secondary" href="#" role="button">View
						details &raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<svg class="bd-placeholder-img rounded-circle" width="140"
					height="140" xmlns="http://www.w3.org/2000/svg"
					preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
					aria-label="Placeholder: 140x140">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#777" />
					<text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
				<h2>Heading</h2>
				<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
					egestas eget quam. Vestibulum id ligula porta felis euismod semper.
					Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
					nibh, ut fermentum massa justo sit amet risus.</p>
				<p>
					<a class="btn btn-secondary" href="#" role="button">View
						details &raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->
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
