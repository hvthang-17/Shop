<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp" />

<body>
	<div class="site-wrap">
		<jsp:include page="templates/header.jsp" />

		<div class="bg-light py-3">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb-0">
						<a href="/">Trang chủ</a> <span class="mx-2 mb-0">/</span>
						<strong class="text-black">Giỏ hàng</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<form class="container" method="post" action="checkout">
				<!-- Hiển thị thông báo -->
				<c:if test="${not empty message}">
					<div class="alert alert-info">${message}</div>
				</c:if>

				<div class="row mb-5">
					<div class="col-md-12">
						<div class="site-blocks-table">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Ảnh</th>
										<th>Sản phẩm</th>
										<th>Giá</th>
										<th>Số lượng</th>
										<th>Tổng cộng</th>
										<th>Xóa</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order.cartProducts}" var="o">
										<tr>
											<td><img src="data:image/jpg;base64,${o.product.base64Image}"
													class="img-fluid" alt="Image"></td>
											<td><input class="form-control-plaintext text-center" readonly
												value="${o.product.name}" /></td>
											<td><input class="form-control-plaintext text-center" readonly
												value="${o.price}" /></td>
											<td>
												<div class="input-group">
													<div class="input-group-prepend">
														<button class="btn btn-outline-primary js-btn-minus"
															type="button">&minus;</button>
													</div>
													<input name="product-quantity" type="text"
														class="form-control text-center"
														value="${o.quantity}" />
													<div class="input-group-append">
														<button class="btn btn-outline-primary js-btn-plus"
															type="button">&plus;</button>
													</div>
												</div>
											</td>
											<td><input class="form-control-plaintext text-center" readonly
												value="${o.price * o.quantity}" /></td>
											<td><a href="cart?remove-product-id=${o.product.id}"
												class="btn btn-danger btn-sm">X</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!-- Áp dụng mã giảm giá -->
				<div class="row">
					<div class="col-md-6">
						<div class="mb-3">
							<a href="shop" class="btn btn-outline-primary">Tiếp tục mua sắm</a>
						</div>

						<h4 class="text-black">Mã giảm giá</h4>
						<p>Nhập mã nếu bạn có</p>

						<div class="form-row">
							<div class="col-md-8 mb-2">
								<input type="text" class="form-control py-3" name="coupon"
									placeholder="Nhập mã giảm giá">
							</div>
							<div class="col-md-4">
								<button type="submit" name="action" value="applyCoupon"
									class="btn btn-primary btn-sm btn-block">Áp dụng mã</button>
							</div>
						</div>
					</div>

					<!-- Tổng cộng -->
					<div class="col-md-6">
						<div class="border p-4 rounded">
							<h3 class="text-black h4 text-uppercase">Tổng giỏ hàng</h3>
							<div class="d-flex justify-content-between">
								<span class="text-black">Tổng cộng:</span>
								<span class="text-black font-weight-bold">
									<c:choose>
										<c:when test="${not empty discounted_price}">
											<del style="color: gray; font-size: 0.9em;">${total_price}</del>
											&nbsp; ${discounted_price}
										</c:when>
										<c:otherwise>
											${total_price}
										</c:otherwise>
									</c:choose>
								</span>
							</div>
							<hr />
							<button type="submit" name="action" value="proceedCheckout"
								class="btn btn-primary btn-block">Tiến hành thanh toán</button>
						</div>
					</div>
				</div>
			</form>
		</div>

		<jsp:include page="templates/footer.jsp" />
	</div>

	<jsp:include page="templates/scripts.jsp" />
</body>
</html>

