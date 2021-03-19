package fans.umamusume.www.index;

import com.jfinal.core.ActionHandler;
import com.jfinal.core.ActionReporter;
import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;
import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.interceptor.ExceptionInterceptor;
import fans.umamusume.www.common.po.SkillDataPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IndexController extends MyController {

    private List<SkillDataPO> getSkillList(){
        Connection c = Config.createMasterConnection();
        Statement stmt = null;
        List<SkillDataPO> list=new ArrayList<>();
        try {
            stmt = c.createStatement();
            String sql="select a.id as 技能id,b.text as 技能名称,c.text as 技能描述,condition_1 as 触发条件,float_ability_time_1 as 生效时长," +
                    "float_cooldown_time_1 as 冷却时间, ability_type_1_1 as 效果1, float_ability_value_1_1 as 数值1,ability_type_1_2 as 效果2,float_ability_value_1_2 as 数值2," +
                    "ability_type_1_3 as 效果3,float_ability_value_1_3 as 数值3 from skill_data as a left join text_data as b on b.\"index\"=a.id and b.category=47 left join text_data as c " +
                    "on c.\"index\"=a.id and c.category=48;";
            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                list.add(new SkillDataPO(rs));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.out.print( e.getClass().getName() + ": " + e.getMessage() );
        }
        return list;
    }

    public void index() {
        //todo remake home page
        title("ウマファン");
        set("skills",getSkillList());

        render("index.html");
    }
}
