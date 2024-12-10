<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.StadiumRepo" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Stadium" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.RegionRepo" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Region" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.BookingHourRepo" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.BookingHour" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Band qilish sahifasi</title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>

<%
    StadiumRepo stadiumRepo = new StadiumRepo();
    String stadiumIdStr = request.getParameter("stadiumId");
    if (stadiumIdStr==null){
        stadiumIdStr = (String) session.getAttribute("stadiumId");
    }
    Integer stadiumId = Integer.parseInt(stadiumIdStr);
    Stadium stadium= stadiumRepo.findById(stadiumId).get();

%>


<div class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-dark text-white">
            <h3 class="text-center">Band qilish sahifasi</h3>
        </div>
        <div class="card-body">
            <div class="row align-items-center mb-4 border-bottom pb-3">
                <div class="col-md-3">
                    <img src="/file/get?id=<%=stadium.getAttachment().getId()%>" class="img-fluid rounded"
                         alt="Rasm topilmadi!"
                         style="height: 300px; object-fit: cover;">
                </div>
                <div class="col-md-5">
                    <p class="mb-1 text-muted">Nomi: <strong><%= stadium.getName() %>
                    </strong></p>
                    <p class="mb-1 text-muted">Narxi: <strong><%= stadium.getPrice() %> UZS</strong></p>
                    <p class="mb-1 text-muted">joylashuv: <strong><%= stadium.getRegion().getName() %>
                    </strong></p>
                    <p class="mb-1 text-muted">Ochilish vaqti: <strong><%= stadium.getStartTime() %> : 00</strong></p>
                    <p class="mb-1 text-muted">Yopilish vaqti: <strong><%= stadium.getEndTime() %> : 00</strong></p>
                    <p class="mb-1 text-muted">Sharh: <strong><%= stadium.getDescription() %>
                    </strong></p>
                </div>
            </div>
        </div>

        <form action="/stadium/booking" method="POST" class="p-4 border rounded shadow-sm bg-light">
            <input type="hidden" name="stadiumId" value="<%=stadiumId%>">
            <div class=" g-3">
                <%
                    List<Integer> bookedHoursList = BookingHourRepo.findByStadiumIdAndCurrentUserId(stadiumId);
                    for (int time = stadium.getStartTime(); time <= stadium.getEndTime(); time++) {
                %>
                <div class="col">
                    <div class="form-check form-check-lg border rounded shadow-sm">
                        <% if (!bookedHoursList.contains(time)) { %>
                        <input
                                class="form-check-input"
                                type="checkbox"
                                name="bookedHours"
                                id="hour_<%= time %>"
                                value="<%= time %>"
                        >
                        <label class="form-check-label fs-5" for="hour_<%= time %>">
                            <%= time %>:00 - <%= time + 1 %>:00
                        </label>
                        <% } else { %>
                        <input
                                class="form-check-input"
                                type="checkbox"
                                id="hour_<%= time %>"
                                value="<%= time %>"
                                disabled
                        >
                        <label class="form-check-label fs-5 text-muted" for="hour_<%= time %>">
                            <%= time %>:00 - <%= time + 1 %>:00
                            <span class="badge bg-danger ms-2">Band qilingan</span>
                        </label>
                        <% } %>
                    </div>
                </div>
                <% } %>
            </div>
            <div class="text-center mt-4">
                <button type="submit" class="btn btn-primary btn-lg px-4" style="min-width: 200px;">Band qilish</button>
            </div>
        </form>



    </div>
</div>
</body>
</html>

