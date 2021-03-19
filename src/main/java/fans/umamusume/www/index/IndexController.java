package fans.umamusume.www.index;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.po.SkillDataPO;

public class IndexController extends MyController {

    public void index() {
        //todo remake home page
        title("ウマファン");
        set("skills",SkillDataPO.getAllSkillsList());
        render("index.html");
    }
}
