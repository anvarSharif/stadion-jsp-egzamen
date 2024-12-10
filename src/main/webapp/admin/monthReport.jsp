
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.StadiumRepo" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Stadium" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<%
    StadiumRepo stadiumRepo = new StadiumRepo();
    List<Stadium> stadiums = stadiumRepo.findAll();
%>
<div class="w-25 m-2">
    <a href="/admin/admin.jsp" class="btn btn-dark p-1"><- Orqaga </a>
</div>
<div class="container mt-4">
    <div class="card shadow">

        <div class="card-header bg-dark text-white">
            <h1>Oylik Hisobot</h1>
        </div>
        <div class="card-body">
            <%
                if (stadiums.isEmpty()) {
            %>
            <div class="alert alert-info text-center">
                Stadium yo'q
            </div>
            <% } else {
                for (Stadium stadium : stadiums) {
            %>
            <div class="row align-items-center mb-4 border-bottom pb-3">
                <div class="col-md-3">
                    <img src="/file/get?id=<%=stadium.getAttachment().getId()%>" class="img-fluid rounded" alt="Rasm topilmadi!"
                         width="200" height="200" >
                </div>
                <div class="col-md-5">
                    <p class="mb-1 text-muted">Nomi: <strong><%= stadium.getName() %></strong></p>
                    <p class="mb-1 text-muted">Narxi: <strong><%= stadium.getPrice() %></strong></p>
                    <p class="mb-1 text-muted">joriy oy o'yinlar soni: <strong><%=stadiumRepo.findCountGamesForMonth(stadium.getId())%></strong></p>
                    <p class="mb-1 text-muted">joriy oy tushumi: <strong><%=stadiumRepo.findCountGamesForMonth(stadium.getId())*stadium.getPrice()%> UZS</strong></p>
                </div>
            </div>
            <% }} %>
        </div>
    </div>

</div>
</body>
</html>

