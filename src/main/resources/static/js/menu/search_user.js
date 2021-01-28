$(document).ready(function(){
    validateRule();

});
$.validator.setDefaults({
    submitHandler: function() {
        searchUser();
    }
});
//点击搜索好友
function searchUser(){
    var data = {};
    data.searchContent = $.trim($("input[name=searchContent]").val());
    $.ajax({
        url: ctx + "search_user",
        async : true,
        type: "post",
        data: data,
        success: function (result) {
        }
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#search_form").validate({
        rules: {
            searchContent: {
                required: true
            }
        },
        messages: {
            searchContent: {
                required: icon + "请输入昵称/手机号！"
            }
        }
    })
}
