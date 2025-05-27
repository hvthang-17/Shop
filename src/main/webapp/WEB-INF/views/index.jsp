<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<!DOCTYPE html>
<html lang="vi">
<jsp:include page="templates/head.jsp" />

<body>
	<div class="site-wrap">
		<jsp:include page="templates/header.jsp" />

		<div class="site-blocks-cover"
			style="background-image: url(static/images/b.png);" data-aos="fade">
			<div class="container">
				<div
					class="row align-items-start align-items-md-center justify-content-end">
					<div class="col-md-5 text-center text-md-left pt-5 pt-md-0">
						<h1 class="mb-2">Khám phá vẻ đẹp vượt thời gian</h1>

						<div class="intro-text text-center text-md-left">
							<p class="mb-4">Chào mừng bạn đến với cửa hàng đồ cổ của
								chúng tôi – nơi lưu giữ những giá trị xưa cũ, tinh tế và đầy
								lịch sử. Mỗi món đồ là một câu chuyện, một di sản chờ bạn khám
								phá.</p>
							<p class="text-center">
								<a href="shop" class="btn btn-sm btn-primary">Khám phá ngay</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section site-section-sm site-blocks-1">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4"
						data-aos="fade-up" data-aos-delay="">
						<div class="icon mr-4 align-self-start">
							<span class="icon-truck"></span>
						</div>

						<div class="text">
							<h2 class="text-uppercase">Miễn phí vận chuyển</h2>
							<p>Chúng tôi giao hàng miễn phí cho tất cả đơn hàng trên toàn
								quốc.</p>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4"
						data-aos="fade-up" data-aos-delay="100">
						<div class="icon mr-4 align-self-start">
							<span class="icon-refresh2"></span>
						</div>
						<div class="text">
							<h2 class="text-uppercase">Đổi trả miễn phí</h2>
							<p>Hoàn toàn yên tâm với chính sách đổi trả linh hoạt trong
								vòng 7 ngày.</p>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4"
						data-aos="fade-up" data-aos-delay="200">
						<div class="icon mr-4 align-self-start">
							<span class="icon-help"></span>
						</div>
						<div class="text">
							<h2 class="text-uppercase">Hỗ trợ khách hàng</h2>
							<p>Đội ngũ hỗ trợ luôn sẵn sàng giúp bạn từ 8h đến 22h mỗi
								ngày.</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="templates/collections-section.jsp" />

		<jsp:include page="templates/featured-products.jsp" />

		<div class="site-section block-8">
			<div class="container">
				<div class="row justify-content-center mb-5">
					<div class="col-md-7 site-section-heading text-center pt-4">
						<h2>Ưu đãi đặc biệt tháng này!</h2>
					</div>
				</div>
				<div class="row align-items-center">
					<div class="col-md-12 col-lg-7 mb-5">
						<a href="#"><img src="static/images/km.png"
							alt="Antique promotion" class="img-fluid rounded"></a>
					</div>
					<div class="col-md-12 col-lg-5 text-center pl-md-5">
						<h2>
							<a href="#">Giảm đến 50% các món đồ cổ được chọn lọc</a>
						</h2>
						<p class="post-meta mb-4">
							Bởi <a href="#">Antique House</a> <span class="block-8-sep">&bullet;</span>
							27 tháng 5, 2025
						</p>
						<p>Cơ hội hiếm có để sở hữu những món đồ cổ mang đậm dấu ấn
							thời gian. Được tuyển chọn kỹ lưỡng, mỗi sản phẩm đều là một tác
							phẩm nghệ thuật – nay với mức giá ưu đãi chưa từng có.</p>
						<p>
							<a href="shop" class="btn btn-primary btn-sm">Khám phá ngay</a>
						</p>
					</div>
				</div>
			</div>
		</div>


		<jsp:include page="templates/footer.jsp" />
	</div>

	<jsp:include page="templates/scripts.jsp" />
</body>
</html>