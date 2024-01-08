<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>프로젝트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container-lg">
<h1>게시물 목록 보기</h1>
<%--    table.table>thead>tr>th4*^^tbody--%>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board">
            <tr>
                <td>${board.id}</td>
                <td>
<%--                    클릭하면 경로 넘어 가게 하는거--%>
                    <a href="/id/${board.id}">
                            ${board.title}
                    </a>
                </td>
                <td>${board.writer}</td>
                <td>${board.inserted}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<c:if test="${param.success eq 'remove'}">
    <script>
        alert("게시물이 삭제 되었습니다.");
    </script>
</c:if>
</body>
</html>
