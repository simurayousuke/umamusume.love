package fans.umamusume.www.common.api.v1;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.kit.JsoupKit;
import fans.umamusume.www.common.model.Comment;
import fans.umamusume.www.common.model.User;

public class CommentApi extends ApiV1 {

    @Before(EvictInterceptor.class)
    @CacheName("comment")
    public void add() {
        User user = getAttr("user");
        Integer target1 = getInt("t1");
        Integer target2 = getInt("t2", 0);
        if (null == target1) {
            renderJson(Ret.fail("msg", "参数错误！"));
        } else if (null == user) {
            renderJson(Ret.fail("msg", "请先登录！"));
        } else if (user.getPriority() < 0) {
            renderJson(Ret.fail("msg", "账号被禁用！"));
        } else {
            Comment comment = new Comment();
            comment.setUid(user.getUid());
            comment.setTarget1(target1);
            comment.setTarget2(target2);
            comment.setContent(JsoupKit.clean(getPara("content", "")));
            if (comment.save()) {
                renderJson(Ret.ok());

            } else
                renderJson(Ret.fail("msg", "保存失败！"));
        }
    }

    @Before(EvictInterceptor.class)
    @CacheName("comment")
    public void remove() {
        Integer id = getInt("id");
        Boolean deleted = getBoolean("deleted", true);
        if (null != id) {
            Comment comment = Comment.dao.findById(id);
            if (null != comment) {
                comment.setDeleted(deleted);
                if (comment.update())
                    renderJson(Ret.ok());
                else
                    renderJson(Ret.fail());
            } else
                renderJson(Ret.ok());
        } else
            renderJson(Ret.ok());
    }

}
