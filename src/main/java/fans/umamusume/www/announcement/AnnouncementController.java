package fans.umamusume.www.announcement;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.interceptor.NeedAdmin;
import fans.umamusume.www.common.model.Announcement;

public class AnnouncementController extends MyController {

    public void index() {
        title("公告");
        Page<Announcement> page = AnnouncementService.getPage(getInt(0, 1));
        set("page", page);
        render("index.html");
    }

    public void detail() {
        Integer id = getParaToInt();
        if (null == id) {
            title("公告不存在");
        } else {
            Announcement announcement = Announcement.dao.findByCache("announcement", "detail" + id,
                    "select id,title ,content,priority, view_count,like_count,create_time,update_time,tag,has_tag,label_class " +
                            "from t_announcement where id=? and deleted=0 order by priority desc,update_time desc", id).get(0);
            title(announcement.getTitle());
            set("announcement", announcement);
        }
        render("detail.html");
    }

    @Before(NeedAdmin.class)
    public void add() {
        title("发布公告");
        render("add.html");
    }

    @Before(NeedAdmin.class)
    public void modify() {
        Integer id = getInt();
        if (null != id) {
            Announcement announcement = Announcement.dao.findById(id);
            if (null != announcement) {
                set("announcement", announcement);
                title("修改公告");
                render("modify.html");
            } else
                redirect("/announcement/admin");
        } else
            redirect("/announcement/admin");

    }

    @Before(NeedAdmin.class)
    public void admin() {
        title("公告管理");
        Page<Announcement> page = AnnouncementService.getPageAdmin(getInt(0, 1));
        set("page", page);
        render("admin.html");
    }

}
