function init247(){
    let button = $("#button-init247");
    button.prop("disabled", true);
    $.post1("/api/v1/log/init247", {}, function (data) {
        if ("ok" === data.state) {
            $.ok(data.msg, () => {
                $.jump("/statistic");
            });
        } else {
            button.prop("disabled", false);
            $.error(data.msg);
        }
    }, button);
}