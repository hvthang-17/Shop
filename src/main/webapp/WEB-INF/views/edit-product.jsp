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
							class="text-black">Chỉnh sửa sản phẩm</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="h3 mb-3 text-black">Thông tin sản phẩm</h2>
					</div>

					<div class="col-md-7">
						<form action="edit-product" method="post"
							enctype="multipart/form-data">
							<div class="p-3 border">
								<div class="form-group row">
									<div class="col-md-12">
										<label for="id" class="text-black"> Mã sản phẩm <span
											class="text-danger">*</span>
										</label> <input name="product-id" type="text" class="form-control"
											id="id" value="${product.id}" readonly>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="name" class="text-black"> Tên sản phẩm <span
											class="text-danger">*</span>
										</label> <input name="product-name" type="text" class="form-control"
											id="name" value="${product.name}">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="image" class="text-black"> Hình ảnh <span
											class="text-danger">*</span>
										</label> <input name="product-image" type="file" class="form-control"
											id="image">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="price" class="text-black"> Giá <span
											class="text-danger">*</span>
										</label> <input name="product-price" type="number"
											class="form-control" id="price" value="${product.price}">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="description" class="text-black"> Mô tả <span
											class="text-danger">*</span>
										</label>

										<textarea name="product-description" id="description"
											cols="30" rows="7" class="form-control">${product.description}</textarea>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="amount" class="text-black"> Số lượng <span
											class="text-danger">*</span>
										</label> <input name="product-amount" type="number"
											class="form-control" id="amount" value="${product.amount}">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="category" class="text-black"> Danh mục <span
											class="text-danger">*</span>
										</label> <select name="product-category" id="category"
											class="form-control">
											<c:forEach items="${category_list}" var="o">
												<option value="${o.id}">${o.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-lg-12">
										<input type="submit" class="btn btn-primary btn-lg btn-block"
											value="Cập nhật sản phẩm">
									</div>
								</div>
							</div>
						</form>
					</div>

					<div class="col-md-5 ml-auto">
						<div class="p-3 border">
							<img src="data:image/jpg;base64,${product.base64Image}"
								alt="image" width="100%">
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