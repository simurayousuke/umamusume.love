package fans.umamusume.www.common.route;

import com.jfinal.config.Routes;
import fans.umamusume.www.admin.AdminController;
import fans.umamusume.www.announcement.AnnouncementController;
import fans.umamusume.www.comment.CommentController;
import fans.umamusume.www.common.base.ErrorController;
import fans.umamusume.www.common.base.ResJsController;
import fans.umamusume.www.common.interceptor.ExceptionInterceptor;
import fans.umamusume.www.index.IndexController;
import fans.umamusume.www.login.LoginController;
import fans.umamusume.www.proper.ProperController;
import fans.umamusume.www.reg.RegController;
import fans.umamusume.www.share.ShareController;
import fans.umamusume.www.simulate.SimulateController;
import fans.umamusume.www.skill.SkillController;
import fans.umamusume.www.statistic.StatisticController;
import fans.umamusume.www.supportcard.SupportCardController;
import fans.umamusume.www.uma.UmaController;
import fans.umamusume.www.version.VersionController;

/*
    403,404,500,logout
 */
public class FrontRoutes extends Routes {
    public void config() {
        addInterceptor(new ExceptionInterceptor());
        setBaseViewPath("/view");
        add("/", IndexController.class, "index");
        add("/login", LoginController.class);
        add("/version", VersionController.class);
        add("/reg", RegController.class);
        add("/error", ErrorController.class, "common");
        add("/skill", SkillController.class);
        add("/supportcard", SupportCardController.class);
        add("/uma", UmaController.class);
        add("/js/res", ResJsController.class, "common");
        add("/proper", ProperController.class);
        add("/announcement", AnnouncementController.class);
        add("/comment", CommentController.class);
        add("/statistic", StatisticController.class);
        add("/admin", AdminController.class);
        add("/simulate", SimulateController.class);
        add("/share", ShareController.class);
    }
}