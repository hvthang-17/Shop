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
						<a href="/"> Trang chủ </a> <span class="mx-2 mb-0">/</span> <a
							href="order-history">Lịch sử đơn hàng</a> <span class="mx-2 mb-0">/</span>

						<strong class="text-black">Chi tiết đơn hàng</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section" data-aos="fade-in">
			<div class="container">
				<div class="row mb-5">
					<div class="col-md-12">
						<div class="site-blocks-table">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Tên sản phẩm</th>
										<th>Số lượng</th>
										<th>Giá</th>
										<th>Tổng tiền</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order_detail_list}" var="o">
										<tr>
											<td>${o.product.name}</td>

											<td>${o.quantity}</td>

											<td>$${o.price}</td>

											<td>$${o.price * o.quantity}</td>
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