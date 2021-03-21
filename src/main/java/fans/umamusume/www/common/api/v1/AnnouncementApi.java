package fans.umamusume.www.common.api.v1;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.interceptor.NeedAdmin;
import fans.umamusume.www.common.interceptor.NeedLogin;
import fans.umamusume.www.common.model.Announcement;


public class AnnouncementApi extends ApiV1 {

    @Clear(NeedLogin.class)
    @Before(NeedAdmin.class)
    public void add() {
        Announcement announcement = getModel(Announcement.class, "");
        if (!StrKit.isBlank(announcement.getTag()))
            announcement.setHasTag(true);
        if (StrKit.isBlank(announcement.getTitle()))
            announcement.setTitle("无标题");
        if (StrKit.isBlank(announcement.getContent()))
            announcement.setContent("无正文");
        else
            announcement.setContent(announcement.getContent().replaceAll("\r\n", "<br>"));
        if (announcement.save())
            renderJson(Ret.ok());
        else
            renderJson(Ret.fail());
    }

    @Clear({NeedLogin.class, POST.class})
    @Before(NeedAdmin.class)
    public void remove() {
        Integer id = getInt();
        if (null != id) {
            Announcement announcement = Announcement.dao.findById(id);
            if (null != announcement) {
                announcement.setDeleted(true);
                if (announcement.update())
                    renderJson(Ret.ok());
                else
                    renderJson(Ret.fail());
            } else
                renderJson(Ret.ok());
        } else
            renderJson(Ret.ok());
    }

}
