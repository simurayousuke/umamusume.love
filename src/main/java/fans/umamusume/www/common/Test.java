package fans.umamusume.www.common;

import com.jfinal.server.undertow.UndertowServer;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final Map<String, String> condition_special_table = new HashMap<String, String>() {{
        put("is_finalcorner==1&corner==0", "最终直线");
    }};
    public static void main(String[] args) {
        String o="is_finalcorner==1&corner==0&change_order_onetime<0&order>=4";
        for (Map.Entry<String,String> pair:condition_special_table.entrySet()) {
            o=o.replaceAll(pair.getKey(),pair.getValue());
            System.out.println(pair.getKey()+","+pair.getValue());
        }
        System.out.println(o);
    }

}
