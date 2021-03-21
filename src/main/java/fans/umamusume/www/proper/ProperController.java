package fans.umamusume.www.proper;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.po.ProperPO;
import fans.umamusume.www.common.po.TextSet;

public class ProperController extends MyController {

    public void index() {
        title("适性修正一览");
        set("list_distance", ProperPO.getProperList(ProperPO.type.distance));
        set("list_ground", ProperPO.getProperList(ProperPO.type.ground));
        set("list_style", ProperPO.getProperList(ProperPO.type.style));
        set("list_rarity", TextSet.PROPER_RARITY_LIST);
        render("index.html");
    }

}
