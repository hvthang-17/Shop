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
                        <a href="/">Trang chủ</a> <span class="mx-2 mb-0">/</span>
                        <a href="coupon-management">Quản lý mã giảm giá</a> <span class="mx-2 mb-0">/</span>
                        <strong class="text-black">Chỉnh sửa mã giảm giá</strong>
                    </div>
                </div>
            </div>
        </div>

        <div class="site-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="h3 mb-3 text-black">Thông tin mã giảm giá</h2>
                    </div>

                    <div class="col-md-7">
                        <form action="edit-coupon" method="post">
                            <div class="p-3 border">
                                <!-- Hidden ID -->
                                <input type="hidden" name="coupon-id" value="${coupon.id}" />

                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="coupon-code" class="text-black">Mã giảm giá <span class="text-danger">*</span></label>
                                        <input type="text" id="coupon-code" name="coupon-code" class="form-control" value="${coupon.code}" required />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="discount-percent" class="text-black">% Giảm <span class="text-danger">*</span></label>
                                        <input type="number" id="discount-percent" name="discount-percent" class="form-control" value="${coupon.discountPercent}" min="1" max="100" required />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label for="expiry-date" class="text-black">Ngày hết hạn <span class="text-danger">*</span></label>
                                        <input type="date" id="expiry-date" name="expiry-date" class="form-control" value="${coupon.expiryDate}" required />
                                    </div>
                                </div>

                                <div class="form-group row mt-4">
                                    <div class="col-lg-12">
                                        <button type="submit" class="btn btn-primary btn-lg btn-block">Cập nhật mã giảm giá</button>
                                        <a href="coupon-management" class="btn btn-outline-secondary btn-block btn-lg mt-2">Hủy</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-md-5 ml-auto">
                        <div class="p-3 border">
                            <p><strong>Mã giảm giá:</strong> ${coupon.code}</p>
                            <p><strong>% Giảm:</strong> ${coupon.discountPercent}%</p>
                            <p><strong>Ngày hết hạn:</strong> ${coupon.expiryDate}</p>
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
