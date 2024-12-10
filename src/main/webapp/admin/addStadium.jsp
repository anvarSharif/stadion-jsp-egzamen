<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Stadium" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.entity.Region" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.RegionRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.stadionjsp_modul8egzamen.repo.StadiumRepo" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add New Stadium</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    Stadium stadium = null;
    String stadiumId = request.getParameter("stadiumId");
    if (stadiumId != null) {
        StadiumRepo stadiumRepo = new StadiumRepo();
        Optional<Stadium> optionalStadium = stadiumRepo.findById(Integer.parseInt(stadiumId));
        if (optionalStadium.isPresent()) {
            stadium = optionalStadium.get();
        }
    }
%>

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-dark text-white text-center">
            <h3><%= stadium != null ? "Update" : "Add" %> Stadium</h3>
        </div>
        <div class="card-body">
            <form action="<%= stadium != null ? "/stadium/update" : "/stadium/add" %>" method="POST"
                  enctype="multipart/form-data">
                <%if (stadiumId != null) {%>
                <input type="hidden" value="<%=stadiumId%>" name="stadiumId">
                <%}%>

                <div class="mb-3">
                    <label for="img" class="form-label">
                        <%
                            if (stadium != null && stadium.getAttachment() != null) {
                        %>
                        <img src="/file/get?id=<%=stadium.getAttachment().getId()%>" alt="Current image" class=""
                             width="150">
                        <% }%>
                    </label>
                    <input type="file" class="form-control" id="img" <%=stadium != null ? "" : "required"%>
                           name="photo">
                </div>


                <div class="mb-3">
                    <label for="name" class="form-label">Stadion nomi</label>
                    <input type="text" value="<%=stadium==null?"":stadium.getName()%>" class="form-control" id="name"
                           name="name" placeholder="Stadion nomini kiriting:" required>
                </div>


                <div class="mb-3">
                    <label for="startTime" class="form-label">Boshlanish soati (24-soatlik farmat(9:00 -> 9))</label>
                    <input type="number" value="<%=stadium==null?"":stadium.getStartTime()%>" class="form-control"
                           id="startTime" name="startTime" placeholder="Boshlanish vaqtini kiriting:" min="0" max="23"
                           required>
                </div>

                <div class="mb-3">
                    <label for="endTime" class="form-label">Tugash soati (24-soatlik farmat(9:00 -> 9))</label>
                    <input type="number" value="<%=stadium==null?"":stadium.getEndTime()%>" class="form-control"
                           id="endTime" name="endTime" placeholder="Tugash vaqtini kiriting:" min="0" max="23" required>
                </div>
                <div class="mb-3">
                    <label for="region" class="form-label">Manzil</label>
                    <select class="form-select" id="region" name="regionId" required>
                        <option value="" disabled selected>Select Region</option>
                        <% RegionRepo regionRepo = new RegionRepo();
                            List<Region> regionList = regionRepo.findAll();
                            for (Region region : regionList) { %>
                        <option <%=stadium != null ? stadium.getRegion().getId().equals(region.getId()) ? "selected" : "" : ""%>
                                value="<%= region.getId() %>"><%= region.getName() %>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Narx (UZS)</label>
                    <input type="number" value="<%=stadium==null?"":stadium.getPrice()%>" class="form-control"
                           id="price" name="price" placeholder="Narx kiriting:"
                           required>
                </div>

                <!-- Description -->
                <div class="mb-3">
                    <label for="description" class="form-label">Sharh</label>
                    <textarea class="form-control" id="description" name="description" rows="3"
                              placeholder="Sharh yozing:">
        <%= stadium != null ? stadium.getDescription() : "" %>
    </textarea>
                </div>


                <!-- Submit Button -->
                <div class="text-end">
                    <button type="submit" class="btn btn-primary"><%=stadium == null ? "Add" : "Update"%>Stadium
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
