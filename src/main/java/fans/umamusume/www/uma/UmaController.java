package fans.umamusume.www.uma;

import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.po.SuccessionRelationPO;
import fans.umamusume.www.common.po.TextSet;

import java.util.Map;

public class UmaController extends MyController {

    public void index(){
        title("建设中");
        render("index.html");
    }

    public void relation(){
        Map<Integer,String> charaNameMap=TextSet.CHARA_NAME_CN;
        title("相性关系");
        set("map_chara_id_name", charaNameMap);
        set("list_chara_id", SuccessionRelationPO.getCharaIndexTable().keySet());

        int[][] a = SuccessionRelationPO.getAllCharaRelations();
        String s[] = new String[SuccessionRelationPO.getCharaIndexTable().size()];
        int row = 0;
        for (Integer chara_id : SuccessionRelationPO.getCharaIndexTable().keySet()) {
            s[row++] = charaNameMap.get(chara_id);
        }
        set("list_chara_name",s);
        set("table_relations",SuccessionRelationPO.getAllCharaRelations());
        render("relation.html");
    }

    public void rank(){
        title("建设中");
        render("rank.html");
    }

}
