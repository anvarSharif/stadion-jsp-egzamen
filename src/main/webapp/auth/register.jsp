<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    Object object = request.getAttribute("errors");
    Map<String,String> errors=new HashMap<>();
    if (object!=null){
        errors=(Map<String, String>) object;
    }
%>

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow p-4" style="width: 100%; max-width: 400px;">
        <h2 class="text-center mb-4">Register</h2>
        <form action="/auth/register" method="post">
            <div class="mb-3">
                <label for="phone" class="form-label">Phone Number</label>
                <input type="tel" class="form-control" id="phone" name="phone_inp"  placeholder="90 123 45 67">
                <span class="text-danger"><%=Objects.requireNonNullElse(errors.get("phone"),"")%></span>

            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" name="password_inp" id="password" placeholder="Enter your password" required>
                <span class="text-danger"><%=Objects.requireNonNullElse(errors.get("password"),"")%></span>

            </div>
            <div class="mb-3">
                <label for="password-repeat" class="form-label">Password</label>
                <input type="password" class="form-control" name="password_repeat_inp" id="password-repeat" placeholder="Confirm your password" required>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Register</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
