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
							class="text-black">Quản lý danh mục</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row mb-4">
					<div class="col-md-6">
						<h2 class="text-black">Danh sách danh mục</h2>
					</div>
					<div class="col-md-6 text-right">
						<!-- Nút mở modal thêm -->
						<button class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#addCategoryModal">Thêm danh mục</button>
					</div>
				</div>

				<!-- Bảng danh sách -->
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Tên danh mục</th>
							<th>Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="category" items="${category_list}">
							<tr>
								<td>${category.id}</td>
								<td>${category.name}</td>
								<td>
									<!-- Nút sửa mở modal -->
									<button class="btn btn-sm btn-success" data-toggle="modal"
										data-target="#editCategoryModal${category.id}"
										style="background-color: green; border-color: green">
										<span class="icon icon-pencil"></span>
									</button> <a href="delete-category?id=${category.id}"
									class="btn btn-sm btn-danger"
									style="background-color: red; border-color: red"
									onclick="return confirm('Xác nhận xóa danh mục này?')"><span
										class="icon icon-trash"></span></a>
								</td>
							</tr>

							<!-- Modal Sửa danh mục -->
							<div class="modal fade" id="editCategoryModal${category.id}"
								tabindex="-1" role="dialog"
								aria-labelledby="editCategoryModalLabel${category.id}"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<form action="edit-category" method="post">
											<div class="modal-header">
												<h5 class="modal-title">Sửa danh mục</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<input type="hidden" name="id" value="${category.id}">
												<div class="form-group">
													<label for="name">Tên danh mục</label> <input type="text"
														name="name" class="form-control" value="${category.name}"
														required>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Hủy</button>
												<button type="submit" class="btn btn-primary">Lưu
													thay đổi</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Modal Thêm danh mục -->
		<div class="modal fade" id="addCategoryModal" tabindex="-1"
			role="dialog" aria-labelledby="addCategoryModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form action="add-category" method="post">
						<div class="modal-header">
							<h5 class="modal-title">Thêm danh mục mới</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="name">Tên danh mục</label> <input type="text"
									name="name" class="form-control"
									placeholder="Nhập tên danh mục" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Hủy</button>
							<button type="submit" class="btn btn-primary">Thêm</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<jsp:include page="templates/footer.jsp" />
	</div>

	<jsp:include page="templates/scripts.jsp" />
</body>
</html>
