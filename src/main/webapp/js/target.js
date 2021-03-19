$("#form-target").submit(function (e) {
    e.preventDefault();
    let button = $("#button-target");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    if (!(data.targetCalorie && data.targetProtein&& data.targetFat&& data.targetCarbohydrate)) {
        $.error(__res.requireMiss);
        button.prop("disabled", false);
        return;
    }
    $.post1("/api/v1/target", data, function (data) {
        if ("ok" === data.state) {
            $.ok(data.msg, () => {
                location.reload();
            });
        } else {
            button.prop("disabled", false);
            $.error(data.msg);
        }
    }, button);
});