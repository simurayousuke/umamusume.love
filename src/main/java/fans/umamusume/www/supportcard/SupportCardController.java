package fans.umamusume.www.supportcard;

import fans.umamusume.www.common.base.MyController;

public class SupportCardController extends MyController {

    public void index(){
        title("支援卡一览");
        render("index.html");
    }

    public void detail(){
        title("马娘粉");
        render("detail.html");
    }

    public void rank(){
        title("马娘粉");
        render("rank.html");
    }

}
