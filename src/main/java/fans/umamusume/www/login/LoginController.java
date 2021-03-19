package fans.umamusume.www.login;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import fans.umamusume.www.common.base.MyController;

public class LoginController extends MyController {

    @Inject
    LoginService loginService;

    public void index() {
        if (loginService.isLogin(getCookie("token"))) {
            redirect("/");
            return;
        }
        title("登录");
        render("index.html");
    }

    @ActionKey("/logout")
    public void logout() {
        loginService.logout(getCookie("token"));
        removeCookie("token");
        redirect("/login");
    }

}
