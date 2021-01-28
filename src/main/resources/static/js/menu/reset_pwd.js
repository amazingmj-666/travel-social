$("#form-user-resetPwd").validate({
    rules:{
        oldPassword:{
            required:true,
            remote: {
                url: ctx + "/checkPassword",
                type: "get",
                dataType: "json",
                data: {
                    password: function() {
                        return $("input[name='oldPassword']").val();
                    }
                }
            }
        },
        newPassword: {
            required: true,
            minlength: 6,
            maxlength: 20,
            isdiff:true,
        },
        confirm: {
            required: true,
            minlength: 6,
            equalTo: "#newPassword"
        }
    },
    messages: {
        oldPassword: {
            required: "请输入原密码",
            remote: "原密码错误"
        },
        newPassword: {
            required: "请输入新密码",
            minlength: "密码不能小于6个字符",
            maxlength: "密码不能大于20个字符",
            isdiff: "新密码不能和旧密码一样"
        },
        confirm: {
            required: "请再次输入新密码",
            equalTo: "两次密码输入不一致"
        }

    },
    focusCleanup: true
});

function submitHandler() {
    if ($.validate.form()) {
        $.operate.save(ctx + "resetPwd", $('#form-user-resetPwd').serialize());
    }
}