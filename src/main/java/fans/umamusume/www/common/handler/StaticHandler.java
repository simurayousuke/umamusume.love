package fans.umamusume.www.common.handler;

import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaticHandler extends Handler {
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        if (target.equals("/js/res.js")) {
            next.handle("/js/res", request, response, isHandled);
            response.setContentType("application/javascript");
        } else if (target.startsWith("/view")) {
            HandlerKit.renderError404(request, response, isHandled);
        } else {
            next.handle(target, request, response, isHandled);
        }
    }
}
