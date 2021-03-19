package fans.umamusume.www.common.po;

import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillSetPO {

    private int id;
    private int skill_id1;
    private int skill_level1;
    private int skill_id2;
    private int skill_level2;
    private int skill_id3;
    private int skill_level3;
    private int skill_id4;
    private int skill_level4;
    private int skill_id5;
    private int skill_level5;
    private int skill_id6;
    private int skill_level6;
    private int skill_id7;
    private int skill_level7;
    private int skill_id8;
    private int skill_level8;
    private int skill_id9;
    private int skill_level9;
    private int skill_id10;
    private int skill_level10;

    private SkillDataPO skill1;
    private SkillDataPO skill2;
    private SkillDataPO skill3;
    private SkillDataPO skill4;
    private SkillDataPO skill5;
    private SkillDataPO skill6;
    private SkillDataPO skill7;
    private SkillDataPO skill8;
    private SkillDataPO skill9;
    private SkillDataPO skill10;

    private static List<SkillSetPO> all_skillsets_list=null;
    private static final Log LOGGER=Log.getLog(SkillSetPO.class);

    public SkillSetPO(ResultSet rs) throws SQLException {
        id=rs.getInt(1);
        skill_id1=rs.getInt(2);
        skill_level1=rs.getInt(3);
        skill_id2=rs.getInt(4);
        skill_level2=rs.getInt(5);
        skill_id3=rs.getInt(6);
        skill_level3=rs.getInt(7);
        skill_id4=rs.getInt(8);
        skill_level4=rs.getInt(9);
        skill_id5=rs.getInt(10);
        skill_level5=rs.getInt(11);
        skill_id6=rs.getInt(12);
        skill_level6=rs.getInt(13);
        skill_id7=rs.getInt(14);
        skill_level7=rs.getInt(15);
        skill_id8=rs.getInt(16);
        skill_level8=rs.getInt(17);
        skill_id9=rs.getInt(18);
        skill_level9=rs.getInt(19);
        skill_id10=rs.getInt(20);
        skill_level10=rs.getInt(21);
        skill1=SkillDataPO.getSkillData(skill_id1);
        skill2=SkillDataPO.getSkillData(skill_id2);
        skill3=SkillDataPO.getSkillData(skill_id3);
        skill4=SkillDataPO.getSkillData(skill_id4);
        skill5=SkillDataPO.getSkillData(skill_id5);
        skill6=SkillDataPO.getSkillData(skill_id6);
        skill7=SkillDataPO.getSkillData(skill_id7);
        skill8=SkillDataPO.getSkillData(skill_id8);
        skill9=SkillDataPO.getSkillData(skill_id9);
        skill10=SkillDataPO.getSkillData(skill_id10);
    }

    public static List<SkillSetPO> getAllSkillsetsList() {
        if (null == all_skillsets_list) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_skillsets_list = new ArrayList<>();
            try {
                stmt = c.createStatement();
                String sql = "select id, skill_id1, skill_level1, skill_id2, skill_level2, skill_id3," +
                        " skill_level3, skill_id4, skill_level4, skill_id5, skill_level5, skill_id6, " +
                        "skill_level6, skill_id7, skill_level7, skill_id8, skill_level8, skill_id9," +
                        " skill_level9, skill_id10, skill_level10 from skill_set";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    all_skillsets_list.add(new SkillSetPO(rs));
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_skillsets_list = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_skillsets_list;
    }

    public SkillSetPO() {
    }

    public static SkillSetPO getSkillSet(int id) {
        for(SkillSetPO s:getAllSkillsetsList())
            if(s.getId()==id)
                return s;
        return null;
    }

    public int getId() {
        return id;
    }

    public int getSkill_level1() {
        return skill_level1;
    }

    public int getSkill_id2() {
        return skill_id2;
    }

    public int getSkill_level2() {
        return skill_level2;
    }

    public int getSkill_id3() {
        return skill_id3;
    }

    public int getSkill_level3() {
        return skill_level3;
    }

    public int getSkill_id4() {
        return skill_id4;
    }

    public int getSkill_level4() {
        return skill_level4;
    }

    public int getSkill_id5() {
        return skill_id5;
    }

    public int getSkill_level5() {
        return skill_level5;
    }

    public int getSkill_id6() {
        return skill_id6;
    }

    public int getSkill_level6() {
        return skill_level6;
    }

    public int getSkill_id7() {
        return skill_id7;
    }

    public int getSkill_level7() {
        return skill_level7;
    }

    public int getSkill_id8() {
        return skill_id8;
    }

    public int getSkill_level8() {
        return skill_level8;
    }

    public int getSkill_id9() {
        return skill_id9;
    }

    public int getSkill_level9() {
        return skill_level9;
    }

    public int getSkill_id10() {
        return skill_id10;
    }

    public int getSkill_level10() {
        return skill_level10;
    }

    public int getSkill_id1() {
        return skill_id1;
    }

    public SkillDataPO getSkill1() {
        return skill1;
    }

    public SkillDataPO getSkill2() {
        return skill2;
    }

    public SkillDataPO getSkill3() {
        return skill3;
    }

    public SkillDataPO getSkill4() {
        return skill4;
    }

    public SkillDataPO getSkill5() {
        return skill5;
    }

    public SkillDataPO getSkill6() {
        return skill6;
    }

    public SkillDataPO getSkill7() {
        return skill7;
    }

    public SkillDataPO getSkill8() {
        return skill8;
    }

    public SkillDataPO getSkill9() {
        return skill9;
    }

    public SkillDataPO getSkill10() {
        return skill10;
    }
}
