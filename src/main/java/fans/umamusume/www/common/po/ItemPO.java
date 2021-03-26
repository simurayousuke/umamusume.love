package fans.umamusume.www.common.po;

import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemPO {

    private int id;
    private String name;
    private String description;
    private int rawCategory;
    private String category;
    private int rawGroupId;
    private String groupId;
    private int rawEffectType1;
    private String effectType1;
    private int rawEffectTarget1;
    private String effectTarget1;
    private int rawEffectValue1;
    private String effectValue1;
    private int rawEffectType2;
    private String effectType2;
    private int rawEffectTarget2;
    private String effectTarget2;
    private int rawEffectValue2;
    private String effectValue2;
    private int rawAddValue1;
    private String addValue1;
    private int rawAddValue2;
    private String addValue2;
    private int rawAddValue3;
    private String addValue3;
    private int limitNum;
    private int sort;
    private int rare;
    private int request;
    private String requestable;
    private int rawRequestReward;
    private String requestReward;
    private int itemPlaceId;
    private String startDate;
    private String  endDate;

    private static final Map<Integer, String> category_translate_table = new HashMap<Integer, String>() {{
        put(11, "觉醒材料");
        put(20, "TP回复药");
        put(21, "RP回复药");
        put(30, "SP");
        put(34, "闹钟");
        put(40, "马娘单抽卷");
        put(41, "3星任选卷1");
        put(42, "SSR任选卷1");
        put(90, "宝石");
        put(91, "金币");
        put(93, "稀有觉醒材料");
        put(94, "转盘币");
        put(97, "女神像");
        put(98, "关注位");
        put(99, "四叶草");
        put(100, "活动点数");
        put(103, "友情点数");
        put(110, "绿帽的红笔");
        put(140, "门票");
        put(150, "比赛可用道具");
        put(160, "蹄铁");
        put(161, "社团点数");
    }};
    private static final Map<Integer, String> effect_type_translate_table = new HashMap<Integer, String>() {{
        put(0, "");
        put(1, "交换物品");
        put(10, "干劲调整");
        put(20, "赛场条件调整");
        put(40, "赛道调整");
    }};
    private static final Map<Integer, String> effect_target_translate_table = new HashMap<Integer, String>() {{
        put(0, "");
        put(1, "内道");
        put(2, "外道");
    }};

    private static List<ItemPO> all_item_list = null;
    private static final Log LOGGER=Log.getLog(ItemPO.class);

    public static ItemPO getItem(int id) {
        for (ItemPO item : getAllItemList())
            if (item.id == id)
                return item;
        return null;
    }

    public static List<ItemPO> getAllItemList() {
        if (all_item_list == null) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_item_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id,\n" +
                        "       b.text name,\n" +
                        "       c.text description,\n" +
                        "       item_category,\n" +
                        "       group_id,\n" +
                        "       effect_type_1,\n" +
                        "       effect_target_1,\n" +
                        "       effect_value_1,\n" +
                        "       effect_type_2,\n" +
                        "       effect_target_2,\n" +
                        "       effect_value_2,\n" +
                        "       add_value_1,\n" +
                        "       add_value_2,\n" +
                        "       add_value_3,\n" +
                        "       limit_num,\n" +
                        "       sort,\n" +
                        "       rare,\n" +
                        "       enable_request,\n" +
                        "       request_reward,\n" +
                        "       item_place_id,\n" +
                        "       start_date,\n" +
                        "       end_date\n" +
                        "from item_data a\n" +
                        "left join text_data b on b.\"index\"=a.id and b.category=23\n" +
                        "left join text_data c on c.\"index\"=a.id and c.category=24;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    all_item_list.add(new ItemPO(rs));
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_item_list = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_item_list;
    }

    public ItemPO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        name = rs.getString(2);
        description = rs.getString(3);
        rawCategory = rs.getInt(4);
        category = category_translate_table.getOrDefault(rawCategory, "未知");
        rawGroupId = rs.getInt(5);
        groupId = "";
        if (rawGroupId != 0)
            groupId = rawGroupId + "";
        rawEffectType1 = rs.getInt(6);
        effectType1 = effect_type_translate_table.getOrDefault(rawEffectType1, rawEffectType1 + "");
        rawEffectTarget1 = rs.getInt(7);
        effectTarget1 = effect_target_translate_table.getOrDefault(rawEffectTarget1, rawEffectTarget1 + "");
        rawEffectValue1 = rs.getInt(8);
        effectValue1 = "";
        if (rawEffectValue1 != 0)
            effectValue1 = rawEffectValue1 + "";
        rawEffectType2 = rs.getInt(9);
        effectType2 = effect_type_translate_table.getOrDefault(rawEffectType2, rawEffectType2 + "");
        rawEffectTarget2 = rs.getInt(10);
        effectTarget2 = effect_target_translate_table.getOrDefault(rawEffectTarget2, rawEffectTarget2 + "");
        rawEffectValue2 = rs.getInt(11);
        effectValue2 = "";
        if (rawEffectValue2 != 0)
            effectValue2 = rawEffectValue2 + "";
        rawAddValue1 = rs.getInt(12);
        addValue1 = "";
        if (rawAddValue1 != 0)
            addValue1 = rawAddValue1 + "";
        rawAddValue2 = rs.getInt(13);
        addValue2 = "";
        if (rawAddValue2 != 0)
            addValue2 = rawAddValue2 + "";
        rawAddValue3 = rs.getInt(14);
        addValue3 = "";
        if (rawAddValue3 != 0)
            addValue3 = rawAddValue3 + "";
        limitNum = rs.getInt(15);
        sort = rs.getInt(16);
        rare = rs.getInt(17);
        request = rs.getInt(18);
        requestable = request == 0 ? "不可捐赠" : "可捐赠";
        rawRequestReward = rs.getInt(19);
        requestReward = "";
        if (rawRequestReward != 0)
            requestReward = rawRequestReward + "社团点";
        itemPlaceId = rs.getInt(20);
        startDate = rs.getString(21);
        endDate = rs.getString(22);
    }

    public ItemPO() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRawCategory() {
        return rawCategory;
    }

    public String getCategory() {
        return category;
    }

    public int getRawGroupId() {
        return rawGroupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public int getRawEffectType1() {
        return rawEffectType1;
    }

    public String getEffectType1() {
        return effectType1;
    }

    public int getRawEffectTarget1() {
        return rawEffectTarget1;
    }

    public String getEffectTarget1() {
        return effectTarget1;
    }

    public int getRawEffectValue1() {
        return rawEffectValue1;
    }

    public String getEffectValue1() {
        return effectValue1;
    }

    public int getRawEffectType2() {
        return rawEffectType2;
    }

    public String getEffectType2() {
        return effectType2;
    }

    public int getRawEffectTarget2() {
        return rawEffectTarget2;
    }

    public String getEffectTarget2() {
        return effectTarget2;
    }

    public int getRawEffectValue2() {
        return rawEffectValue2;
    }

    public String getEffectValue2() {
        return effectValue2;
    }

    public int getRawAddValue1() {
        return rawAddValue1;
    }

    public String getAddValue1() {
        return addValue1;
    }

    public int getRawAddValue2() {
        return rawAddValue2;
    }

    public String getAddValue2() {
        return addValue2;
    }

    public int getRawAddValue3() {
        return rawAddValue3;
    }

    public String getAddValue3() {
        return addValue3;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public int getSort() {
        return sort;
    }

    public int getRare() {
        return rare;
    }

    public int getRequest() {
        return request;
    }

    public String getRequestable() {
        return requestable;
    }

    public int getRawRequestReward() {
        return rawRequestReward;
    }

    public String getRequestReward() {
        return requestReward;
    }

    public int getItemPlaceId() {
        return itemPlaceId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
