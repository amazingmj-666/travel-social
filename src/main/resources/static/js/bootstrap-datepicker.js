$(function () {
    $('.date-picker').datepicker({
        rtl: Metronic.isRTL(),
        autoclose: true,
        clearBtn: true, //清除按钮
        todayBtn: false, //今日按钮
        format: "yyyy-mm-dd"
    });
});

