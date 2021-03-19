package fans.umamusume.www.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class NeedLogin implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if (null != controller.getAttr("user"))
            inv.invoke();
        else
            controller.renderError(403);
    }
}
