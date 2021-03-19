$("#form-kofuku").submit(function (e) {
    e.preventDefault();
    let button = $("#button-kofuku");
    button.prop("disabled", true);
    let data = $(this).serializeObject();
    $.post1("/api/v1/fujishiro/kofuku", data, function (data) {
        if ("ok" === data.state) {
            $.ok("提出成功しました！", () => {
                window.location.href="/fujishiro/kofuku";
            });
        }else{
            $.error("提出できませんでした。");
            button.prop("disabled", false);
        }
    }, button);
});