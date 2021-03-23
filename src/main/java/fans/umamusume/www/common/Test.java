package fans.umamusume.www.common;

import fans.umamusume.www.common.kit.JsoupKit;
import fans.umamusume.www.common.po.ProperPO;
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

    private static void printProperTable(ProperPO[] table) {
        String[] ss = TextSet.PROPER_RARITY_LIST;
        System.out.print("适性\t速度\t耐力\t力量\t根性\t智力\n");
        for (ProperPO p : table) {
            System.out.printf("%s\t%d%%\t%d%%\t%d%%\t%d%%\t%d%%\n",
                    ss[p.getId()], p.getSpeed(), p.getStamina(), p.getPower(), p.getKonjyou(), p.getIq());
        }
    }

    private static void printAllProperTable() {
        printProperTable(ProperPO.getProperList(ProperPO.type.distance));
        printProperTable(ProperPO.getProperList(ProperPO.type.ground));
        printProperTable(ProperPO.getProperList(ProperPO.type.style));
    }

    public static void main(String[] args) {
//        printAllProperTable();
        String testHtml = "<div class='div'style='height: 100px;'><a href='\\test'>aaa</a>div 标签的内容 </div><p class='div'style='width: 50px;'>p 标签的内容 </p>";
        System.out.println(JsoupKit.clean(testHtml));// 输出:   div 标签的内容 < p>p 标签的内容 </p>
    }

}
