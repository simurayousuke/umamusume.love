package fans.umamusume.www.common.api.v1;

import com.jfinal.aop.Clear;
import com.jfinal.core.NotAction;
import com.jfinal.kit.Ret;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.interceptor.NeedLogin;
import fans.umamusume.www.common.po.SupportCardPO;
import fans.umamusume.www.common.po.SupportEffectTablePO;

import java.util.HashMap;
import java.util.Map;

public class SupportCardApi extends ApiV1 {

    @NotAction
    public static Map<String, Integer> calc(int id, int lv) {
        SupportCardPO supportCard = SupportCardPO.getSupportCard(id);
        Map<String, Integer> status = new HashMap<>();
        for (SupportEffectTablePO effect : supportCard.getSupportEffectTable()) {
            //String key=effect.getType_name();
            String key=effect.getType_description();
            int unlockLv = effect.getUnlockLevel();
            if (unlockLv > 0 && lv < unlockLv)
                continue;
            int nextIndex = effect.getNextLimit(lv);
            if (nextIndex == -1) {
                int preIndex = effect.getPreLimit(lv);
                if (preIndex == -1) {
                    continue;
                }
                int preValue = effect.limit_values[preIndex];
                status.put(key, preValue);
                continue;
            }
            int nextLevel = SupportEffectTablePO.limit_levels[nextIndex];
            int nextValue = effect.limit_values[nextIndex];
            int preIndex = effect.getPreLimit(lv);
            if (preIndex == -1) {
                continue;
            }
            int preLevel = SupportEffectTablePO.limit_levels[preIndex];
            int preValue = effect.limit_values[preIndex];
            double step = (nextValue - preValue) / (double) (nextLevel - preLevel);
            double value = preValue + step * (lv - preLevel);
            value = Math.floor(value);
            status.put(key, (int) value);
        }
        return status;
    }

    //{"state":"ok","status":{"レースボーナス":1,"友情ボーナス":5,"トレーニング効果アップ":1,"初期スピードアップ":11,"ファン数ボーナス":1}}
    @Clear(NeedLogin.class)
    public void calculate(int id, int lv) {
        try {
            Map<String, Integer> status = calc(id, lv);
            renderJson(Ret.ok("status", status));
        } catch (Throwable t) {
            renderJson(Ret.fail());
        }
    }

}
