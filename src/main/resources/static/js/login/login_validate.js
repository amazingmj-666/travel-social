$(document).ready(function(){
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function() {
        login();
    }
});
//点击登录
function login(){
    var data = {};
    data.phonenumber = $.trim($("input[name=phonenumber]").val());
    data.password = $.trim($("input[name=password]").val());
    data.validateCode = $.trim($("input[name=validateCode]").val());
    $.ajax({
        url: ctx + "login",
        async : true,
        type: "post",
        data: data,
        success: function (result) {
            if (result =="noExist"){
                swal({
                    title:"账号或密码错误",
                    type:"error",
                    customClass: "sa"
                },function(isConfirm){
                    createCode();
                });
            }
            if (result == "success"){
                location.href = ctx + 'index';
            }
            if(result == "error"){
                swal({
                    title:"系统出现异常",
                    type:"error",
                    customClass: "sa"
                },function(isConfirm){
                    createCode();
                });
            }
        }
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#loginForm").validate({
        rules: {
            phonenumber: {
                required: true,
                minlength: 11,
                isMobile: true,
                remote: {
                    url: ctx + "/checkPhoneNumberIsExist",
                    type: "get",
                    dataType: "json",
                    data: {
                        phonenumber: function() {
                            return $("input[name='phonenumber']").val();
                        }
                    }
                }
            },
            password: {
                required: true,
                minlength: 6,
                remote: {
                    url: ctx + "/checkPasswordIsExist",
                    type: "get",
                    dataType: "json",
                    data: {
                        phonenumber: function() {
                            return $("input[name='phonenumber']").val();
                        },
                        password: function() {
                            return $("input[name='password']").val();
                        }
                    }
                }

            },
            validateCode: {
                required: true,
                minlength: 4,
                maxlength: 6,
                imageValidate: true
            }
        },
        messages: {
            phonenumber: {
                required: icon + "请输入您的手机号！",
                minlength: icon + "手机号位数不能少于11位！",
                isMobile: icon + "请填写正确格式的手机号！",
                remote: icon + "该手机号未注册,请先注册"
            },
            password: {
                required: icon + "请输入密码！",
                minlength: icon + "密码位数不能少于6位！",
                remote: icon + "密码错误"
            },
            validateCode: {
                required: icon + "请输入验证码！",
                minlength: icon + "验证码位数为4位",
                maxlength: icon + "验证码位数为4位",
                imageValidate: icon + "验证码错误"
            }
        }
    })
    //手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");
    //图片验证码验证
    jQuery.validator.addMethod("imageValidate", function(value, element) {
        if(document.getElementById("validateCode").value.toUpperCase()!=code.toUpperCase()){ //toUpperCase不区分大小写
            createCode();//读取文件
            return false;
        }else{
            return true;
        }
    }, "验证码错误");


}

