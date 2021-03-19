package fans.umamusume.www.common.skill;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.po.SkillDataPO;

public class SkillController extends MyController {

    public void index() {
        title("技能一览");
        set("skills", SkillDataPO.getAllSkillsList());
        render("index.html");
    }

    public void detail(){
        SkillDataPO skill=SkillDataPO.getSkillData(getParaToInt());
        if(skill==null)
            title("技能不存在");
        else
            title(skill.getName());
        set("skill",skill);
        render("detail.html");
    }

}
