<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách phòng trọ</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Danh sách phòng trọ</h1>

    <!-- Tìm kiếm -->
    <form action="${pageContext.request.contextPath}/rooms/search" method="get">
        <div class="form-group">
            <input type="text" name="keyword" class="form-control" placeholder="Tìm kiếm theo mã phòng, tên người thuê, số điện thoại" value="${param.keyword}">
        </div>
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
    </form>

    <!-- Thêm mới -->
    <a href="${pageContext.request.contextPath}/rooms/create" class="btn btn-success my-3">Tạo mới</a>

    <!-- Bảng danh sách phòng trọ -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><input type="checkbox" id="select-all"></th>
            <th>Mã phòng trọ</th>
            <th>Tên người thuê</th>
            <th>Số điện thoại</th>
            <th>Ngày bắt đầu thuê</th>
            <th>Hình thức thanh toán</th>
            <th>Ghi chú</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td><input type="checkbox" name="selectedRooms" value="${room.id}"></td>
                <td>${room.id}</td>
                <td>${room.tenantName}</td>
                <td>${room.phoneNumber}</td>
                <td>${room.startDate}</td>
                <td>${room.paymentType}</td>
                <td>${room.notes}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/rooms/update?id=${room.id}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="${pageContext.request.contextPath}/rooms/delete?id=${room.id}" class="btn btn-danger btn-sm">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Xóa nhiều -->
    <form action="${pageContext.request.contextPath}/rooms/delete-multiple" method="post">
        <button type="submit" class="btn btn-danger">Xóa đã chọn</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Select/Deselect all checkboxes
    document.getElementById('select-all').addEventListener('change', function () {
        const checkboxes = document.querySelectorAll('input[name="selectedRooms"]');
        checkboxes.forEach(checkbox => checkbox.checked = this.checked);
    });
</script>
</body>
</html>
