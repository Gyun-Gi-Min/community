// 리스트 클에 마우스 올렸을때(hover)
// cursor 모양 손 모양
// 배경색 변경
// 눌렀을때 주소이동
// /board/detail?ibroad=?

// {} 주는 이유는 scope 문제때문에 혹시나해서 하는것.

{
    const recordList = document.querySelectorAll('.record');
    //All 했기때문에 배열로 넘어온다.

    const recordEvent = (item) => {
        item.addEventListener('click', () => {
            const iboard = item.dataset.iboard;
            console.log(iboard);
            location.href = `/board/detail?iboard=${iboard}`;
            //  ` 템플릿 리터럴 (백틱)
        });
    };
    recordList.forEach(recordEvent);
}