package fans.umamusume.www.common.po;

import com.jfinal.kit.StrKit;
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

public class SkillDataPO {

    //技能ID,技能名称,技能描述,触发条件,生效时长,冷却时间,效果1,数值1,效果2,数值2,效果3,数值3

    private int id;
    private String name;
    private String description;
    private String rawCondition;
    private float duration;
    private float cooldown;
    private int type1;
    private float value1;
    private int type2;
    private float value2;
    private int type3;
    private float value3;
    private int icon;

    private static List<SkillDataPO> all_skills_list = null;
    private static final Log LOGGER = Log.getLog(SkillDataPO.class);

    public static List<SkillDataPO> getAllSkillsList() {
        if (null == all_skills_list) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_skills_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id as 技能id,b.text as 技能名称,c.text as 技能描述,condition_1 as 触发条件,float_ability_time_1 as 生效时长," +
                        "float_cooldown_time_1 as 冷却时间, ability_type_1_1 as 效果1, float_ability_value_1_1 as 数值1,ability_type_1_2 as 效果2,float_ability_value_1_2 as 数值2," +
                        "ability_type_1_3 as 效果3,float_ability_value_1_3 as 数值3, icon_id as 图标 from skill_data as a left join text_data as b on b.\"index\"=a.id and b.category=47 left join text_data as c " +
                        "on c.\"index\"=a.id and c.category=48;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    all_skills_list.add(new SkillDataPO(rs));
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_skills_list = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_skills_list;
    }

    public static final int[] skill_icons_id = new int[]{
            10011, 10014, 10021, 10024, 10031, 10034, 10041, 10044, 10051, 10054, 10061, 10062,
            20011, 20012, 20013, 20014, 20021, 20022, 20023, 20024, 20041, 20042, 20043, 20044,
            20051, 20052, 20054, 20061, 20062, 20064, 20091, 20092, 20094, 30011, 30012, 30021,
            30022, 30041, 30051, 30052, 30071, 30072
    };

    public static final Map<Integer, String> skill_effect_type_table = new HashMap<Integer, String>() {{
        put(1, "速度");
        put(2, "耐力");
        put(3, "力量");
        put(4, "根性");
        put(5, "智力");
        put(8, "视野");
        put(9, "持久力");
        put(10, "起跑");
        put(13, "冷静");
        put(21, "速度");
        put(27, "速度");
        put(28, "抢路");
        put(31, "加速力");
    }};

    public static final Map<String, String> condition_type_table = new HashMap<String, String>() {{
        put("distance_rate", "赛程进度百分比");
        put("order_rate", "排名百分比");
        put("order", "排名");
        put("remain_distance", "剩余距离");
        put("bashin_diff_infront", "与前马相差马身");
        put("bashin_diff_behind", "与后马相差马身");
        put("hp_per", "剩余耐力百分比");
        put("blocked_side_continuetime", "侧面有马持续时间");
        put("near_count", "附近马数");
        put("blocked_front", "前方马数");
        put("distance_diff_rate", "当前赛程百分比");//待确定
        put("temptation_count", "掛る次数");//待确定
        put("change_order_up_end_after", "超过的马数");
        put("running_style_count_same", "与自己跑法相同的马数(含自己)");
        put("popularity", "人气排名");
        put("accumulatetime", "起跑后经过时间");
        put("blocked_all_continuetime", "被围住持续时间");
        put("blocked_front_continuetime", "前方被挡持续时间");
        put("temptation_count_behind", "后方掛る马数");//待确定
        put("temptation_count_infront", "前方掛る马数");//待确定
        put("running_style_temptation_count_nige", "逃马掛る的数量");
        put("running_style_temptation_count_senko", "先行马掛る的数量");
        put("running_style_temptation_count_sashi", "差马掛る的数量");
        put("running_style_temptation_count_oikomi", "追马掛る的数量");
        put("running_style_count_nige_otherself", "其他逃马数量");
        put("running_style_count_senko_otherself", "其他先行马数量");
        put("running_style_count_sashi_otherself", "其他差马数量");
        put("running_style_count_oikomi_otherself", "其他追马数量");
        put("activate_count_start", "比赛初期技能发动数");
        put("activate_count_middle", "比赛中期技能发动数");
        put("activate_count_end_after", "比赛后期技能发动数");
        put("same_skill_horse_count", "拥有本技能马数");
        put("infront_near_lane_time", "同赛道前方近处有马持续时间");
        put("behind_near_lane_time", "同赛道后方近处有马持续时间");
    }};

