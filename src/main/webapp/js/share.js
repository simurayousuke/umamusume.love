let inputDate = $("#input-date-statistic");

inputDate.val(dateStatistic);

$("#input-date-statistic").datetimepicker(
    {
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy-mm-dd",
        initialDate: dateStatistic,
        language: __locale.substring(0, 2)
    }).on('changeDate', function (ev) {
    $.jump($.buildShareLink(shareToken, inputDate.val()));
});

function initDate(type) {
    if ($.getPara("shorten") === "true" && parseInt(type) === 0) {
        dateStatistic = $.formatDate(new Date());
        inputDate.val(dateStatistic);
    }
    $("#display-date").text(
        $("#display-date").text()+dateStatistic
    );
}

function checkPermission() {
    $.post4("/api/v1/share/check", {token: shareToken, date: dateStatistic}, (data) => {
        if (data.access) {
            if (parseInt(data.type ?? 0) === 2) {
                $("#container-input-date").hide();
            }
            initDate(data.type);
            initData();
        } else {
            $.error(__res.shareTokenNoPermission, () => {
                $.jump("/error/403");
            });
        }
    }, () => {
        $.error(__res.networkError, () => {
            checkPermission();
        });
    });
}

$(document).ready(function () {
    checkPermission();
});