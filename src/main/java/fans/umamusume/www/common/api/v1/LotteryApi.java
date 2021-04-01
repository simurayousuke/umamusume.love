package fans.umamusume.www.common.api.v1;

import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.model.Lottery;
import fans.umamusume.www.common.model.LotteryUser;
import fans.umamusume.www.common.model.User;

import java.util.Date;

public class LotteryApi extends ApiV1 {

    public void join() {
        Integer id=getParaToInt("id");
        String email=getPara("email");
        Lottery lottery = Lottery.dao.findById(id);
        User user = (User) getAttr("user");
        if (user == null | lottery == null) {
            fail("抽奖不存在或未登陆");
            return;
        }
        Date now = new Date();
        if (now.compareTo(lottery.getStartTime()) < 0) {
            fail("抽奖还未开始");
            return;
        } else if (now.compareTo(lottery.getEndTime()) > 0) {
            fail("抽奖已经结束");
            return;
        }
        LotteryUser lotteryUser = LotteryUser.dao.findFirst("select * from t_lottery_user where lid=? and uid=?",
                lottery.getId(), user.getUid());
        if (lotteryUser != null) {
            fail("您已应募过了");
            return;
        }
        lotteryUser = new LotteryUser();
        lotteryUser.setLid(lottery.getId());
        lotteryUser.setUid(user.getUid());
        lotteryUser.setEmail(email);
        if (lotteryUser.save()) {
            success();
        } else {
            fail("应募失败");
        }
    }

}
