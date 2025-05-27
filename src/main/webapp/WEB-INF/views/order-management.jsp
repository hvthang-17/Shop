<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

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
							class="text-black">Quản lý đơn hàng</strong>
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
										<th>Ảnh</th>
										<th>ID</th>
										<th style="max-width: 120px">Tên sản phẩm</th>
										<th>Giá</th>
										<th>Số lượng</th>
										<th>Tổng tiền</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order_detail_list}" var="o">
										<tr>
											<td class="product-thumbnail"><img
												src="data:image/jpg;base64,${o.product.base64Image}"
												alt="Image" class="img-fluid"></td>

											<td>${o.product.id}</td>

											<td>${o.product.name}</td>

											<td>$${o.price}</td>

											<td>${o.quantity}</td>

											<td>${o.quantity * o.price}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
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