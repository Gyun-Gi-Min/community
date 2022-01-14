<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div>
        <button id="btnMod">수정</button>
        <button id="btnDel">삭제</button>
    </div>
    <%--삭제 버튼 클릭시 삭제하시겠습니까? confirm창
    긍정 클릭시 주소이동 /board/del?iboard=? --%>

</c:if>

<div id="data" data-icategory="${data.icategory}" data-iboard="${data.iboard}"></div>

<div>
    <div>카테고리 : ${data.categorynm}</div>
    <div>조회수 : ${data.hits} | 등록일시 : ${data.rdt}</div>
    <div>글쓴이 : ${data.writernm}</div>
    <div>제목 : <c:out value="${data.title}"/> </div>
    <hr>
    <div>내용 : <c:out value="${data.ctnt}"/>  </div>
</div>

<div>
    <c:if test="${requestScope.prevNext.previboard > 0}">
       <a href="/board/detail?iboard=${requestScope.prevNext.previboard}"><input type="button" value="이전글s"></a>
    </c:if>
    <c:if test="${requestScope.prevNext.nextiboard > 0}">
       <a href="/board/detail?iboard=${requestScope.prevNext.nextiboard}"><input type="button" value="다음글"></a>
    </c:if>

</div>

<c:if test="${sessionScope.loginUser !=null}">
    <div class="m-t-20">
        <form id="cmtFrm">
            <input type="text" name="ctnt">
            <input type="button" id="btn_submit" value="댓글달기">
        </form>
    </div>
</c:if>
<div class="m-t-20">댓글 리스트</div>
