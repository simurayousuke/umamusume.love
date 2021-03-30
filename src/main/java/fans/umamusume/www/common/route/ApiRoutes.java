package fans.umamusume.www.common.route;

import com.jfinal.config.Routes;
import com.jfinal.ext.interceptor.POST;
import fans.umamusume.www.common.api.v1.*;
import fans.umamusume.www.common.interceptor.ApiExceptionInterceptor;
import fans.umamusume.www.common.interceptor.NeedLogin;

public class ApiRoutes extends Routes {

    @Override
    public void config() {
        addInterceptor(new ApiExceptionInterceptor());
        addInterceptor(new POST());
        addInterceptor(new NeedLogin());
        add("/api/v1/supportcard", SupportCardApi.class);
        add("/api/v1/login", LoginApi.class);
        add("/api/v1/reg", RegApi.class);
        add("/api/v1/succession", SuccessionApi.class);
        add("/api/v1/announcement", AnnouncementApi.class);
        add("/api/v1/comment", CommentApi.class);
        add("/api/v1/simulate",SimulateApi.class);
        add("/api/v1/share", ShareApi.class);
        //add("/api/v1/fujishiro",FujishiroApi.class);
    }

}
