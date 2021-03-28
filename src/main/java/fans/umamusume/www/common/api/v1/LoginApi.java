package fans.umamusume.www.common.api.v1;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.interceptor.NeedLogin;
import fans.umamusume.www.common.kit.HttpPost;
import fans.umamusume.www.login.LoginService;

public class LoginApi extends ApiV1 {

    @Inject
    LoginService loginService;

    @Clear(NeedLogin.class)
    public void index(String username, String password) {
        String recaptcha = getPara("g-recaptcha-response");
        if (StrKit.isBlank(recaptcha)) {
            fail("请勾选验证码");
            return;
        }
        if (!HttpPost.recaptcha(recaptcha)) {
            fail("验证码无效或已超时，请重试");
            return;
        }
        String token = loginService.login(username, password);
        if (null == token) {
            fail(getRes().get("wrongPassword"));
            return;
        }
        setCookie("token", token, 60 * 60 * 24);
        success(Ret.by("token", token));
    }

}
