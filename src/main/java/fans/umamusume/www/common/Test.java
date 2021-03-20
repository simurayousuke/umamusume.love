package fans.umamusume.www.common;

import fans.umamusume.www.common.po.SuccessionRelationPO;
import fans.umamusume.www.common.po.TextSet;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final Map<String, String> condition_special_table = new HashMap<String, String>() {{
        put("is_finalcorner==1&corner==0", "最终直线");
    }};

    private static void printRelationTable() {
        int[][] a = SuccessionRelationPO.getAllCharaRelations();
        String s[] = new String[SuccessionRelationPO.getCharaIndexTable().size()];
        int row = 0;
        System.out.print(",");
        for (Integer chara_id : SuccessionRelationPO.getCharaIndexTable().keySet()) {
            s[row] = TextSet.CHARA_NAME_CN.get(chara_id);
            System.out.print(s[row++] + ",");
        }
        System.out.println();
        row = 0;
        for (int i[] : a) {
            System.out.print(s[row++] + ",");
            for (int j : i) {
                System.out.print(j + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printRelationTable();
    }

}
