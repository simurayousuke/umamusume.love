package fans.umamusume.www.index;

import fans.umamusume.www.common.base.MyController;

public class IndexController extends MyController {

    public void index() {
        //todo remake home page
        title("马娘粉");
        render("index.html");
    }
}
