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

		<div class="bg-light py-3">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb-0">
						<a href="/">Trang chủ</a> <span class="mx-2 mb-0">/</span> <strong
							class="text-black">Quản lý mã giảm giá</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row mb-5">
					<div class="col-md-12">
						<div class="site-blocks-table">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Mã giảm giá</th>
										<th>Số % giảm</th>
										<th>Ngày hết hạn</th>
										<th>Sửa / Xóa</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${coupon_list}" var="c">
										<tr>
											<td>${c.code}</td>
											<td>${c.discountPercent}%</td>
											<td>${c.expiryDate}</td>
											<td><a href="edit-coupon?coupon-id=${c.id}"
												class="btn btn-primary btn-sm"
												style="background-color: green; border-color: green"> <span
													class="icon icon-pencil"></span>
											</a> <a href="delete-coupon?coupon-id=${c.id}"
												class="btn btn-sm btn-danger"
												style="background-color: red; border-color: red"
												onclick="return confirm('Xác nhận xóa mã giảm giá này?')">
													<span class="icon icon-trash"></span>
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 offset-md-3">
						<button class="btn btn-primary btn-sm btn-block"
							data-toggle="modal" data-target="#addCouponModal">Thêm
							mã giảm giá</button>

						<!-- Modal thêm mã giảm giá -->
						<div class="modal fade" id="addCouponModal" tabindex="-1"
							role="dialog" aria-labelledby="addCouponModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<form class="modal-content" action="add-coupon" method="post">
									<div class="modal-header">
										<h5 class="modal-title" id="addCouponModalLabel">Thêm mã
											giảm giá</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Đóng">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>

									<div class="modal-body">
										<div class="form-group">
											<label for="code" class="text-black">Mã giảm giá <span
												class="text-danger">*</span></label> <input type="text" name="code"
												id="code" class="form-control" required>
										</div>

										<div class="form-group">
											<label for="discount" class="text-black">% Giảm <span
												class="text-danger">*</span></label> <input type="number"
												name="discountPercent" id="discountPercent" class="form-control" min="1"
												max="100" required>
										</div>

										<div class="form-group">
											<label for="expiry" class="text-black">Ngày hết hạn <span
												class="text-danger">*</span></label> <input type="date"
												name="expiryDate" id="expiryDate" class="form-control" required>
										</div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-outline-primary btn-sm"
											data-dismiss="modal">Hủy</button>
										<button type="submit" class="btn btn-primary btn-sm">Lưu</button>
									</div>
								</form>
							</div>
						</div>
						<!-- End Modal -->
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="templates/footer.jsp" />
	</div>

	<jsp:include page="templates/scripts.jsp" />
</body>
</html>
