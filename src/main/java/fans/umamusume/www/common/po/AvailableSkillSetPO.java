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

public class AvailableSkillSetPO {

    private int id;
    private int skill_set_id;
    private SkillDataPO skill;
    private int rank;

    private static final Log LOGGER = Log.getLog(AvailableSkillSetPO.class);
    private static Map<Integer, List<AvailableSkillSetPO>> id_skillset_table = null;

    public static List<AvailableSkillSetPO> getSkillSet(int id) {
        return getAllSkillSet().getOrDefault(id, null);
    }

    public static Map<Integer, List<AvailableSkillSetPO>> getAllSkillSet() {
        if (null == id_skillset_table) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            id_skillset_table = new HashMap<>();
            try {
                stmt = c.createStatement();
                String sql = "select id, available_skill_set_id, skill_id, need_rank " +
                        "from available_skill_set";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    AvailableSkillSetPO set = new AvailableSkillSetPO(rs);
                    if (!id_skillset_table.containsKey(set.skill_set_id))
                        id_skillset_table.put(set.skill_set_id, new ArrayList<>());
                    id_skillset_table.get(set.skill_set_id).add(set);
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                id_skillset_table = null;
                LOGGER.error(e.getMessage());
            }
        }
        return id_skillset_table;
    }

    public AvailableSkillSetPO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        skill_set_id=rs.getInt(2);
        skill = SkillDataPO.getSkillData(rs.getInt(3));
        rank = rs.getInt(4);
    }

    public AvailableSkillSetPO() {
    }

    public int getId() {
        return id;
    }

    public SkillDataPO getSkill() {
        return skill;
    }

    public int getRank() {
        return rank;
    }
}
