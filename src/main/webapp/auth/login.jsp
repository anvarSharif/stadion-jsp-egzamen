<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<div class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card shadow-lg p-4" style="width: 400px;">
        <div class="text-center mb-4">
            <h3 class="text-dark">Login</h3>
            <p class="text-muted">Tizimga kirish uchun maʼlumotlarni kiriting</p>
        </div>
        <form action="/auth/login" method="post">
            <div class="mb-3">
                <label for="phone" class="form-label">Telefon raqami</label>
                <div class="input-group">
                    <input type="text" id="phone" name="phone_inp" class="form-control" placeholder="90 123 45 67"
                           maxlength="9" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Parol</label>
                <input type="password" id="password" name="password_inp" class="form-control" placeholder="********"
                       required>
            </div>
            <div class="d-grid mt-4">
                <button type="submit" class="btn btn-dark btn-lg">Kirish</button>
            </div>
            <div class="text-center mt-3">
                    <a href="/auth/register.jsp" class="text-dark">Ro‘yxatdan o‘tish</a>
                </p>
            </div>
        </form>
    </div>
</div>
</body>
</html>
