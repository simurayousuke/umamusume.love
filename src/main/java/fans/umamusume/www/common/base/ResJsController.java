package fans.umamusume.www.common.base;

import com.jfinal.i18n.Res;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ResJsController extends MyController {

    public void index() {
        Res _res = getRes();
        Enumeration<String> keys = _res.getResourceBundle().getKeys();
        Map<String, String> __res = new HashMap<>();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            __res.put(key, _res.get(key));
        }
        set("__res", __res);
        set("__locale", getCookie("_locale","en_US"));
        render("res.js");
    }

}
