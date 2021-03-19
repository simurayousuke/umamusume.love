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
    $.jump("/statistic/" + inputDate.val());
});

$(document).ready(function () {
    initData();
});