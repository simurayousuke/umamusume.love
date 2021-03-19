package fans.umamusume.www.common.route;

import fans.umamusume.www.common.api.v1.*;
import fans.umamusume.www.common.interceptor.ApiExceptionInterceptor;
import fans.umamusume.www.common.interceptor.NeedLogin;
import com.jfinal.config.Routes;
import com.jfinal.ext.interceptor.POST;
import fans.umamusume.www.common.api.v1.*;

public class ApiRoutes extends Routes {

    @Override
    public void config() {
        addInterceptor(new ApiExceptionInterceptor());
        addInterceptor(new POST());
        addInterceptor(new NeedLogin());
        //add("/api/v1/food", FoodApi.class);
        add("/api/v1/login", LoginApi.class);
        add("/api/v1/reg", RegApi.class);
        //add("/api/v1/log", LogApi.class);
        //add("/api/v1/statistic", StatisticApi.class);
        //add("/api/v1/share", ShareApi.class);
        //add("/api/v1/target",TargetApi.class);
        //add("/api/v1/shortUrl", ShortUrlApi.class);
        //add("/api/v1/fujishiro",FujishiroApi.class);
    }

}
