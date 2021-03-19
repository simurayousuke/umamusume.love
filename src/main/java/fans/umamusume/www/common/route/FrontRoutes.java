package fans.umamusume.www.common.route;

import fans.umamusume.www.common.base.ErrorController;
import fans.umamusume.www.common.base.ResJsController;
import fans.umamusume.www.common.interceptor.ExceptionInterceptor;
import fans.umamusume.www.index.IndexController;
import fans.umamusume.www.login.LoginController;
import fans.umamusume.www.reg.RegController;
import fans.umamusume.www.version.VersionController;
import com.jfinal.config.Routes;

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
        //add("/upload", UploadController.class);
        //add("/food", FoodController.class);
        //add("/log", LogController.class);
        add("/js/res", ResJsController.class, "common");

        //add("/statistic", StatisticController.class);
        //add("/share", ShareController.class);

        //add("/target", TargetController.class);
        //add("/s", ShortUrlController.class,"shortUrl");

        //add("/fujishiro", FujishiroController.class);
    }
}