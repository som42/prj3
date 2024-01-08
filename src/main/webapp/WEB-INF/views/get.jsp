<%--
  Created by IntelliJ IDEA.
  User: dkxha
  Date: 2024-01-08
  Time: 오후 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container-lg">
    <h1>${board.id}번 게시물 보기</h1>
    <div>
        제목 : ${board.title}
    </div>
    <div>
        본문 : <div>${board.body}</div>
    </div>
    <div>
        작성자 : ${board.writer}
    </div>
    <div>
        작성일시 : ${board.inserted}
    </div>
    <div>
<%--        수정 폼이 있는 곳으로 이동하기--%>
        <a class="btn btn-secondary" href="/modify/${board.id}">수정</a>
        <button id="removeButton" class="btn btn-danger" form="removeForm" type="submit">삭제</button>
    </div>
</div>

<div class="d-none">
    <form action="/remove" method="post" id="removeForm">
        <input type="text" name="id" value="${board.id}" />
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
    $("#removeButton").click(function(e){
      // 서브밋 진행 이벤트 막기
      e.preventDefault();

      const res = confirm("삭제 하시겠습니까?");
      if (res) {
        // 서브밋 실행
        $("#removeForm").submit();
      }
    });
</script>
<c:if test="${not empty param.success}">
    <script>
      alert("게시물 수정되었습니다.");
    </script>
</c:if>
</body>
</html>
