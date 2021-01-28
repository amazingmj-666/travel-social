$(document).ready(function(){
    validateRule();

});
$.validator.setDefaults({
    submitHandler: function() {
        searchCustom();
    }
});
//点击搜索好友
function searchCustom(){
    var data = {};
    data.country = $.trim($("input[name=searchContent]").val());
    $.ajax({
        url: ctx + "search_custom",
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
                required: icon + "请输入国家名称"
            }
        }
    })
}
