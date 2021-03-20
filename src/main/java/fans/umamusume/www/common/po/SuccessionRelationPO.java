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

public class SuccessionRelationPO {

    private int id;
    private int relation_type;
    private int chara_id;
    private int relation_point;

    private static Map<Integer, List<SuccessionRelationPO>> all_chara_relations_table = null;
    private static final Log LOGGER = Log.getLog(SuccessionRelationPO.class);

    private static int[][] all_chara_relations = null;
    private static Map<Integer, Integer> chara_index_table = null;

    public static int[][] getAllCharaRelations() {
        if (all_chara_relations == null) {
            int chara_count = getAllCharaRelationsTable().size();
            all_chara_relations = new int[chara_count][chara_count];
            Map<Integer, Integer> table = getCharaIndexTable();
            for (Map.Entry<Integer, List<SuccessionRelationPO>> a : getAllCharaRelationsTable().entrySet()) {
                for (Map.Entry<Integer, List<SuccessionRelationPO>> b : getAllCharaRelationsTable().entrySet()) {
                    int x = getCharaIndexTable().get(a.getKey());
                    int y = getCharaIndexTable().get(b.getKey());
                    if (x == y) {
                        all_chara_relations[x][y] = 0;
                        continue;
                    }
                    int relation = 0;
                    for (SuccessionRelationPO i : a.getValue()) {
                        for (SuccessionRelationPO j : b.getValue()) {
                            if (i.getRelation_type() == j.getRelation_type()) {
                                relation += i.relation_point;
                                break;
                            }
                        }
                    }
                    all_chara_relations[x][y] = relation;
                    all_chara_relations[y][x] = relation;
                }
            }
        }
        return all_chara_relations;
    }

    public static int getCharaRelation(int id1, int id2) {
        return getAllCharaRelations()[getCharaIndexTable().get(id1)][getCharaIndexTable().get(id2)];
    }

    public SuccessionRelationPO(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        relation_type = rs.getInt(2);
        chara_id = rs.getInt(3);
        relation_point = rs.getInt(4);
    }

    public static Map<Integer, Integer> getCharaIndexTable() {
        if (chara_index_table == null) {
            chara_index_table = new HashMap<>();
            int i = 0;
            for (Integer key : getAllCharaRelationsTable().keySet()) {
                chara_index_table.put(key, i++);
            }
        }
        return chara_index_table;
    }

    public static Map<Integer, List<SuccessionRelationPO>> getAllCharaRelationsTable() {
        if (all_chara_relations_table == null) {
            Connection c = Config.createMasterConnection();
            Statement stmt = null;
            all_chara_relations_table = new HashMap<Integer, List<SuccessionRelationPO>>();
            try {
                stmt = c.createStatement();
                String sql = "select a.id, a.relation_type, a.chara_id, sr.relation_point " +
                        "from succession_relation_member as a " +
                        "         left join succession_relation sr on a.relation_type = sr.relation_type";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SuccessionRelationPO temp = new SuccessionRelationPO(rs);
                    int key=temp.getChara_id();
                    if (all_chara_relations_table.containsKey(key)) {
                        all_chara_relations_table.get(key).add(temp);
                    } else {
                        List<SuccessionRelationPO> list = new ArrayList<>();
                        list.add(temp);
                        all_chara_relations_table.put(key, list);
                    }
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                all_chara_relations_table = null;
                LOGGER.error(e.getMessage());
            }
        }
        return all_chara_relations_table;
    }

    public static List<SuccessionRelationPO> getCharaRelationsList(int id) {
        return getAllCharaRelationsTable().getOrDefault(id, null);
    }

    public SuccessionRelationPO() {
    }

    public int getId() {
        return id;
    }

    public int getRelation_type() {
        return relation_type;
    }

    public int getChara_id() {
        return chara_id;
    }

    public int getRelation_point() {
        return relation_point;
    }
}
