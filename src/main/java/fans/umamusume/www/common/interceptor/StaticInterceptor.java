package fans.umamusume.www.common.interceptor;

import fans.umamusume.www.common.Start;
import fans.umamusume.www.login.LoginService;
import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class StaticInterceptor implements Interceptor {

    @Inject
    LoginService loginService;

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        controller.set("devMode", Start.devMode);
        controller.set("version", Start.version);
        controller.set("user", loginService.findByToken(controller.getCookie("token")));
        inv.invoke();
    }
}
