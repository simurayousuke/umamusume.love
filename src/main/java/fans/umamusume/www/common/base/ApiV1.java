package fans.umamusume.www.common.base;

import fans.umamusume.www.common.kit.DateKit;
import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import fans.umamusume.www.common.model.User;

import java.util.Date;

public class ApiV1 extends Controller {

    protected Res getRes() {
        String locale = getCookie("_locale");
        return StrKit.notBlank(locale) ? I18n.use(locale) : I18n.use();
    }

    protected void fail() {
        renderJson(Ret.fail());
    }

    protected void fail(String msg) {
        renderJson(Ret.fail("msg", msg));
    }

    protected void fail(Ret ret) {
        renderJson(ret.setFail());
    }

    protected void success() {
        renderJson(Ret.ok());
    }

    protected void success(String msg) {
        renderJson(Ret.ok("msg", msg));
    }

    protected void success(Ret ret) {
        renderJson(ret.setOk());
    }

    protected <T> T bean(Class<T> beanClass) {
        return getBean(beanClass, "");
    }

    protected Integer getUid() {
        return ((User) getAttr("user")).getUid();
    }

    protected User getUser() {
        return ((User) getAttr("user"));
    }

    protected boolean isSameDay(Date date1,Date date2){
        return DateKit.isSameDay(date1,date2);
    }

}
