const msg = {
    isDel : '삭제 하시겠습니까?',

    fnIsDel:function (target){
        return `${target}을/를`  + this.isDel;
    }
}

const regex = {
    id: /^([a-zA-Z0-9]{4,15})$/,
    pw: /^([a-zA-Z0-9!@_]{4,20})$/,
    nm: /^([가-힣]{2,5})$/,
    msg:{
        id: '대소문자_조합으로 4~15글자',
        pw: '대소문자+숫자+!@_ 조합으로 4~20글자인지 확인해 주세요',
        nm: '한글조합으로 2~5글자'
    },
    isWrongWith: function (target,val) {
        return !this[target].test(val);
    }

}