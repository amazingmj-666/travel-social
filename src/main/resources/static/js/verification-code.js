$(document).ready(function(){
    //发送验证码
    var wait = 120;
    validateRule();
    $("#sendVerifyCode").click(function(){
        var phonenumber = $("input[name=phonenumber]").val();
        $.ajax({
            url: ctx+"sendSms",
            async : true,
            type: "post",
            data: {
                "phonenumber": phonenumber
            },
            success: function (result) {
                if(result == 'sendsuccess'){
                    swal({
                        title:"",
                        text:"验证码发送成功",
                        type:"success",
                        customClass: "sa"
                    },function time(o) {
                        o = document.getElementById("sendVerifyCode");
                        if (wait == 0) {
                            o.removeAttribute("disabled");
                            o.value="发送验证码";
                            wait = 120;
                        } else {
                            o.setAttribute("disabled", true);
                            o.value="重新发送(" + wait + ")";
                            wait--;
                            setTimeout(function() {
                                    time(o)
                                },
                                1000)
                        }
                    });
                }else{
                    swal({
                        title:"",
                        text:"验证码发送失败",
                        type:"error",
                        customClass: "sa"
                    });
                }
            }
        });
    });
});
$.validator.setDefaults({
    submitHandler: function() {
        register();
    }
});
function time(o) {
    var wait=60;
    if (wait == 0) {
        o.removeAttribute("disabled");
        o.value="发送验证码";
        wait = 60;
    } else {
        o.setAttribute("disabled", true);
        o.value="重新发送(" + wait + ")";
        wait--;
        setTimeout(function() {
                time(o)
            },
            1000)
    }
}
//点击注册
function register(){
    var data = {};
    data.phonenumber = $.trim($("input[name=phonenumber]").val());
    data.password = $.trim($("input[name=password]").val());
    data.validateCode = $.trim($("input[name=validateCode]").val());
    $.ajax({
        url: ctx+"register",
        async : true,
        type: "post",
        data: data,
        success: function (result) {
            if(result == 'errorcode'){
                swal({
                    title:"",
                    text:"验证码错误",
                    type:"error",
                    customClass: "sa"
                });
            }
            if(result == 'sendFirst'){
                swal({
                    title:"",
                    text:"验证码未发送，请先点击发送验证码按钮！",
                    type:"error",
                    customClass: "sa"
                });
            }
            if(result == 'outtime'){
                swal({
                    title:"",
                    text:"验证码超时",
                    type:"error",
                    customClass: "sa"
                });
            }
            if(result == 'success') {
                swal({
                    title:"",
                    text:"注册成功",
                    type:"success",
                    customClass: "sa"
                },function(isConfirm){
                    location.href = ctx + 'login';
                });
            }
            if(result == "fail"){
                swal({
                    title:"",
                    text:"注册失败",
                    type:"error",
                    customClass: "sa"
                },function(isConfirm){
                    location.href = ctx + 'register';
                });
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#registerForm").validate({
        rules: {
            phonenumber: {
                required: true,
                minlength: 11,
                isMobile: true,
                remote: {
                    url: ctx + "/checkPhoneNumber",
                    type: "get",
                    dataType: "json",
                    data: {
                        phonenumber: function() {
                            return $("input[name='phonenumber']").val();
                        }
                    }
                }
            },
            validateCode: {
                required: true
            },
            password: {
                required: true,
                minlength: 6
            },
            repassword: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            }
        },
        messages: {
            phonenumber: {
                required: icon + "请输入您的手机号！",
                minlength: icon + "手机号位数不能少于11位！",
                isMobile: icon + "请填写正确的手机号！",
                remote: icon + "该手机号已被注册"
            },
            validateCode: {
                required: icon + "请输入验证码！"
            },
            password: {
                required: icon + "请输入密码！",
                minlength: icon + "密码位数不能少于6位！"
            },
            repassword: {
                required: icon + "请再次输入密码！",
                minlength: icon + "密码位数不能少于6位！",
                equalTo: icon + "两次密码输入不一致！"
            }
        }
    })
    //手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");


}

