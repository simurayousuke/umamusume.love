$("#form-lottery").submit(function (e) {
    e.preventDefault();
    if(!recaptcha_ready){
        $.error("请先勾选验证码");
        return;
    }
    $.post2("/api/v1/lottery/join", $(this), function (data) {
        if ("ok" === data.state) {
            $.ok("您已应募成功",()=>{
                location.reload();
            });
        } else {
            $.error(data.msg);
        }
    });
    grecaptcha.reset();
});
