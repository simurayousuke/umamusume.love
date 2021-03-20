package fans.umamusume.www.common.api.v1;

import com.jfinal.aop.Clear;
import com.jfinal.kit.Ret;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.interceptor.NeedLogin;
import fans.umamusume.www.common.po.SuccessionRelationPO;

public class SuccessionApi extends ApiV1 {

    @Clear(NeedLogin.class)
    public void relation(int id1, int id2) {
        try {
            renderJson(Ret.ok("relation", SuccessionRelationPO.getCharaRelation(id1, id2)));
        } catch (Throwable t) {
            renderJson(Ret.fail());
        }
    }

}