    private static final Map<String, String> condition_special_table = new HashMap<String, String>() {{
        put("corner_random==1@corner_random==2@corner_random==3@corner_random==4", "任意弯道");

        put("is_finalcorner==1&corner==0", "最终直线");
        put("is_finalcorner==1&corner!=0", "最终弯道");

        put("change_order_onetime<0", "超过一人");
        put("phase>=2", "比赛后期");
        put("corner!=0", "弯道");
        put("phase_random==1", "比赛中期");
        put("is_overtake==1", "与前马距离缩小");
        put("is_finalcorner==1", "最终弯道及以后");//待确定
        put("corner==0", "非弯道");
        put("is_badstart==0", "起跑顺利");
        put("rotation==1", "顺时针");
        put("rotation==2", "逆时针");
        put("track_id==10006", "东京赛场");
        put("track_id==10005", "中山赛场");
        put("track_id==10009", "阪神赛场");
        put("track_id==10008", "京都赛场");
        put("track_id==10007", "中京赛场");
        put("track_id==10001", "札幌赛场");
        put("track_id==10002", "函馆赛场");
        put("track_id==10004", "福岛赛场");
        put("track_id==10003", "新潟赛场");
        put("track_id==10010", "小仓赛场");
        put("is_basis_distance==1", "400m的整数倍");
        put("is_basis_distance==1", "非400m的整数倍");
        put("ground_condition==1", "马场状态良");
        put("ground_condition==2", "马场状态稍重");
        put("ground_condition==3", "马场状态重");
        put("ground_condition==4", "马场状态不良");
        put("season==1", "春");
        put("season==5", "春");
        put("season==2", "夏");
        put("season==3", "秋");
        put("season==4", "冬");
        put("weather==1", "晴");
        put("weather==2", "云");
        put("weather==3", "雨");
        put("weather==4", "雪");
        put("post_number<=3", "赛道1~3");
        put("post_number>=6", "赛道6~8");
        put("running_style_count_same<=1", "没有与自己跑法相同的马");
        put("running_style_equal_popularity_one==1", "和人气第一的马跑法相同");
        put("grade==100", "GI比赛");
        put("straight_random==1", "直线");
        put("slope==1", "上坡");
        put("is_lastspurt==1", "正在最终冲刺");//待确定
        put("always==1", "常驻");
        put("phase==0", "比赛初期");
        put("phase_random==0", "比赛初期");
        put("phase==1", "比赛中期");
        put("change_order_onetime>0", "被超过");
        put("phase_random==2", "比赛后期");
        put("phase_random==3", "最终冲刺时期");
        put("running_style==1", "逃");
        put("running_style==2", "先行");
        put("running_style==3", "差");
        put("running_style==4", "追");
        put("is_finalcorner_random==1", "最终弯道");
        put("is_behind_in==1", "从外侧");
        put("distance_type==1", "短距离");
        put("distance_type==2", "英里");
        put("distance_type==3", "中距离");
        put("distance_type==4", "长距离");
        put("lane_type==0", "内道");
        put("is_hp_empty_onetime==1", "持久力用尽");
        put("is_temptation==0", "当前没有掛る");
        put("track_id==10101", "大井赛场");
        put("corner_random==1", "在弯道1处");
        put("corner_random==2", "在弯道2处");
        put("corner_random==3", "在弯道3处");
        put("corner_random==4", "在弯道4处");
        put("is_move_lane==1", "正在向左变道");//方向待确定
        put("is_move_lane==2", "正在向右变道");//方向待确定
        put("phase==2", "比赛后期");
        put("slope==2", "下坡");
        put("same_skill_horse_count==1", "仅自己有此技能");
        put("post_number==7", "赛道为7");
    }};

