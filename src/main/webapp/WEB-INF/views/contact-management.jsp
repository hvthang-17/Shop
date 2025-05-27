<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
							class="text-black">Quản lý liên hệ</strong>
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
										<th>Họ tên</th>
										<th>Email</th>
										<th>Tiêu đề</th>
										<th>Nội dung</th>
										<th>Ngày gửi</th>
										<th>Xóa</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${contact_list}" var="c">
										<tr>
											<td>${c.firstName} ${c.lastName}</td>
											<td>${c.email}</td>
											<td>${c.subject}</td>
											<td>${c.message}</td>
											<td><c:out value="${c.createdAt}" /></td>
											<td><a href="delete-contact?id=${c.id}"
												class="btn btn-danger btn-sm"
												onclick="return confirm('Bạn có chắc chắn muốn xóa liên hệ này?');">
													<span class="icon icon-trash"></span>
											</a></td>
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
