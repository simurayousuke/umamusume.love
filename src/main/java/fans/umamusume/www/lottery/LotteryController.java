package fans.umamusume.www.lottery;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.model.Lottery;
import fans.umamusume.www.common.model.LotteryUser;
import fans.umamusume.www.common.model.User;

import java.util.Date;

public class LotteryController extends MyController {

    public void index() {
        Integer id = getParaToInt();
        Lottery lottery = Lottery.dao.findById(id);
        set("lottery", lottery);
        User user = (User) getAttr("user");
        LotteryUser lotteryUser = null;
        if (user != null & lottery != null)
            lotteryUser = LotteryUser.dao.findFirst("select * from t_lottery_user where lid=? and uid=?",
                    lottery.getId(), user.getUid());
        set("lotteryUser", lotteryUser);
        Date now = new Date();
        set("now", now);
        render("detail.html");
    }

}
