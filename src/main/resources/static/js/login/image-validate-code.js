$(document).ready(function() {
    $("#login").click(function(){
        if(document.getElementById("vc").value==""){
            alert("验证码不能为空！");
            createCode();//输错一次或提交一次都将会刷新一次验证码
            return false; //结束本次会话
        }else if(document.getElementById("vc").value.toUpperCase()!=code.toUpperCase()){ //toUpperCase不区分大小写
            alert("您输入的验证码有误，请重新输入！！");
            createCode();//读取文件
        }
        else{
            location.href = ctx + 'index';
        }
    });
});
