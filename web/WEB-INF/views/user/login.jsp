<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<h1>로그인</h1>
<div>${requestScope.msg}</div>

<form action="/user/login" method="post" id="login_frm">
    <div><label>id : <input type="text" name="uid" value="${requestScope.tryLogin.uid}"></label></div>
    <div><label>password : <input type="password" name="upw" ></label></div>
    <div>
        <input type="submit" value="LOGIN">
    </div>
</form>
<div><a href="/user/join">join</a></div>

<!--
    로그인 처리
    세션에 USerEntity 객체 주소값 저장하는데
    키값은 Const.LOGIN_USER
    객체에 iuser uid nm profileimg 값 저장
    로그인 성공시 /board/list 주소값
    실패시 login.jsp에서 메시지 출력
    (아이디 없음 비밀번호 확인 알수없는 에러)
-->