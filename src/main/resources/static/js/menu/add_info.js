$(document).ready(function(){
    validateRule();
    'use strict';

    var $citypicker1 = $('#city-picker1');
    $citypicker1.citypicker();

    var $citypicker2 = $('#city-picker2');

    $citypicker2.citypicker({
        province: '江苏省',
        city: '常州市',
        district: '溧阳市'
    });
    var $citypicker3 = $('#city');

    $('#reset').click(function () {
        $citypicker3.citypicker('reset');
    });

    $('#destroy').click(function () {
        $citypicker3.citypicker('destroy');
    });
});
$.validator.setDefaults({
    submitHandler: function() {
        addInfo();
    }
});
//点击完善资料
function addInfo(){
    var data = {};
    data.city = $.trim($("input[name=city]").val());
    data.userName = $.trim($("input[name=userName]").val());
    data.email = $.trim($("input[name=email]").val());
    data.birthday = $.trim($("input[name=birthday]").val());
    data.hobby = $.trim($("textarea[name=hobby]").val());
    data.sex = $.trim($('input[name= "sex"]:checked').val());
    $.ajax({
        url: ctx + "add_info",
        async : true,
        type: "post",
        data: data,
        success: function (result) {
            if (result == "success"){
                swal({
                    title:"",
                    text:"完善资料成功",
                    type:"success",
                    customClass: "sa"
                },function(isConfirm){
                    location.href = ctx + 'profile';
                });
            }
            if(result == "fail"){
                swal({
                    title:"",
                    text:"完善资料失败，请重新填写",
                    type:"success",
                    customClass: "sa"
                });
            }
        }
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#form").validate({
        rules: {
            city: {
                required: true
            },
            userName: {
                required: true,
                minlength: 2,
                maxlength: 8,
                remote: {
                    url: ctx + "/checkUserName",
                    type: "get",
                    dataType: "json",
                    data: {
                        userName: function() {
                            return $("input[name='userName']").val();
                        }
                    }
                }
            },
            email: {
                required: true,
                email: true,
                remote: {
                    url: ctx + "/checkEmail",
                    type: "get",
                    dataType: "json",
                    data: {
                        email: function() {
                            return $("input[name='email']").val();
                        }
                    }
                }
            },
            birthday:{
                required: true
            }
        },
        messages: {
            city: {
                required: icon + "请选择您的城市"
            },
            userName: {
                required: icon + "请输入您的昵称",
                minlength: icon + "昵称不能少于2位！",
                maxlength: icon + "昵称不能多于8位",
                remote: icon + "该昵称已被使用"
            },
            email: {
                required: icon + "请输入您的邮箱",
                email: icon + "邮箱格式不正确",
                remote: icon + "该邮箱已被使用"
            },
            birthday:{
                required: icon + "请选择您的生日日期",
            }
        }
    })
}

