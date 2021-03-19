package fans.umamusume.www.common.skill;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.po.SkillDataPO;

public class SkillController extends MyController {

    public void index() {
        title("技能数据");
        set("skills", SkillDataPO.getAllSkillsList());
        render("index.html");
    }

}
