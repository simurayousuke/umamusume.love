package fans.umamusume.www.reg;

import com.jfinal.aop.Inject;
import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.login.LoginService;

public class RegController extends MyController {

    @Inject
    LoginService loginService;

    public void index() {
        if (loginService.isLogin(getCookie("token"))) {
            redirect("/");
            return;
        }
        title("注册");
        render("index.html");
    }

}
