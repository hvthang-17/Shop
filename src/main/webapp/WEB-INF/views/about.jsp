<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp" />
<style>
.mission-vision-img {
	height: 250px;
	object-fit: cover;
	border-radius: 16px; /* Bo tròn góc */
	width: 100%;
}
</style>

<body>
	<div class="site-wrap">
		<%
		request.setAttribute("about_active", "active");
		%>
		<jsp:include page="templates/header.jsp" />

		<div class="bg-light py-3">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb-0">
						<a href="index.jsp">Trang chủ</a> <span class="mx-2 mb-0">/</span>
						<strong class="text-black">Giới thiệu</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section border-bottom" data-aos="fade">
			<div class="container">
				<div class="row mb-5">
					<div class="col-md-6">
						<div class="block-16">
							<figure>
								<img src="static/images/introduce.png" alt="Image placeholder"
									class="img-fluid rounded">
								<a href="https://vimeo.com/channels/staffpicks/93951774"
									class="play-button popup-vimeo"> <span class="ion-md-play"></span>
								</a>
							</figure>
						</div>
					</div>

					<div class="col-md-1"></div>

					<div class="col-md-5">
						<div class="site-section-heading pt-3 mb-4">
							<h2 class="text-black">Hành Trình Gìn Giữ Giá Trị Thời Gian</h2>
						</div>
						<p>Antique House bắt đầu từ niềm đam mê với những giá trị cổ
							xưa. Từ một bộ sưu tập nhỏ, chúng tôi phát triển thành cửa hàng
							đồ cổ được yêu mến và tin tưởng.</p>
						<p>Mỗi món đồ đều ẩn chứa câu chuyện lịch sử – từ chiếc đồng
							hồ thời Pháp đến bức tranh thập niên 40, tất cả đều gợi lại ký ức
							một thời.</p>
						<p>Chúng tôi kết nối quá khứ với hiện tại qua những hiện vật
							độc đáo, được tuyển chọn và bảo quản kỹ lưỡng, mang đến trải
							nghiệm khám phá đầy ý nghĩa cho khách hàng.</p>

					</div>
				</div>
			</div>

			<div class="site-section" data-aos="fade-up">
				<div class="container">
					<div class="row mb-5">
						<!-- Sứ mệnh -->
						<div class="col-md-6">
							<img src="static/images/sumenh.png" alt="Sứ mệnh"
								class="img-fluid rounded-4 mb-3 mission-vision-img">
							<div class="site-section-heading mb-3">
								<h2 class="text-black">Sứ Mệnh</h2>
							</div>
							<p>Chúng tôi gìn giữ giá trị văn hóa và lịch sử thông qua
								từng món đồ cổ, kết nối quá khứ với hiện tại bằng các hiện vật
								đầy câu chuyện.</p>
							<p>Antique House cam kết cung cấp sản phẩm chất lượng, được
								chọn lọc kỹ lưỡng và mang giá trị tinh thần sâu sắc.</p>
						</div>

						<!-- Tầm nhìn -->
						<div class="col-md-6">
							<img src="static/images/tamnhin.jpg" alt="Tầm nhìn"
								class="img-fluid rounded-4 mb-3 mission-vision-img">
							<div class="site-section-heading mb-3">
								<h2 class="text-black">Tầm Nhìn</h2>
							</div>
							<p>Chúng tôi hướng đến trở thành địa chỉ đồ cổ uy tín tại
								Việt Nam, xây dựng cộng đồng yêu đồ cổ ngày càng lớn mạnh.</p>
							<p>Luôn đổi mới và kết hợp công nghệ hiện đại, chúng tôi mang
								lại trải nghiệm mua sắm tiện lợi và đầy cảm hứng.</p>
						</div>
					</div>
				</div>
			</div>

			<div class="site-section border-bottom" data-aos="fade">
				<div class="container">
					<div class="row justify-content-center mb-5">
						<div class="col-md-7 site-section-heading text-center pt-4">
							<h2>Đội Ngũ Của Chúng Tôi</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-lg-3">

							<div class="block-38 text-center">
								<div class="block-38-img">
									<div class="block-38-header">
										<img src="static/images/person_1.jpg" alt="Image placeholder"
											class="mb-4">
										<h3 class="block-38-heading h4">Elizabeth Graham</h3>
										<p class="block-38-subheading">Nhà sưu tầm / Sáng lập</p>
									</div>
									<div class="block-38-body">
										<p>Với hơn 15 năm kinh nghiệm, Elizabeth Graham là người
											trực tiếp tìm kiếm, thẩm định và chia sẻ giá trị từng món đồ
											cổ đến tay khách hàng.</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-lg-3">
							<div class="block-38 text-center">
								<div class="block-38-img">
									<div class="block-38-header">
										<img src="static/images/person_2.jpg" alt="Image placeholder"
											class="mb-4">
										<h3 class="block-38-heading h4">Jennifer Greive</h3>
										<p class="block-38-subheading">Tư vấn & Dịch vụ khách hàng</p>
									</div>
									<div class="block-38-body">
										<p>Jennifer luôn sẵn sàng lắng nghe và hỗ trợ khách hàng
											lựa chọn sản phẩm phù hợp với nhu cầu, sở thích và không gian
											sống.</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-lg-3">
							<div class="block-38 text-center">
								<div class="block-38-img">
									<div class="block-38-header">
										<img src="static/images/person_3.jpg" alt="Image placeholder"
											class="mb-4">
										<h3 class="block-38-heading h4">Patrick Marx</h3>
										<p class="block-38-subheading">Chuyên viên phục chế</p>
									</div>
									<div class="block-38-body">
										<p>Patrick là một chuyên gia có kỹ năng phục chế cao, giúp
											các món đồ cổ được bảo tồn nguyên trạng và giữ được vẻ đẹp
											theo thời gian.</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-lg-3">
							<div class="block-38 text-center">
								<div class="block-38-img">
									<div class="block-38-header">
										<img src="static/images/person_4.jpg" alt="Image placeholder"
											class="mb-4">
										<h3 class="block-38-heading h4">Mike Coolbert</h3>
										<p class="block-38-subheading">Quản lý kho & vận chuyển</p>
									</div>
									<div class="block-38-body">
										<p>Đảm bảo mỗi món đồ đều được vận chuyển an toàn, đóng
											gói cẩn thận và đến tay khách hàng trong tình trạng hoàn hảo.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="site-section site-section-sm site-blocks-1 border-0"
				data-aos="fade">
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4"
							data-aos="fade-up" data-aos-delay="">
							<div class="icon mr-4 align-self-start">
								<span class="icon-truck"></span>
							</div>
							<div class="text">
								<h2 class="text-uppercase">Giao Hàng Cẩn Thận</h2>
								<p>Đảm bảo mỗi món đồ cổ được gói kỹ lưỡng và giao đến tay
									khách hàng nguyên vẹn, đúng thời gian.</p>
							</div>
						</div>
						<div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4"
							data-aos="fade-up" data-aos-delay="100">
							<div class="icon mr-4 align-self-start">
								<span class="icon-refresh2"></span>
							</div>
							<div class="text">
								<h2 class="text-uppercase">Chính Sách Đổi Trả</h2>
								<p>Hỗ trợ đổi trả trong 7 ngày nếu sản phẩm có lỗi kỹ thuật
									hoặc không đúng mô tả ban đầu.</p>
							</div>
						</div>
						<div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4"
							data-aos="fade-up" data-aos-delay="200">
							<div class="icon mr-4 align-self-start">
								<span class="icon-help"></span>
							</div>
							<div class="text">
								<h2 class="text-uppercase">Tư Vấn Tận Tâm</h2>
								<p>Luôn đồng hành cùng khách hàng trong hành trình khám phá
									và sưu tầm những giá trị cổ xưa.</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<jsp:include page="templates/footer.jsp" />
		</div>

		<jsp:include page="templates/scripts.jsp" />
</body>
</html>