    private static final String[] condition_symbol = new String[]{">=", "<=", ">", "<", "==", "!="};

    private static List<List<String>> TranslateCondition(String condition) {
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, String> pair : condition_special_table.entrySet()) {
            condition = condition.replaceAll(pair.getKey(), pair.getValue());
        }
        String[] or = condition.split("@");
        for (String o : or) {
            List<String> conditions = new ArrayList<String>();

            String[] cs = o.split("&");
            for (String s : cs) {
                String front = "", middle = "", back = "";
                for (String symbol : condition_symbol) {
                    if (s.contains(symbol)) {
                        middle = symbol;
                        front = s.substring(0, s.indexOf(symbol));
                        back = s.substring(s.indexOf(symbol) + symbol.length(), s.length());
                        break;
                    }
                }
                if (!StrKit.isBlank(middle)) {
                    String c = condition_type_table.getOrDefault(front, front);
                    c += middle + back;
                    conditions.add(c);
                } else {
                    conditions.add(s);
                }
            }
            list.add(conditions);
        }
        return list;
    }

    private static String ConditionsToString(List<List<String>> list) {
        StringBuilder s = new StringBuilder();
        for (List<String> l : list) {
            for (String c : l) {
                s.append(c).append(" 且 ");
            }
            s.delete(s.length() - 3, s.length());
            s.append(" 或 ");
        }
        s.delete(s.length() - 3, s.length());
        return s.toString();
    }

    SkillDataPO() {
    }

    public SkillDataPO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        name = rs.getString(2);
        description = rs.getString(3);
        rawCondition = rs.getString(4);
        duration = rs.getFloat(5);
        cooldown = rs.getFloat(6);
        type1 = rs.getInt(7);
        value1 = rs.getFloat(8);
        type2 = rs.getInt(9);
        value2 = rs.getFloat(10);
        type3 = rs.getInt(11);
        value3 = rs.getFloat(12);
        icon = rs.getInt(13);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRawCondition() {
        return rawCondition;
    }

    public String getCondition() {
        return ConditionsToString(TranslateCondition(rawCondition));
    }

    public void setRawCondition(String rawCondition) {
        this.rawCondition = rawCondition;
    }

    public String getDuration() {
        if (duration == -1)
            return "瞬间生效";
        else
            return "" + duration / 10000;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getCooldown() {
        if (cooldown == 0)
            return "常驻";
        else
            return "" + cooldown / 10000;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public String getType1() {
        if (skill_effect_type_table.containsKey((type1)))
            return skill_effect_type_table.get(type1);
        else
            return "" + type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public float getValue1() {
        return value1;
    }

    public void setValue1(float value1) {
        this.value1 = value1;
    }

    public String getType2() {
        if (skill_effect_type_table.containsKey((type2)))
            return skill_effect_type_table.get(type2);
        else
            return "" + type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public float getValue2() {
        return value2;
    }

    public void setValue2(float value2) {
        this.value2 = value2;
    }

    public String getType3() {
        if (skill_effect_type_table.containsKey((type3)))
            return skill_effect_type_table.get(type3);
        else
            return "" + type3;
    }

    public void setType3(int type3) {
        this.type3 = type3;
    }

    public float getValue3() {
        return value3;
    }

    public void setValue3(float value3) {
        this.value3 = value3;
    }

    public String getIcon() {
        for (int i : skill_icons_id) {
            if (i == icon)
                return Config.OSSPATH + "/skillicon/" + String.valueOf(icon) + ".png";
        }
        return Config.OSSPATH + "/skillicon/00000.png";
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
