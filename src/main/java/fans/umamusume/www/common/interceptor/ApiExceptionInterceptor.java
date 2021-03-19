package fans.umamusume.www.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.ActionException;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiExceptionInterceptor implements Interceptor {

    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionInterceptor.class);

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        try {
            inv.invoke();
        } catch (ActionException e) {
            controller.renderJson(Ret.fail("code", e.getErrorCode()).set("msg", e.getErrorCode()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            controller.renderJson(Ret.fail("code", 500).set("msg", "Server error"));
        }
    }
}
