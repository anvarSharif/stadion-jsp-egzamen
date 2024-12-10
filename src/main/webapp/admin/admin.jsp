<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %>
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
<div class="container mt-4">
    <div class="card shadow">

        <div class="card-header bg-dark text-white">
                <a href="/home.jsp" class="btn btn-secondary ">
                    Home
                </a>
            <h3 class="text-center">Admin</h3>
        </div>
        <form action="/admin/addStadium.jsp">
            <button class="btn btn-success my-1 align-content-end">
                Add Stadium
            </button>
        </form>
        <form action="/admin/monthReport.jsp">
            <button class="btn btn-success my-1 align-content-end">
                Month Reports
            </button>
        </form>
        <div class="card-body">
            <%
                StadiumRepo stadiumRepo = new StadiumRepo();
                List<Stadium> stadiums = stadiumRepo.findAll();
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
                    <p class="mb-1 text-muted">Narxi: <strong><%= stadium.getPrice() %> UZS</strong></p>
                    <p class="mb-1 text-muted">joylashuv: <strong><%= stadium.getRegion().getName() %></strong></p>
                    <p class="mb-1 text-muted">Ochilish vaqti: <strong><%= stadium.getStartTime() %> : 00</strong></p>
                    <p class="mb-1 text-muted">Yopilish vaqti: <strong><%= stadium.getEndTime() %> : 00</strong></p>

                </div>
                <div class="col-md-4 text-end">
                    <div class="d-flex justify-content-end align-items-center">
                        <form action="/admin/addStadium.jsp">
                            <input type="hidden" name="stadiumId" value="<%=stadium.getId()%>">
                            <button class="btn btn-dark m-2">
                                Edite
                            </button>
                        </form>
                        <form action="/stadium/delete" method="post">
                            <input type="hidden" name="stadiumId" value="<%=stadium.getId()%>">
                            <button class="btn btn-danger">
                                Delete
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <% }} %>
        </div>
    </div>
</div>
</body>
</html>

