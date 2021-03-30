package fans.umamusume.www.common.po;

import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UmaPO {
    private int id;
    private int charaId;
    private String cardName;
    private String umaName;
    private int rarity;
    private int limited;
    private int skillSetId;
    private List<AvailableSkillSetPO> skillSet;
    private int talentSpeed;
    private int talentStamina;
    private int talentPow;
    private int talentGuts;
    private int talentWiz;
    private int talentGroupId;
    private List<TalentUpgradePO> talentUpgrade;
    private int bgId;
    private int getPieceId;
    private int rawRunningStyle;
    private String runningStyle;
    private String icon;

    private SkillDataPO uniqueSkill;
    private int defaultSpeed;
    private int defaultStamina;
    private int defaultPow;
    private int defaultGuts;
    private int defaultWiz;
    private int maxSpeed;
    private int maxStamina;
    private int maxPow;
    private int maxGuts;
    private int maxWiz;
    private int properDistanceShort;
    private int properDistanceMile;
    private int properDistanceMiddle;
    private int properDistanceLong;
    private int properGroundTurf;
    private int properGroundDirt;
    private int properRunningStyleNige;
    private int properRunningStyleSenko;
    private int properRunningStyleSashi;
    private int properRunningStyleOikomi;
    private List<CardRarityDataPO> rarityList;

    private static List<UmaPO> all_uma_list = null;
    private static final Log LOGGER = Log.getLog(UmaPO.class);

    private static final int[] iconsId = new int[]{100101, 100201, 100301, 100401, 100601, 100701,
            100801, 100901, 101001, 101101, 101301, 101401, 101501, 101701, 101801, 102301, 102401,
            102601, 102701, 103001, 103201, 103501, 104101, 104501, 105201, 105601, 106001, 106101};

    public static List<UmaPO> getAllUmaList() {
        if (null == all_uma_list) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_uma_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id,\n" +
                        "       a.chara_id,\n" +
                        "       b.text card_name,\n" +
                        "       c.text uma_name,\n" +
                        "       default_rarity,\n" +
                        "       limited_chara,\n" +
                        "       available_skill_set_id,\n" +
                        "       talent_speed,\n" +
                        "       talent_stamina,\n" +
                        "       talent_pow,\n" +
                        "       talent_guts,\n" +
                        "       talent_wiz,\n" +
                        "       talent_group_id,\n" +
                        "       bg_id,\n" +
                        "       get_piece_id,\n" +
                        "       running_style,\n" +
                        "       d.skill_set as uniqueSkill,\n" +
                        "       d.speed,\n" +
                        "       d.stamina,\n" +
                        "       d.pow,\n" +
                        "       d.guts,\n" +
                        "       d.wiz,\n" +
                        "       d.max_speed,\n" +
                        "       d.max_stamina,\n" +
                        "       d.max_pow,\n" +
                        "       d.max_guts,\n" +
                        "       d.max_wiz,\n" +
                        "       d.proper_distance_short,\n" +
                        "       d.proper_distance_mile,\n" +
                        "       d.proper_distance_middle,\n" +
                        "       d.proper_distance_long,\n" +
                        "       d.proper_ground_turf,\n" +
                        "       d.proper_ground_dirt,\n" +
                        "       d.proper_running_style_nige,\n" +
                        "       d.proper_running_style_senko,\n" +
                        "       d.proper_running_style_sashi,\n" +
                        "       d.proper_running_style_oikomi\n" +
                        "from card_data a\n" +
                        "         left join text_data b on b.\"index\" = a.id and b.category = 5\n" +
                        "         left join text_data c on c.\"index\" = a.chara_id and c.category = 6\n" +
                        "left join card_rarity_data d on d.card_id=a.id and d.rarity=a.default_rarity;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    all_uma_list.add(new UmaPO(rs));
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_uma_list = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_uma_list;
    }

    public static UmaPO getUma(int id) {
        for (UmaPO uma : getAllUmaList()) {
            if (uma.id == id)
                return uma;
        }
        return null;
    }

    public UmaPO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        charaId = rs.getInt(2);
        cardName = rs.getString(3);
        umaName = rs.getString(4);
        rarity = rs.getInt(5);
        limited = rs.getInt(6);
        skillSetId = rs.getInt(7);
        skillSet = AvailableSkillSetPO.getSkillSet(skillSetId);
        talentSpeed = rs.getInt(8);
        talentStamina = rs.getInt(9);
        talentPow = rs.getInt(10);
        talentGuts = rs.getInt(11);
        talentWiz = rs.getInt(12);
        talentGroupId = rs.getInt(13);
        talentUpgrade=TalentUpgradePO.getTalentUpgradeData(talentGroupId);
        bgId = rs.getInt(14);
        getPieceId = rs.getInt(15);
        rawRunningStyle = rs.getInt(16);
        switch (rawRunningStyle) {
            case 1:
                runningStyle = "逃げ";
                break;
            case 2:
                runningStyle = "先行";
                break;
            case 3:
                runningStyle = "差し";
                break;
            case 4:
                runningStyle = "追込";
                break;
            default:
                runningStyle = "未知";
                break;
        }
        icon = Config.OSSPATH + "/umaicon/00000.png";
        for (int i : iconsId) {
            if (i == id) {
                icon = Config.OSSPATH + "/umaicon/" + id + ".png";
                break;
            }
        }
        uniqueSkill=SkillSetPO.getSkillSet(rs.getInt(17)).getSkills().get(0);
        defaultSpeed=rs.getInt(18);
        defaultStamina=rs.getInt(19);
        defaultPow=rs.getInt(20);
        defaultGuts=rs.getInt(21);
        defaultWiz=rs.getInt(22);
        maxSpeed=rs.getInt(23);
        maxStamina=rs.getInt(24);
        maxPow=rs.getInt(25);
        maxGuts=rs.getInt(26);
        maxWiz=rs.getInt(27);
        properDistanceShort=rs.getInt(28);
        properDistanceMile=rs.getInt(29);
        properDistanceMiddle=rs.getInt(30);
        properDistanceLong=rs.getInt(31);
        properGroundTurf=rs.getInt(32);
        properGroundDirt=rs.getInt(33);
        properRunningStyleNige=rs.getInt(34);
        properRunningStyleSenko=rs.getInt(35);
        properRunningStyleSashi=rs.getInt(36);
        properRunningStyleOikomi=rs.getInt(37);
    }

    public UmaPO() {
    }

    public int getId() {
        return id;
    }

    public int getCharaId() {
        return charaId;
    }

    public String getCardName() {
        return cardName;
    }

    public String getUmaName() {
        return umaName;
    }

    public int getRarity() {
        return rarity;
    }

    public int getLimited() {
        return limited;
    }

    public int getSkillSetId() {
        return skillSetId;
    }

    public List<AvailableSkillSetPO> getSkillSet() {
        return skillSet;
    }

    public int getTalentSpeed() {
        return talentSpeed;
    }

    public int getTalentStamina() {
        return talentStamina;
    }

    public int getTalentPow() {
        return talentPow;
    }

    public int getTalentGuts() {
        return talentGuts;
    }

    public int getTalentWiz() {
        return talentWiz;
    }

    public int getTalentGroupId() {
        return talentGroupId;
    }

    public int getBgId() {
        return bgId;
    }

    public int getGetPieceId() {
        return getPieceId;
    }

    public int getRawRunningStyle() {
        return rawRunningStyle;
    }

    public String getRunningStyle() {
        return runningStyle;
    }

    public String getIcon() {
        return icon;
    }

    public List<TalentUpgradePO> getTalentUpgrade() {
        return talentUpgrade;
    }

    public SkillDataPO getUniqueSkill() {
        return uniqueSkill;
    }

    public int getDefaultSpeed() {
        return defaultSpeed;
    }

    public int getDefaultStamina() {
        return defaultStamina;
    }

    public int getDefaultPow() {
        return defaultPow;
    }

    public int getDefaultGuts() {
        return defaultGuts;
    }

    public int getDefaultWiz() {
        return defaultWiz;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public int getMaxPow() {
        return maxPow;
    }

    public int getMaxGuts() {
        return maxGuts;
    }

    public int getMaxWiz() {
        return maxWiz;
    }

    public int getProperDistanceShort() {
        return properDistanceShort;
    }

    public int getProperDistanceMile() {
        return properDistanceMile;
    }

    public int getProperDistanceMiddle() {
        return properDistanceMiddle;
    }

    public int getProperDistanceLong() {
        return properDistanceLong;
    }

    public int getProperGroundTurf() {
        return properGroundTurf;
    }

    public int getProperGroundDirt() {
        return properGroundDirt;
    }

    public int getProperRunningStyleNige() {
        return properRunningStyleNige;
    }

    public int getProperRunningStyleSenko() {
        return properRunningStyleSenko;
    }

    public int getProperRunningStyleSashi() {
        return properRunningStyleSashi;
    }

    public int getProperRunningStyleOikomi() {
        return properRunningStyleOikomi;
    }
}
