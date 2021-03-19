package fans.umamusume.www.common;

import fans.umamusume.www.common.api.v1.SupportCardApi;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final Map<String, String> condition_special_table = new HashMap<String, String>() {{
        put("is_finalcorner==1&corner==0", "最终直线");
    }};

    public static void main(String[] args) {
        for(Map.Entry<String,Integer> entry: SupportCardApi.calc(10004,34).entrySet()){
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }

}
