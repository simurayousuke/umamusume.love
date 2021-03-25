package fans.umamusume.www.comment;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class CommentService {

    private static final int PAGE_SIZE = 5;


    public static Page<Record> getComment(int target1, int target2, int page) {
        return Db.paginateByCache("comment", "comment" + target1 + "-" + target2 + "-" + page, page, PAGE_SIZE,
                "select id, a.uid uid, target1, target2, content, a.priority, deleted, a.create_time create_time, a.update_time update_time,b.username username,b.priority priority",
                "from t_comment a left join t_user b on b.uid = a.uid where target1=? and target2=? and deleted=0 order by a.priority desc,update_time desc",
                target1, target2);
    }

}
