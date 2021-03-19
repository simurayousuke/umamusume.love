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

public class SupportEffectTablePO {

    private int id;
    private int type;
    private String type_name;
    private String type_description;
    private int init;
    private int limit_lv5;
    private int limit_lv10;
    private int limit_lv15;
    private int limit_lv20;
    private int limit_lv25;
    private int limit_lv30;
    private int limit_lv35;
    private int limit_lv40;
    private int limit_lv45;
    private int limit_lv50;


    private static List<SupportEffectTablePO> all_support_effect_table_list = null;
    private static final Log LOGGER = Log.getLog(SupportEffectTablePO.class);

    public static final Map<Integer, String> support_effect_type_table = new HashMap<Integer, String>() {{
        put(1, "友情训练效果额外提升");
        put(2, "干劲加成效果额外提升");
        put(3, "速度训练加成");
        put(4, "耐力训练加成");
        put(5, "力量训练加成");
        put(6, "根性训练加成");
        put(7, "智力训练加成");
        put(8, "所有训练效果加成");
        put(9, "初期速度上升");
        put(10, "初期耐力上升");
        put(11, "初期力量上升");
        put(12, "初期根性上升");
        put(13, "初期智力上升");
        put(14, "初期羁绊上升");
        put(15, "比赛奖励加成");
        put(16, "粉丝数加成");
        put(17, "技能提示等级加成");
        put(18, "技能提示发生率加成");
        put(19, "得意率加成");
        put(25, "事件回复量加成");
        put(26, "事件效果加成");
        put(27, "失败率下降");
        put(28, "体力消耗下降");
        put(30, "技能点数加成");
        put(31, "智力友情训练回复量加成");
    }};

    public static final int[] limit_levels = new int[]{1, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
    public int[] limit_values = new int[11];

    public SupportEffectTablePO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        type = rs.getInt(2);
        type_name = rs.getString(3);
        if (support_effect_type_table.containsKey(type))
            type_description = support_effect_type_table.get(type);
        else
            type_description = rs.getString(4);
        init = rs.getInt(5);
        limit_lv5 = rs.getInt(6);
        limit_lv10 = rs.getInt(7);
        limit_lv15 = rs.getInt(8);
        limit_lv20 = rs.getInt(9);
        limit_lv25 = rs.getInt(10);
        limit_lv30 = rs.getInt(11);
        limit_lv35 = rs.getInt(12);
        limit_lv40 = rs.getInt(13);
        limit_lv45 = rs.getInt(14);
        limit_lv50 = rs.getInt(15);
        limit_values[0] = init;
        limit_values[1] = limit_lv5;
        limit_values[2] = limit_lv10;
        limit_values[3] = limit_lv15;
        limit_values[4] = limit_lv20;
        limit_values[5] = limit_lv25;
        limit_values[6] = limit_lv30;
        limit_values[7] = limit_lv35;
        limit_values[8] = limit_lv40;
        limit_values[9] = limit_lv45;
        limit_values[10] = limit_lv50;
        /*init = Math.max(rs.getInt(5), 0);
        limit_lv5 = Math.max(rs.getInt(6), init);
        limit_lv10 = Math.max(rs.getInt(7), limit_lv5);
        limit_lv15 = Math.max(rs.getInt(8), limit_lv10);
        limit_lv20 = Math.max(rs.getInt(9), limit_lv15);
        limit_lv25 = Math.max(rs.getInt(10), limit_lv20);
        limit_lv30 = Math.max(rs.getInt(11), limit_lv25);
        limit_lv35 = Math.max(rs.getInt(12), limit_lv30);
        limit_lv40 = Math.max(rs.getInt(13), limit_lv35);
        limit_lv45 = Math.max(rs.getInt(14), limit_lv40);
        limit_lv50 = Math.max(rs.getInt(15), limit_lv45);*/
    }

    public SupportEffectTablePO() {
    }

    public static List<SupportEffectTablePO> getAllSupportEffectTableList() {
        if (all_support_effect_table_list == null) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_support_effect_table_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id, type,b.text as type_name,c.text as type_description, " +
                        "init, limit_lv5, limit_lv10, limit_lv15, limit_lv20, limit_lv25, limit_lv30, " +
                        "limit_lv35, limit_lv40, limit_lv45, limit_lv50 " +
                        "from support_card_effect_table as a left join text_data as b " +
                        "on a.type=b.\"index\" and b.category=151 left join text_data as c " +
                        "on a.type=c.\"index\" and c.category=154;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    all_support_effect_table_list.add(new SupportEffectTablePO(rs));
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_support_effect_table_list = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_support_effect_table_list;
    }

    public static List<SupportEffectTablePO> getSupportEffectTable(int id) {
        List<SupportEffectTablePO> res = new ArrayList<>();
        for (SupportEffectTablePO s : getAllSupportEffectTableList()) {
            if (s.getId() == id)
                res.add(s);
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getType_name() {
        return type_name;
    }

    public String getType_description() {
        return type_description;
    }

    public int getInit() {
        return init;
    }

    public int getLimit_lv5() {
        return limit_lv5;
    }

    public int getLimit_lv10() {
        return limit_lv10;
    }

    public int getLimit_lv15() {
        return limit_lv15;
    }

    public int getLimit_lv20() {
        return limit_lv20;
    }

    public int getLimit_lv25() {
        return limit_lv25;
    }

    public int getLimit_lv30() {
        return limit_lv30;
    }

    public int getLimit_lv35() {
        return limit_lv35;
    }

    public int getLimit_lv40() {
        return limit_lv40;
    }

    public int getLimit_lv45() {
        return limit_lv45;
    }

    public int getLimit_lv50() {
        return limit_lv50;
    }

    public int getNextLimit(int lv) {
        int i = lv / 5 + 1;
        if (i < 0)
            i = 0;
        else if (i >= 11)
            return -1;
        for (; i < 11; ++i) {
            if (limit_values[i] != -1)
                return i;
        }
        return -1;
    }

    public int getPreLimit(int lv) {
        int i = lv / 5;
        if (i >= 11)
            i = 10;
        else if (i < 0)
            return -1;
        for (; i >= 0; --i) {
            if (limit_values[i] != -1)
                return i;
        }
        return -1;
    }

    public int getUnlockLevel() {
        for (int i = 0; i < 11; ++i) {
            if (limit_values[i] != -1)
                return limit_levels[i];
        }
        return -1;
    }
}
