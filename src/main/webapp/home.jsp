<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.StadiumRepo" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Stadium" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.RegionRepo" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Region" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.UserRepo" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Attachment" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asosiy</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    StadiumRepo stadiumRepo = new StadiumRepo();
    String regionIdStr = request.getParameter("regionId");
    List<Stadium> stadiums = stadiumRepo.findAll();
    if (regionIdStr != null && !regionIdStr.equals("all")) {
        int regionId = Integer.parseInt(regionIdStr);
        stadiums = stadiumRepo.findAllByRegionId(regionId);
    }
    User currentUser = (User) session.getAttribute("currentUser");
%>

<div class="container mt-4">
    <div class="card shadow">

        <div class="card-header bg-dark text-white">
            <h3 class="text-center">Asosiy</h3>
            <%
                if (currentUser == null) {
            %>
            <a class="btn btn-secondary " href="/auth/login.jsp">Login</a>
            <%
            } else {
            %>
            <form action="/auth/logout" method="post">
                <button class="btn btn-secondary ">
                    Logout
                </button>
            </form>
            <%
                }%>
        </div>
        <div class="card-body">
            <form action="" class="d-flex align-items-center">
                <div>
                    <select class="form-select form-control" id="region" name="regionId" required>
                        <option value="all">Hammasi</option>
                        <%
                            RegionRepo regionRepo = new RegionRepo();
                            List<Region> regionList = regionRepo.findAll();
                            for (Region region : regionList) {
                                if (regionIdStr != null && !regionIdStr.equals("all")) {
                                    Integer regionId = Integer.parseInt(regionIdStr);
                        %>
                        <option <%=regionId.equals(region.getId()) ? "selected" : ""%>
                                value="<%= region.getId() %>"><%= region.getName() %>
                        </option>
                        <%} else {%>
                        <option
                                value="<%= region.getId() %>"><%= region.getName() %>
                        </option>
                        <% }
                        } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-dark m-2">Tanlash</button>
            </form>

            <%
                for (Stadium stadium : stadiums) {
            %>
            <div class="row align-items-center mb-4 border-bottom pb-3">
                <div class="col-md-3">

                    <img src="/file/get?id=<%=stadium.getAttachment().getId()%>" class="img-fluid rounded"
                         alt="Rasm topilmadi!"
                         style="height: 200px; object-fit: cover;">
                </div>
                <div class="col-md-5">
                    <p class="mb-1 text-muted">Nomi: <strong><%= stadium.getName() %>
                    </strong></p>
                    <p class="mb-1 text-muted">Narxi: <strong><%= stadium.getPrice() %> UZS</strong></p>
                    <p class="mb-1 text-muted">joylashuv: <strong><%= stadium.getRegion().getName() %>
                    </strong></p>
                    <p class="mb-1 text-muted">Ochilish vaqti: <strong><%= stadium.getStartTime() %> : 00</strong></p>
                    <p class="mb-1 text-muted">Yopilish vaqti: <strong><%= stadium.getEndTime() %> : 00</strong></p>
                </div>
                <div class="col-md-4 text-end">
                    <div class="d-flex justify-content-end align-items-center">
                        <form action="/booking.jsp">
                            <input type="hidden" name="stadiumId" value="<%=stadium.getId()%>">
                            <button class="btn btn-outline-secondary m-2">
                                Tanlash
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <%
                } %>
        </div>
    </div>
</div>
</body>
</html>

