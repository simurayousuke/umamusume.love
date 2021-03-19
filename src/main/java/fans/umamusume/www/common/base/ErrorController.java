package fans.umamusume.www.common.base;

import com.jfinal.core.ActionKey;

public class ErrorController extends MyController {

    @ActionKey("/404")
    public void error404() {
        renderError(404);
    }

    @ActionKey("/403")
    public void error403() {
        renderError(403);
    }

    @ActionKey("/500")
    public void error500() {
        renderError(500);
    }

    public void index() {
        renderError(Integer.parseInt(getPara()));
    }

}
