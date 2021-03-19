package fans.umamusume.www.common.interceptor;

import fans.umamusume.www.common.Start;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.ActionException;
import com.jfinal.core.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExceptionInterceptor implements Interceptor {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        try {
            inv.invoke();
        } catch (ActionException e) {
            int ec = e.getErrorCode();
            if (ec == 500 || ec == 403 || ec == 404)
                controller.render("/view/common/" + ec + ".html");
            else
                throw e;
        } catch (Throwable e) {
            if (Start.devMode)
                System.out.println(e.getMessage());
            LOG.error(e.getMessage(), e);
            controller.renderError(500);
        }
    }
}
