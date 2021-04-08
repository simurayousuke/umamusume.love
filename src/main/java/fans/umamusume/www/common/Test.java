package fans.umamusume.www.common;

import fans.umamusume.www.common.base.Random;
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

    public static String doubleTrans(float d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

    public static final int start = 1;
    public static final int end = 87;
    public static final int times = 100;
    public static void main(String[] args) throws InterruptedException {
        int number = 0;
        //做100次
        for (int i = 0; i < times; ++i) {
            //从start到end中随机一个整数出来
            number = new Random().nextInt(end - start + 1) + start;
            System.out.println(number);
            Thread.sleep(100);
        }
        //最后一次被随机到的人中奖
        System.out.println("最终中奖者：第" + number + "人");
    }

}
