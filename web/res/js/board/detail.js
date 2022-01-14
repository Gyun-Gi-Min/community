{
const dataElem =document.querySelector('#data');

//삭제
const delBtnElem = document.querySelector("#btnDel");
if(delBtnElem){
    delBtnElem.addEventListener('click',()=>{
        const icategory = dataElem.dataset.icategory;
        const iboard = dataElem.dataset.iboard;
        if(confirm(msg.fnIsDel(`${iboard}번 글`))){
            location.href=`/board/del?icategory=${icategory}&iboard=${iboard}`;
        }
    });
}

    //수정버튼
    const modBtnElem = document.querySelector("#btnMod");
    if(modBtnElem){
        modBtnElem.addEventListener('click',() =>{
            const iboard = dataElem.dataset.iboard;
            location.href=`/board/mod?iboard=${iboard}`;
        });
    }

    const cmtFrmElem = document.querySelector('#cmtFrm');
    if(cmtFrmElem){ //true : 로그인 상태
        cmtFrmElem.btn_submit.addEventListener('click',() =>{
            const cmtVal = cmtFrmElem.ctnt.value;
            if(cmtVal.length === 0) {
                alert('댓글 내용을 작성해 주세요');
            }else if(regex.isWrongWith('ctnt',cmtVal)){
                alert(regex.msg.ctnt);
            }else {
                insBoardCmtAjax(cmtVal);
            }
        });

        const insBoardCmtAjax = (val) => {
            const param = {
                'iboard' : dataElem.dataset.iboard,
                'ctnt' : val
            };
            myFetch.post('/board/cmt',param,(data) => {
                console.log(data);
            });
        }
    }
}



