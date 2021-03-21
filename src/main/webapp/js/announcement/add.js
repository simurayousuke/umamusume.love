$("#form-announcement-add").submit(function (e) {
    e.preventDefault();
    console.log("submit");
    let button = $("#button-calc");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    $.post1("/api/v1/announcement/add", data, function (data) {
        if ("ok" === data.state) {
            $.ok("ok");
            button.prop("disabled", false);
        }else{
            $.error(__res.fail);
            button.prop("disabled", false);
        }
    }, button);
});