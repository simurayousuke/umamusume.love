package fans.umamusume.www.share;

import fans.umamusume.www.common.base.MyController;

public class ShareController extends MyController {

    public void index(){
        title("种马支援卡搜索");
        render("index.html");
    }

    public void config(){
        title("我的种马支援卡");
        render("config.html");
    }

}
