$("#form-login").submit(function (e) {
    e.preventDefault();
    $.post2("/api/v1/login", $(this), function (data) {
        if ("ok" === data.state) {
            location.href = "/";
        } else {
            $.error(data.msg);
        }
    });
});
