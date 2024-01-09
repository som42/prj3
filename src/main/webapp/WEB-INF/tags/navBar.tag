<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="current"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style>
    /* 네비게이션 바를 위한 사용자 정의 스타일 */
    .navbar {
        background-color: #ffd9e6; /* 연한 핑크 배경색상 */
        border-radius: 10px; /* 네비게이션 바의 모서리를 둥글게 만듭니다. */
        font-family: 'Noto Sans KR', sans-serif; /* 한글을 위한 글꼴 지정 */
    }

    .nav-link {
        border-radius: 5px; /* 네비게이션 링크의 모서리를 둥글게 만듭니다. */
        font-weight: bold; /* 글꼴을 굵게 설정합니다. */
    }

    .nav-link.active {
        color: #ffffff; /* 활성 링크의 텍스트 색상을 흰색으로 설정 */
        background-color: #ff66b2; /* 활성 링크의 배경 색상을 조금 더 진한 핑크로 설정 */
        border-radius: 5px; /* 활성 링크에 둥근 모서리 추가 */
    }
</style>

<nav class="navbar navbar-expand-lg bg-body-tertiary mb-5">
    <div class="container-lg">
        <a class="navbar-brand" href="/list">
            리아게시판
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link ${current eq 'list' ? 'active' : '' }" href="/list">목록</a>
                </li>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link ${current eq 'add' ? 'active' : '' }" href="/add">글작성</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link ${current eq 'signup' ? 'active' : '' }" href="/member/signup">회원가입</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasAuthority('admin')">
                    <li class="nav-item">
                        <a class="nav-link ${current eq 'memberList' ? 'active' : '' }" href="/member/list">회원목록</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link ${current eq 'memberInfo' ? 'active' : '' }" href="/member/info?id=<sec:authentication property="name" />">회원정보</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link ${current eq 'login' ? 'active' : '' }" href="/member/login">로그인</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/member/logout">로그아웃</a>
                    </li>
                </sec:authorize>
            </ul>
            <form action="/list" class="d-flex" role="search">

                <div class="input-group">
                    <select class="form-select flex-grow-0" style="width: 100px;" name="type" id="">
                        <option value="all">전체</option>
                        <option value="title" ${param.type eq 'title' ? 'selected' : '' }>제목</option>
                        <option value="body" ${param.type eq 'body' ? 'selected' : '' }>본문</option>
                        <option value="writer" ${param.type eq 'writer' ? 'selected' : '' }>작성자</option>
                    </select>

                    <input value="${param.search }" name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
</nav>




