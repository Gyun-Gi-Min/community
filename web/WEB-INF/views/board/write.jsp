<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <h1>글쓰기</h1>
    <form action="/board/write" method="post">
        <input type="hidden" name="icategory" value="${param.icategory}">
        <div><label>제목 : <input type="text" name="title"></label></div>
        <div><label>내용 : <textarea name="ctnt"></textarea></label></div>
        <div><input type="submit" value="WRITE"></div>
        <!--글쓰기를 눌렀을때 insert가 되도록해보자-->
    </form>
</div>