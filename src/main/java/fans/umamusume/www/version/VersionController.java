package fans.umamusume.www.version;

import fans.umamusume.www.common.base.MyController;

public class VersionController extends MyController {

    public void index() {
        title("版本记录");
        render("index.html");
    }

}
