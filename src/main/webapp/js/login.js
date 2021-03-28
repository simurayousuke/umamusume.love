$("#form-login").submit(function (e) {
    e.preventDefault();
    if(!recaptcha_ready){
        $.error("请先勾选验证码");
        return;
    }
    $.post2("/api/v1/login", $(this), function (data) {
        if ("ok" === data.state) {
            location.href = "/";
        } else {
            $.error(data.msg);
        }
    });
    grecaptcha.reset();
});
