let recaptcha_ready=false;
function recaptcha_error_callback(){
    recaptcha_ready=false;
    $.error("验证时发生错误，请重新勾选");
}

function recaptcha_callback(g_recaptcha_response){
    recaptcha_ready=true;
}

function recaptcha_expired_callback(){
    recaptcha_ready=false;
    $.error("验证码已过期，请重新勾选");
}