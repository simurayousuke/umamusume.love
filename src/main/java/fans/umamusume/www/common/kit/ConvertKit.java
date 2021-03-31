package fans.umamusume.www.common.kit;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertKit {

    public static final Map<String, String> UNIT_MAP = new HashMap<String, String>() {{
        put("calorie", "kcal");
        put("protein", "g");
        put("fat", "g");
        put("carbohydrate", "g");
        put("sodium", "mg");
        put("salt", "g");
        put("cholesterol", "mg");
        put("sugar", "g");
        put("vitaminA", "μg(mcg)");
        put("vitaminD", "μg(mcg)");
        put("vitaminE", "mg");
        put("vitaminK", "μg(mcg)");
        put("vitaminB1", "mg");
        put("vitaminB2", "mg");
        put("vitaminB6", "mg");
        put("vitaminB12", "μg(mcg)");
        put("vitaminC", "mg");
        put("calcium", "mg");
        put("iron", "mg");
        put("magnesium", "mg");
        put("zinc", "mg");
        put("potassium", "mg");
    }};

    public static Ret convertPageToZuiDatagridJsonResult(Page<Record> page) {
        Ret ret = Ret.by("result", "success");
        if (null == page) {
            ret.set("result", "fail");
            return ret;
        }
        ret.set("data", page.getList());
        Ret pager = Ret.by("page", page.getPageNumber()).set("recTotal", page.getTotalRow()).set("recPerPage", page.getPageSize());
        ret.set("pager", pager);
        return ret;
    }

    public static Ret convertPageToDatatablesJsonResult(Page<Record> page) {
        return convertPageToZuiDatagridJsonResult(page);
    }

    public static Map<String, Object> convertCompositionWithUnit(Map<String, Object> ret, boolean removeNull) {
        UNIT_MAP.forEach((k, v) -> {
            if (null != ret.get(k))
                ret.put(k, String.format("%.2f", ret.get(k)) + " " + v);
            else if (removeNull)
                ret.remove(k);

        });
        return ret;
    }

    public static List<Map<String, Object>> getSumForDouble(List<Record> recordList) {
        if (recordList.size() < 1)
            return null;
        Map<String, Object> counter = new HashMap<>();
        List<Map<String, Object>> ret = new ArrayList<>();
        recordList.get(0).getColumns().forEach((k, v) -> {
            counter.put(k, null);
        });
        recordList.forEach((record) -> {
            ret.add(record.getColumns());
            record.getColumns().forEach((k, v) -> {
                if (v instanceof Double) {
                    Double temp = (Double) counter.get(k);
                    if (null == temp)
                        temp = 0.0;
                    counter.put(k, temp + (Double) v);
                }
            });
        });
        ret.add(counter);
        return ret;
    }

    public static String[] convertToShortUrl(String url) {

        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"
        };

        String key = "zhuangcloud.cn";                               // 可以自定义生成 MD5 加密字符传前的混合加密key
        String hex = Md5Kit.md5(key + url);

        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteter.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;     // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                outChars += chars[(int) index];         // 把取得的字符相加
                lHexLong = lHexLong >> 5;             // 每次循环按位右移 5 位
            }
            resUrl[i] = outChars;                       // 把字符串存入对应索引的输出数组
        }
        return resUrl;
    }

    public static String convertFloatToString(float d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

}
