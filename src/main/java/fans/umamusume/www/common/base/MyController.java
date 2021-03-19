package fans.umamusume.www.common.base;

import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.kit.StrKit;
import fans.umamusume.www.common.model.User;

public class MyController extends Controller {

    protected void title(String title) {
        set("title", title);
    }

    protected void building() {
        render("/view/common/building.html");
    }

    protected Integer getUid() {
        return ((User) getAttr("user")).getUid();
    }

    protected Res getRes() {
        String locale = getCookie("_locale");
        return StrKit.notBlank(locale) ? I18n.use(locale) : I18n.use();
    }

}
