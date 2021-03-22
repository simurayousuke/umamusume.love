$("#form-announcement-add").submit(function (e) {
    e.preventDefault();
    console.log("submit");
    let button = $("#button-announce");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    $.post1("/api/v1/announcement/modify", data, function (data) {
        if ("ok" === data.state) {
            $.ok("ok", () => {
                window.opener=null;
                window.open('','_self');
                window.close();
            });
            button.prop("disabled", false);
        } else {
            $.error(__res.fail);
            button.prop("disabled", false);
        }
    }, button);
});