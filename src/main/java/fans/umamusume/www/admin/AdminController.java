package fans.umamusume.www.admin;

import com.jfinal.aop.Before;
import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.interceptor.NeedAdmin;

public class AdminController extends MyController {

    @Before(NeedAdmin.class)
    public void index(){
        title("管理后台");
        render("index.html");
    }

}
