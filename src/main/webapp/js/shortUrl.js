$("#form-sUrl").submit(function (e) {
    e.preventDefault();
    let button = $("#button-sUrl");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    let date = $.formatDate(new Date());
    if (!data.url) {
        $.error(__res.requireMiss);
        button.prop("disabled", false);
        return;
    }
    $.post1("/api/v1/shortUrl/create", data, function (data) {
        if ("ok" === data.state) {
            $.ok(__res.success, () => {
                $("#container-form-sUrl").hide();
                $("#result-sUrl").val("https://zhuangcloud.cn/s/" + data.sUrl);
                $("#container-result-sUrl").show();
            });
        }else{
            $.error(__res.fail);
            button.prop("disabled", false);
        }
    }, button);
});

let clipboardToken = new ClipboardJS("#button-copy-sUrl");
clipboardToken.on('success', function (e) {
    $.ok(__res.copySuccess);
    e.clearSelection();
});

clipboardToken.on('error', function (e) {
    $.error(__res.copyFail);
});