{
    const dataElem = document.querySelector('#data');

    //삭제 버튼
    const delBtnElem = document.querySelector('#delBtn');
    if(delBtnElem) {
        delBtnElem.addEventListener('click', ()=> {
            const icategory = dataElem.dataset.icategory;
            const iboard = dataElem.dataset.iboard;

            if(confirm(msg.fnIsDel(`${iboard}번 글`))) {
                location.href=`/board/del?icategory=${icategory}&iboard=${iboard}`;
            }
        });
    }

    //수정 버튼
    const modBtnElem = document.querySelector('#modBtn');
    if(modBtnElem) {
        modBtnElem.addEventListener('click', ()=> {
            const iboard = dataElem.dataset.iboard;
            location.href=`/board/mod?iboard=${iboard}`;
        });
    }

    const cmtFrmElem = document.querySelector('#cmtFrm');
    if(cmtFrmElem) { // true: 로그인 한 상태

        //input-text ctnt에서 엔터치면 submit날아가기 때문에 막는다.
        cmtFrmElem.addEventListener('submit', (e)=> {
            e.preventDefault();
        });

        cmtFrmElem.btn_submit.addEventListener('click', () => {
            const cmtVal = cmtFrmElem.ctnt.value;
            if(cmtVal.length === 0) {
                alert('댓글 내용을 작성해 주세요.');
            } else if(regex.isWrongWith('ctnt', cmtVal)) {
                alert(regex.msg.ctnt);
            } else {

                insBoardCmtAjax(cmtVal);
            }
        });

        const insBoardCmtAjax = (val) => {
            const param = {
                'iboard': dataElem.dataset.iboard,
                'ctnt': val
            };
            myFetch.post('/board/cmt', (data) => {
                console.log(data);
            }, param);
        }
    }


    const cmtListElem = document.querySelector('#cmt_list');
    if(cmtListElem){ //통신시작!
        const getCmtList = () =>{
            const iboard = dataElem.dataset.iboard;
            myFetch.get(`/board/cmt/${iboard}`,(list) =>{
                console.log(list);
                setCmtList(list);
            });
        }

        //통신 결과물 세팅
        const setCmtList = (list) => {

            if(list.length === 0){
                cmtListElem.innerText = '댓글 없음';
                return;
            }

            const table = document.createElement('table');
            table.innerHTML= `
                <tr>
                    <th>no</th>
                    <th>content</th>
                    <th>writer</th>
                    <th></th>
                </tr>
            `;
            list.forEach(item =>{
                const tr = document.createElement('tr');

                const imgSrc =  item.profileimg === null
                    ?  'res/img/defaultProfile.jpg'
                    : `/images/user/${item.iuser}/${item.profileimg}`;

                tr.innerHTML= `
                    <td>${item.icmt}</td>
                    <td>${item.ctnt}</td>
                    <td>
                        <span>${item.writernm}</span>
                        <div class="circular--img wh-30">
                            <img src="${imgSrc}" onerror="this.style.display='none';">                  
                        </div>
                    </td>
                `;

                const td= document.createElement('td');
                tr.appendChild(td);

                table.appendChild(tr);
            });
            cmtListElem.appendChild(table);
f        }

        getCmtList();
    }


}

// Restful API > POST, GET, PUT, DELETE