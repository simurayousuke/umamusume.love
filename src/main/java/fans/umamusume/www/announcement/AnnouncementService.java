package fans.umamusume.www.announcement;

import com.jfinal.plugin.activerecord.Page;
import fans.umamusume.www.common.model.Announcement;

public class AnnouncementService {

    private static final int PAGE_SIZE = 10;



    public static Page<Announcement> getPage(int page) {
        return Announcement.dao.paginateByCache("announcement", "announcement"+page, page, PAGE_SIZE,
                "select id,title ,content,priority, view_count,like_count,create_time,update_time,tag,has_tag,label_class",
                "from t_announcement order by priority desc,update_time desc");
    }

}
