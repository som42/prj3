<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>프로젝트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<my:navBar current="list"/>

<my:alert></my:alert>

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

<div class="container0lg">
    <div class="row">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">

                <!-- 이전 버튼 -->
<%--                1이 아닐때 보여주면 된다는 뜻 --%>
                <c:if test="${pageInfo.currentPageNum gt 1 }">
                    <c:url value="/list" var="pageLink">
                        <c:param name="page" value="${pageInfo.currentPageNum - 1}"/>
                    </c:url>
                    <li class="page-item">
                        <a class="page-link" href="${pageLink}">
                            <i class="fa-solid fa-angle-left"></i>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="${pageInfo.leftPageNum}" end="${pageInfo.rightPageNum}" var="pageNum">
                    <c:url value="/list" var="pageLink">
                        <c:param name="page" value="${pageNum}"/>
                    </c:url>
                    <li class="page-item">
                        <a class="page-link ${pageNum eq pageInfo.currentPageNum ? 'active' : ''}" href="${pageLink}">${pageNum}</a>
                    </li>
                </c:forEach>

                <!-- 다음 버튼-->
<%--                마지막 페이지보다 작을 때 나오게 한다--%>
                <c:if test="${pageInfo.currentPageNum lt pageInfo.lastPageNum}">
                    <c:url value="/list" var="pageLink">
                        <c:param name="page" value="${pageInfo.currentPageNum + 1}"/>
                    </c:url>
                    <li class="page-item">
                        <a class="page-link" href="${pageLink}">
                            <i class="fa-solid fa-chevron-right"></i>
                        </a>
                    </li>
                </c:if>

            </ul>
        </nav>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


</body>
</html>
