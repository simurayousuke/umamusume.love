package fans.umamusume.www.reg;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.login.LoginService;
import com.jfinal.aop.Inject;

public class RegController extends MyController {

    @Inject
    LoginService loginService;

    public void index() {
        if (loginService.isLogin(getCookie("token"))) {
            redirect("/");
            return;
        }
        title("reg");
        render("index.html");
    }

}
