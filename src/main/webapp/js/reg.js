$("#form-login").submit(function (e) {
    e.preventDefault();
    let data = $(this).serializeObject();
    if(!recaptcha_ready){
        $.error("请先勾选验证码");
        return;
    }
    if (!(data.username && data.password && data.confirm)) {
        $.error(__res.requireMiss);
        return;
    }
    if (data.password !== data.confirm) {
        $.error(__res.passwordNotSame);
        return;
    }
    $.post1("/api/v1/reg", data, function (data) {
        if ("ok" === data.state) {
            location.href = "/login";
        } else {
            $.error(data.msg);
        }
    });
    grecaptcha.reset();
});
