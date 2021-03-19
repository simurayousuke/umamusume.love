package fans.umamusume.www.common.po;

import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupportCardPO {

    private int id;
    private String name;
    private String prefix;
    private int chara_id;
    private String postfix;
    private int rarity;
    private int effect_table_id;
    private int unique_effect_id;
    private int skill_set_id;
    private int lv;
    private int type_0;
    private int value_0;
    private int type_1;
    private int value_1;
    private SkillSetPO skillSet;
    private SupportEffectTablePO supportEffectTable;

    private static final int max1=10054;
    private static final int max2=20025;
    private static final int max3=30027;

    private static List<SupportCardPO> all_support_cards_list = null;
    private static final Log LOGGER = Log.getLog(SupportCardPO.class);

    public SupportCardPO() { }

    public SupportCardPO(ResultSet rs) throws SQLException {
        id=rs.getInt(1);
        name=rs.getString(2);
        prefix=rs.getString(3);
        chara_id=rs.getInt(4);
        postfix=rs.getString(5);
        rarity=rs.getInt(6);
        effect_table_id=rs.getInt(7);
        unique_effect_id=rs.getInt(8);
        skill_set_id=rs.getInt(9);
        lv=rs.getInt(10);
        type_0=rs.getInt(11);
        value_0=rs.getInt(12);
        type_1=rs.getInt(13);
        value_1=rs.getInt(14);
        skillSet=SkillSetPO.getSkillSet(skill_set_id);
        supportEffectTable=SupportEffectTablePO.getSupportEffectTable(effect_table_id);
    }

    public static SupportCardPO getSupportCard(int id){
        for(SupportCardPO s : getAllSupportCards()){
            if(s.getId()==id)
                return s;
        }
        return null;
    }

    public static List<SupportCardPO> getAllSupportCards() {
        if (all_support_cards_list == null) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_support_cards_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id," +
                        "b.text || c.text," +
                        "b.text," +
                        "a.chara_id," +
                        "c.text," +
                        "a.rarity," +
                        "a.effect_table_id," +
                        "a.unique_effect_id," +
                        "a.support_card_type," +
                        "a.skill_set_id," +
                        "d.lv," +
                        "d.type_0," +
                        "d.value_0," +
                        "d.type_1," +
                        "d.value_1," +
                        "a.exchange_item_id," +
                        "a.command_type," +
                        "a.command_id," +
                        "a.detail_pos_x," +
                        "a.detail_pos_y," +
                        "a.detail_scale," +
                        "a.detail_rot_z " +
                        "from support_card_data as a " +
                        "left join text_data as b " +
                        "on b.\"index\" = a.id and b.category = 76 " +
                        "left join text_data as c " +
                        "on c.\"index\" = a.chara_id and c.category = 170 " +
                        "left join support_card_unique_effect as d " +
                        "on d.id = a.id;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    all_support_cards_list.add(new SupportCardPO(rs));
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_support_cards_list = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_support_cards_list;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getCharaId() {
        return chara_id;
    }

    public String getPostfix() {
        return postfix;
    }

    public int getRarity() {
        return rarity;
    }

    public int getEffectTableId() {
        return effect_table_id;
    }

    public int getUniqueEffectId() {
        return unique_effect_id;
    }

    public int getSkillSetId() {
        return skill_set_id;
    }

    public int getLv() {
        return lv;
    }

    public int getType0() {
        return type_0;
    }

    public int getValue0() {
        return value_0;
    }

    public int getType1() {
        return type_1;
    }

    public int getValue1() {
        return value_1;
    }

    public SkillSetPO getSkillSet() {
        return skillSet;
    }

    public SupportEffectTablePO getSupportEffectTable() {
        return supportEffectTable;
    }

    public String getIcon() {
        if((id>10000&&id<=max1)||(id>20000&&id<=max2)||(id>30000&&id<=max3))
                return Config.OSSPATH + "/supportcard/" + String.valueOf(id) + ".png";
        return Config.OSSPATH + "/supportcard/00000.png";
    }
}
