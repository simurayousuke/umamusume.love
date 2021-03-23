package fans.umamusume.www.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import fans.umamusume.www.common.model.User;

public class NeedAdmin implements Interceptor {

    private static final int[] admin_ids = new int[]{1, 10};

    private static boolean isAdmin(int uid) {
        for (int admin : admin_ids)
            if (admin == uid)
                return true;
        return false;
    }

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        User user = controller.getAttr("user");
        if (null != user && user.getPriority() == 999)
            inv.invoke();
        else
            controller.renderError(403);
    }
}
