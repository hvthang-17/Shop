<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Đăng nhập</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="static/images/logo.png" />
<link rel="stylesheet" type="text/css"
	href="static/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="static/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="static/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="static/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="static/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="static/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="static/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="static/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="static/css/util.css">
<link rel="stylesheet" type="text/css" href="static/css/main.css">

<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/magnific-popup.css">
<link rel="stylesheet" href="static/css/jquery-ui.css">
<link rel="stylesheet" href="static/css/owl.carousel.min.css">
<link rel="stylesheet" href="static/css/owl.theme.default.min.css">
<link rel="stylesheet" href="static/css/aos.css">
<link rel="stylesheet" href="static/css/style.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">

<style>
body, input, button, .login100-form-title, .txt1 {
	font-family: 'Roboto', sans-serif !important;
}

.login100-form-title {
	font-size: 2.2rem;
	font-weight: 700;
	letter-spacing: 2px;
}

.input100 {
	font-size: 1.1rem;
}

.login100-form-btn {
	font-size: 1.2rem;
	font-weight: 600;
	padding: 12px 0;
}

.txt1 {
	font-size: 1rem;
}
</style>

</head>

<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="shadow-lg p-2 p-lg-5 rounded" data-aos="fade-up">
				<div class="wrap-login100 p-t-50 p-b-90">
					<form action="login?status=typed" method="post"
						class="login100-form validate-form flex-sb flex-w">
						<span class="login100-form-title p-b-51"> ĐĂNG NHẬP </span>

						${alert}

						<div class="wrap-input100 validate-input m-b-16"
							data-validate="Username is required">
							<input class="input100" type="text" name="username"
								placeholder="Tên đăng nhập">
						</div>

						<div class="wrap-input100 validate-input m-b-16"
							data-validate="Password is required">
							<input class="input100" type="password" name="password"
								placeholder="Mật khẩu">
						</div>

						<div class="flex-sb-m w-full p-t-3 p-b-24">
							<div class="contact100-form-checkbox">
								<input class="input-checkbox100" id="ckb1" type="checkbox"
									name="remember-me-checkbox"> <label
									class="label-checkbox100" for="ckb1"> Ghi nhớ tài khoản
								</label>
							</div>

							<div>
								<a href="#" class="txt1"> Quên mật khẩu? </a>
							</div>
						</div>

						<div class="container-login100-form-btn m-t-17">
							<button type="submit" class="login100-form-btn">Đăng
								nhập</button>
						</div>
					</form>
				</div>

				<div class="text-center">
					<p class="txt1" style="color: #999999">
						Chưa có tài khoản? <a href="register" class="txt1"> Đăng ký
							ngay </a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<div id="dropDownSelect1"></div>

	<script src="static/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="static/vendor/animsition/js/animsition.min.js"></script>
	<script src="static/vendor/bootstrap/js/popper.js"></script>
	<script src="static/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="static/vendor/select2/select2.min.js"></script>
	<script src="static/vendor/daterangepicker/moment.min.js"></script>
	<script src="static/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="static/vendor/countdowntime/countdowntime.js"></script>

	<script src="static/js/jquery-3.3.1.min.js"></script>
	<script src="static/js/jquery-ui.js"></script>
	<script src="static/js/popper.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/owl.carousel.min.js"></script>
	<script src="static/js/jquery.magnific-popup.min.js"></script>
	<script src="static/js/aos.js"></script>
	<script src="static/js/main.js"></script>
</body>
</html>
