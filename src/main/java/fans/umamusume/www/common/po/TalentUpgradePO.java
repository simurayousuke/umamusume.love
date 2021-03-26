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

public class TalentUpgradePO {

    public class ItemSet {
        private int id;
        private int category;
        private ItemPO item;
        private int num;

        public ItemSet(int category, int id, int num) {
            this.category = category;
            this.id = id;
            item = ItemPO.getItem(id);
            this.num = num;
        }

        public ItemSet() {
        }

        public int getCategory() {
            return category;
        }

        public int getId() {
            return id;
        }

        public ItemPO getItem() {
            return item;
        }

        public int getNum() {
            return num;
        }
    }

    private int id;
    private int talentGroupId;
    private int talentLevel;
    private List<ItemSet> items = new ArrayList<>();

    private static Map<Integer, List<TalentUpgradePO>> talentGroupId_talentUpgrade_table = null;
    private static final Log LOGGER = Log.getLog(TalentUpgradePO.class);

    public static List<TalentUpgradePO> getTalentUpgradeData(int groupId){
        return getAllTalentUpgradeData().getOrDefault(groupId,null);
    }

    public static Map<Integer, List<TalentUpgradePO>> getAllTalentUpgradeData(){
        if(null==talentGroupId_talentUpgrade_table){
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            talentGroupId_talentUpgrade_table = new HashMap<>();
            try {
                stmt = c.createStatement();
                String sql = "select id,\n" +
                        "       talent_group_id,\n" +
                        "       talent_level,\n" +
                        "       item_category_1,\n" +
                        "       item_id_1,\n" +
                        "       item_num_1,\n" +
                        "       item_category_2,\n" +
                        "       item_id_2,\n" +
                        "       item_num_2,\n" +
                        "       item_category_3,\n" +
                        "       item_id_3,\n" +
                        "       item_num_3,\n" +
                        "       item_category_4,\n" +
                        "       item_id_4,\n" +
                        "       item_num_4,\n" +
                        "       item_category_5,\n" +
                        "       item_id_5,\n" +
                        "       item_num_5,\n" +
                        "       item_category_6,\n" +
                        "       item_id_6,\n" +
                        "       item_num_6\n" +
                        "from card_talent_upgrade;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    TalentUpgradePO set=new TalentUpgradePO(rs);
                    if (!talentGroupId_talentUpgrade_table.containsKey(set.talentGroupId))
                        talentGroupId_talentUpgrade_table.put(set.talentGroupId, new ArrayList<>());
                    talentGroupId_talentUpgrade_table.get(set.talentGroupId).add(set);
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                talentGroupId_talentUpgrade_table = null;
                LOGGER.error(e.getMessage());
            }
        }
        return talentGroupId_talentUpgrade_table;
    }

    public TalentUpgradePO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        talentGroupId = rs.getInt(2);
        talentLevel = rs.getInt(3);
        for (int i = 5; i < 21; i += 3) {
            int itemId = rs.getInt(i);
            if (itemId == 0)
                break;
            int num = rs.getInt(i + 1);
            int category = rs.getInt(i - 1);
            items.add(new ItemSet(category, itemId, num));
        }
    }

    public TalentUpgradePO() {
    }

    public int getId() {
        return id;
    }

    public int getTalentGroupId() {
        return talentGroupId;
    }

    public int getTalentLevel() {
        return talentLevel;
    }

    public List<ItemSet> getItems() {
        return items;
    }
}
