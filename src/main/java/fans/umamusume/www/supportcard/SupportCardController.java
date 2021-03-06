package fans.umamusume.www.supportcard;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.po.SupportCardPO;

public class SupportCardController extends MyController {

    public void index(){
        title("支援卡一览");
        set("cards", SupportCardPO.getAllSupportCards());
        render("index.html");
    }

    public void detail(){
        SupportCardPO card=SupportCardPO.getSupportCard(getParaToInt());
        if(card==null)
            title("支援卡不存在");
        else
            title(card.getName());
        set("card",card);
        render("detail.html");
    }

    public void rank(){
        title("建设中");
        render("rank.html");
    }

}
