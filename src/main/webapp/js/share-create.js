let defaultMealDate = $.formatDate(new Date());
let inputStartDate = $("#input-startDate");
let inputEndDate = $("#input-endDate");
let containerStartDate = $("#container-startDate");
let containerEndDate = $("#container-endDate");
let labelStartDate = $("#label-startDate");

function switchView(type) {
    switch (parseInt(type)) {
        case 0:
            inputStartDate.val("");
            inputEndDate.val("");
            containerStartDate.hide();
            containerEndDate.hide();
            break;
        case 1:
            inputStartDate.val("");
            inputEndDate.val(defaultMealDate);
            labelStartDate.text(__res.startDate);
            containerStartDate.show();
            containerEndDate.show();
            break;
        case 2:
            inputStartDate.val(defaultMealDate);
            inputEndDate.val("");
            labelStartDate.text(__res.date);
            containerStartDate.show();
            containerEndDate.hide();
            break;
    }
}

switchView($("#input-share-type").val());

$(".form-date").datetimepicker(
    {
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy-mm-dd",
        initialDate: defaultMealDate,
        language: __locale.substring(0, 2)
    });

$("#input-share-type").change((e) => {
    switchView(e.target.value);
});

$("#form-share").submit(function (e) {
    e.preventDefault();
    let button = $("#button-share");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    let date = $.formatDate(new Date());
    switch (parseInt(data.type)) {
        case 0:
            delete data.startDate;
            delete data.endDate;
            break;
        case 1:
            if (!(data.startDate && data.endDate)) {
                $.error(__res.requireMiss);
                button.prop("disabled", false);
                return;
            }
            date = data.startDate;
            break;
        case 2:
            delete data.endDate;
            if (!data.startDate) {
                $.error(__res.requireMiss);
                button.prop("disabled", false);
                return;
            }
            date = data.startDate;
            break;
    }
    $.post1("/api/v1/share/create", data, function (data) {
        if ("ok" === data.state) {
            let url = $.buildShareLink(data.token, date, __locale);
            $.post4("/api/v1/shortUrl/create", {url: url + "&shorten=true"}, function (data) {
                if ("ok" === data.state) {
                    url = __shortUrlBase + data.sUrl;
                }
            }, () => {
            }, () => {
                $.ok(__res.shareSuccess, () => {
                    $("#container-form-share").hide();
                    $("#result-token").val(url);
                    $("#container-result").show();
                });
            });
        } else {
            button.prop("disabled", false);
            $.error(data.msg);
        }
    }, button);
});

let clipboardToken = new ClipboardJS("#button-copy-token");
clipboardToken.on('success', function (e) {
    $.ok(__res.copySuccess);
    e.clearSelection();
});

clipboardToken.on('error', function (e) {
    $.error(__res.copyFail);
});