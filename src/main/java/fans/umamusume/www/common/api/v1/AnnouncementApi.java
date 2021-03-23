package fans.umamusume.www.common.api.v1;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.interceptor.NeedAdmin;
import fans.umamusume.www.common.interceptor.NeedLogin;
import fans.umamusume.www.common.model.Announcement;


public class AnnouncementApi extends ApiV1 {

    @Clear(NeedLogin.class)
    @Before({NeedAdmin.class, EvictInterceptor.class})
    @CacheName("announcement")
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
        String abs = announcement.getContent().replaceAll("<br>", "");
        if (abs.length() > 100) {
            abs = abs.substring(0, 100) + "...";
        }
        announcement.setAbstract(abs);
        if (announcement.save())
            renderJson(Ret.ok());
        else
            renderJson(Ret.fail());
    }

    @Clear({NeedLogin.class})
    @Before({NeedAdmin.class, EvictInterceptor.class})
    @CacheName("announcement")
    public void modify() {
        Announcement announcement = getModel(Announcement.class, "");
        if (null != announcement) {
            announcement.setHasTag(!StrKit.isBlank(announcement.getTag()));
            if (StrKit.isBlank(announcement.getTitle()))
                announcement.setTitle("无标题");
            if (StrKit.isBlank(announcement.getContent()))
                announcement.setContent("无正文");
            else
                announcement.setContent(announcement.getContent().replaceAll("\r\n", "<br>"));
            String abs = announcement.getContent().replaceAll("<br>", "");
            if (abs.length() > 100) {
                abs = abs.substring(0, 100) + "...";
            }
            announcement.setAbstract(abs);
            if (announcement.update())
                renderJson(Ret.ok());
            else
                renderJson(Ret.fail());
        } else
            renderJson(Ret.fail());
    }

    @Clear(NeedLogin.class)
    @Before({NeedAdmin.class, EvictInterceptor.class})
    @CacheName("announcement")
    public void remove() {
        Integer id = getInt("id");
        Boolean deleted = getBoolean("deleted", true);
        if (null != id) {
            Announcement announcement = Announcement.dao.findById(id);
            if (null != announcement) {
                announcement.setDeleted(deleted);
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
