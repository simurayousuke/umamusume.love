package fans.umamusume.www.version;

import fans.umamusume.www.common.base.MyController;

public class VersionController extends MyController {

    public void index() {
        title("version");
        render("index.html");
    }

}
