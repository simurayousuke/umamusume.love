package fans.umamusume.www.announcement;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import fans.umamusume.www.common.base.MyController;
import fans.umamusume.www.common.interceptor.NeedAdmin;
import fans.umamusume.www.common.model.Announcement;

public class AnnouncementController extends MyController {

    public void index() {
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
                            "from t_announcement where id=? order by priority desc,update_time desc", id).get(0);
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

}
