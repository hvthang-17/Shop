<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<jsp:include page="templates/head.jsp" />

<body>
	<div class="site-wrap">
		<%
		request.setAttribute("contact_active", "active");
		%>
		<jsp:include page="templates/header.jsp" />

		<div class="bg-light py-3">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb-0">
						<a href="index.jsp">Trang chủ</a> <span class="mx-2 mb-0">/</span>
						<strong class="text-black">Liên hệ</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="h3 mb-3 text-black">Liên hệ với chúng tôi</h2>
					</div>

					<div class="col-md-7">
						<c:if test="${not empty success}">
							<div class="alert alert-success">${success}</div>
						</c:if>
						<c:if test="${not empty error}">
							<div class="alert alert-danger">${error}</div>
						</c:if>

						<form action="contact" method="post">
							<div class="p-3 p-lg-5 border">
								<div class="form-group row">
									<div class="col-md-6">
										<label for="c_fname" class="text-black">Họ <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="c_fname" name="c_fname">
									</div>

									<div class="col-md-6">
										<label for="c_lname" class="text-black">Tên <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="c_lname" name="c_lname">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_email" class="text-black">Email <span
											class="text-danger">*</span></label> <input type="email"
											class="form-control" id="c_email" name="c_email"
											placeholder="">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_subject" class="text-black">Chủ đề </label> <input
											type="text" class="form-control" id="c_subject"
											name="c_subject">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_message" class="text-black">Nội dung </label>
										<textarea name="c_message" id="c_message" cols="30" rows="7"
											class="form-control"></textarea>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-lg-12">
										<input type="submit" class="btn btn-primary btn-lg btn-block"
											value="Gửi">
									</div>
								</div>
							</div>
						</form>
					</div>

					<div class="col-md-5 ml-auto">
						<div class="p-4 border mb-3">
							<span class="d-block text-primary h6 text-uppercase">New
								York</span>
							<p class="mb-0">203 Phố Giả, Mountain View, San Francisco,
								California, Hoa Kỳ</p>
						</div>

						<div class="p-4 border mb-3">
							<span class="d-block text-primary h6 text-uppercase">London</span>
							<p class="mb-0">203 Phố Giả, Mountain View, San Francisco,
								California, Hoa Kỳ</p>
						</div>

						<div class="p-4 border mb-3">
							<span class="d-block text-primary h6 text-uppercase">Canada</span>
							<p class="mb-0">203 Phố Giả, Mountain View, San Francisco,
								California, Hoa Kỳ</p>
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