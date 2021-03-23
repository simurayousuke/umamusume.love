package fans.umamusume.www.comment;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import fans.umamusume.www.common.base.MyController;

public class CommentController extends MyController {

    public void index() {
        title("评论");
        int t1 = getParaToInt(0);
        int t2 = getParaToInt(1);
        int index = getParaToInt(2, 1);
        Page<Record> page = CommentService.getComment(t1, t2, index);
        set("page", page);
        render("index.html");
    }

    public void comment(){
        title("留言板");
        int t1=5;
        int t2=0;
        int index = getParaToInt(0, 1);
        Page<Record> page = CommentService.getComment(t1, t2, index);
        render("comment.html");
    }

}
