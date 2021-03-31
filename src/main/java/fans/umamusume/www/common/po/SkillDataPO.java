package fans.umamusume.www.common.po;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;
import fans.umamusume.www.common.kit.ConvertKit;

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
    private int targetType1;
    private int targetValue1;
    private int targetType2;
    private int targetType3;
    private int targetValue2;
    private int targetValue3;

    private String durationStr;
    private String cooldownStr;
    private String effect1;
    private String effect2;
    private String effect3;
    /*private String type1Str;
    private String type2Str;
    private String type3Str;
    private String value1Str;
    private String value2Str;
    private String value3Str;*/
    private String iconStr;

    private String tooltip1;
    private String tooltip2;
    private String tooltip3;

    private static List<SkillDataPO> all_skills_list = null;
    private static final Log LOGGER = Log.getLog(SkillDataPO.class);

    /*
    target_type
1  自己
4  其他的马
9  前方的马
10 后方的马
18 其他特定跑法的马
19 前方掛かった的马
20 后方掛かった的马
21 掛る的马
target_value
* 1 逃
* 2 先行
* 3 差
* 4 追
* 5  第一只马?
* 10 掛かった?
* 18 所有马
---------------
1  自己
4  其他的马
* 18 所有马
9  前方的马
* 18 所有马
* 5  第一只马?
10 后方的马
* 18 所有马
* 5  第一只马?
18 其他特定跑法的马
* 1 逃
* 2 先行
* 3 差
* 4 追
19 前方掛る的马
* 10 掛かった?
20 后方掛る的马
* 10 掛かった?
21 掛る的马
* 1 逃
* 2 先行
* 3 差
* 4 追
*/

    public static List<SkillDataPO> getAllSkillsList() {
        if (null == all_skills_list) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_skills_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id as 技能id,b.text as 技能名称,c.text as 技能描述,condition_1 as 触发条件,float_ability_time_1 as 生效时长," +
                        "float_cooldown_time_1 as 冷却时间, ability_type_1_1 as 效果1, float_ability_value_1_1 as 数值1,ability_type_1_2 as 效果2,float_ability_value_1_2 as 数值2," +
                        "ability_type_1_3 as 效果3,float_ability_value_1_3 as 数值3, icon_id as 图标, target_type_1_1,target_value_1_1," +
                        "target_type_1_2,target_value_1_2,target_type_1_3," +
                        "target_value_1_3 from skill_data as a left join text_data as b on b.\"index\"=a.id and b.category=47 left join text_data as c " +
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

    public static SkillDataPO getSkillData(int id) {
        if (id == 0)
            return null;
        for (SkillDataPO s : getAllSkillsList())
            if (s.getId() == id)
                return s;
        return null;
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

    public static final Map<Integer, String> skill_target_value_table = new HashMap<Integer, String>() {{
        put(0, "马的");
        put(1, "逃马的");
        put(2, "先行马的");
        put(3, "差马的");
        put(4, "追马的");
        put(5, "第一只马(?)的");
        put(10, "掛かった的马的");
        put(18, "所有马的");
    }};

    public static final Map<Integer, String> skill_target_type_table = new HashMap<Integer, String>() {{
        put(0, "");
        put(1, "自己的");
        put(4, "其他的");
        put(9, "前方的");
        put(10, "后方的");
        put(18, "其他的");
        put(19, "前方的");
        put(20, "后方的");
        put(21, "(包括自己?)掛る的");
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
        put("overtake_target_no_order_up_time","想要超车但没能超过去的持续时间");
        put("overtake_target_time","想要超车的持续时间");
    }};

    private static final Map<String, String> condition_special_table = new HashMap<String, String>() {{
        put("corner_random==1@corner_random==2@corner_random==3@corner_random==4", "任意弯道随机判定");

        put("is_finalcorner==1&corner==0", "最终直线");
        put("is_finalcorner==1&corner!=0", "最终弯道");

        put("change_order_onetime<0", "超过一人");
        put("phase>=2", "比赛后期");
        put("corner!=0", "弯道");
        put("phase_random==1", "比赛中期随机判定");
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
        put("straight_random==1", "直线随机判定");
        put("slope==1", "上坡");
        put("is_lastspurt==1", "正在最终冲刺");//待确定
        put("always==1", "常驻");
        put("phase==0", "比赛初期");
        put("phase_random==0", "比赛初期随机判定");
        put("phase==1", "比赛中期");
        put("change_order_onetime>0", "被超过");
        put("phase_random==2", "比赛后期随机判定");
        put("phase_random==3", "最终冲刺时期随机判定");
        put("running_style==1", "逃");
        put("running_style==2", "先行");
        put("running_style==3", "差");
        put("running_style==4", "追");
        put("is_finalcorner_random==1", "最终弯道随机判定");
        put("is_behind_in==1", "从外侧");
        put("distance_type==1", "短距离");
        put("distance_type==2", "英里");
        put("distance_type==3", "中距离");
        put("distance_type==4", "长距离");
        put("lane_type==0", "内道");
        put("is_hp_empty_onetime==1", "持久力用尽");
        put("is_temptation==0", "当前没有掛る");
        put("track_id==10101", "大井赛场");
        put("corner_random==1", "在弯道1处随机判定");
        put("corner_random==2", "在弯道2处随机判定");
        put("corner_random==3", "在弯道3处随机判定");
        put("corner_random==4", "在弯道4处随机判定");
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

    private String combineSkillEffectDescription(int effectType, float effectValue, int targetType, int targetValue) {
        float absValue = Math.abs(effectValue);
        String value = "";
        String res="";
        if(effectType!=0){
            switch (effectType) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 21:
                case 27:
                case 28:
                case 31:
                    value = ConvertKit.convertFloatToString(absValue / 10000f);
                    break;
                case 9:
                    value = ConvertKit.convertFloatToString(absValue / 100f) + "%";
                    break;
                default:
                    value = absValue + "";
            }
            res= skill_target_type_table.getOrDefault(targetType, targetType + "的")
                    + skill_target_value_table.getOrDefault(targetValue, targetValue + "马的")
                    + skill_effect_type_table.getOrDefault(effectType, effectType + "")
                    + (effectValue < 0 ? "降低" : "提高")+" " + value;
        }
        return res;
    }

    private String combineTooltipDescription(int effectType, float effectValue, int targetType, int targetValue) {
        String res = "";
        if (effectType != 0) {
            res = "<a data-toggle=\"tooltip\" data-placement=\"left\" data-title=\"" + targetType + "的" + targetValue + "的"
                    + effectType + "改变" + effectValue + "\" " +
                    "data-trigger=\"focus hover click\"><i class=\"icon icon-info-sign\"></i></a>";
        }
        return res;
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
        targetType1 = rs.getInt(14);
        targetValue1 = rs.getInt(15);
        targetType2 = rs.getInt(16);
        targetValue2 = rs.getInt(17);
        targetType3 = rs.getInt(18);
        targetValue3 = rs.getInt(19);

        if (duration == -1)
            durationStr = "瞬间生效";
        else
            durationStr = ConvertKit.convertFloatToString(duration / 10000f) + "秒";
        if (cooldown == 0)
            cooldownStr = "常驻";
        else
            cooldownStr = ConvertKit.convertFloatToString(cooldown / 10000f) + "秒";

        effect1 = combineSkillEffectDescription(type1, value1, targetType1, targetValue1);
        effect2 = combineSkillEffectDescription(type2, value2, targetType2, targetValue2);
        effect3 = combineSkillEffectDescription(type3, value3, targetType3, targetValue3);
        tooltip1 = combineTooltipDescription(type1, value1, targetType1, targetValue1);
        tooltip2 = combineTooltipDescription(type2, value2, targetType2, targetValue2);
        tooltip3 = combineTooltipDescription(type3, value3, targetType3, targetValue3);

        iconStr = Config.OSSPATH + "/skillicon/00000.png";
        for (int i : skill_icons_id) {
            if (i == icon) {
                iconStr = Config.OSSPATH + "/skillicon/" + String.valueOf(icon) + ".png";
                break;
            }
        }

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

    public float getRawDuration() {
        return duration;
    }

    public String getDuration() {
        return durationStr;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getRawCooldown() {
        return cooldown;
    }

    public String getCooldown() {
        return cooldownStr;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public int getRawType1() {
        return type1;
    }


    public void setType1(int type1) {
        this.type1 = type1;
    }

    public float getRawValue1() {
        return value1;
    }


    public void setValue1(float value1) {
        this.value1 = value1;
    }

    public int getRawType2() {
        return type2;
    }


    public void setType2(int type2) {
        this.type2 = type2;
    }

    public float getRawValue2() {
        return value2;
    }


    public void setValue2(float value2) {
        this.value2 = value2;
    }

    public int getRawType3() {
        return type3;
    }


    public void setType3(int type3) {
        this.type3 = type3;
    }

    public float getRawValue3() {
        return value3;
    }


    public void setValue3(float value3) {
        this.value3 = value3;
    }

    public String getIcon() {
        return iconStr;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getType1() {
        return type1;
    }

    public float getValue1() {
        return value1;
    }

    public int getType2() {
        return type2;
    }

    public float getValue2() {
        return value2;
    }

    public int getType3() {
        return type3;
    }

    public float getValue3() {
        return value3;
    }

    public int getTargetType1() {
        return targetType1;
    }

    public int getTargetValue1() {
        return targetValue1;
    }

    public int getTargetType2() {
        return targetType2;
    }

    public int getTargetType3() {
        return targetType3;
    }

    public int getTargetValue2() {
        return targetValue2;
    }

    public int getTargetValue3() {
        return targetValue3;
    }

    public String getEffect1() {
        return effect1;
    }

    public String getEffect2() {
        return effect2;
    }

    public String getEffect3() {
        return effect3;
    }

    public String getTooltip1() {
        return tooltip1;
    }

    public String getTooltip2() {
        return tooltip2;
    }

    public String getTooltip3() {
        return tooltip3;
    }
}
