package fans.umamusume.www.common;

import com.jfinal.config.*;
import com.jfinal.i18n.I18nInterceptor;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import fans.umamusume.www.common.handler.StaticHandler;
import fans.umamusume.www.common.interceptor.StaticInterceptor;
import fans.umamusume.www.common.kit.SharedMethodLib;
import fans.umamusume.www.common.model._MappingKit;
import fans.umamusume.www.common.route.ApiRoutes;
import fans.umamusume.www.common.route.FrontRoutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config extends JFinalConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    public static final String OSSPATH="umasset.zhuangcloud.cn";
    public static final String SITENAME="马娘粉";
    public static final String RECAPTCHA="6LfiE5IaAAAAAFlW2OKv4oLU4tx1YDw_KdJb04pK";

    static Prop p;

    static void loadConfig() {
        if (null == p)
            p = PropKit.useFirstFound("config-dev.txt", "config.txt");
    }

    public static DruidPlugin createDruidPlugin() {
        loadConfig();
        String connUrl = "jdbc:mysql://" + p.get("mysqlHost") + "/" + p.get("mysqlDatabase");
        return new DruidPlugin(connUrl, p.get("mysqlUser"), p.get("mysqlPassword"));
    }

    public static Connection createMasterConnection(){
        loadConfig();
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:"+ p.get("masterdbHost"));
            c.setAutoCommit(false);
        } catch (SQLException throwables) {
            LOGGER.error("Creating failure.",throwables);
        }
            return c;
    }

    public void configRoute(Routes me) {
        me.add(new FrontRoutes());
        me.add(new ApiRoutes());
    }

    public void configEngine(Engine me) {
        me.addSharedFunction("/view/common/_layout.html");
       /* me.setBaseTemplatePath("/view");*/
        me.addSharedMethod(new SharedMethodLib());
        me.setCompressorOn('\n');
    }

    public void configConstant(Constants me) {
        loadConfig();
        Start.devMode = p.getBoolean("devMode", false);
        me.setDevMode(Start.devMode);
        me.setInjectDependency(true);
        me.setInjectSuperClass(true);
        me.setI18nDefaultBaseName("i18n");
        me.setI18nDefaultLocale("zh_CN");
        me.setToSlf4jLogFactory();
        me.setJsonFactory(new MixedJsonFactory());
        me.setViewType(ViewType.JFINAL_TEMPLATE);
        me.setJsonDatePattern("yyyy-MM-dd HH:mm:ss");
    }

    public void configPlugin(Plugins me) {
        me.add(new EhCachePlugin());

        RedisPlugin redisUser = new RedisPlugin("user", p.get("redisHost"), p.getInt("redisPort"), p.get("redisPassword"));
        me.add(redisUser);

        DruidPlugin dp = createDruidPlugin();
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setBaseSqlTemplatePath("/sql");
        arp.addSqlTemplate("all.sql");
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
        _MappingKit.mapping(arp);
        me.add(arp);

    }


    public void configInterceptor(Interceptors me) {
//        me.addGlobalActionInterceptor(new ExceptionInterceptor());
        me.addGlobalActionInterceptor(new I18nInterceptor());
        me.addGlobalActionInterceptor(new StaticInterceptor());
    }

    public void configHandler(Handlers me) {
        me.add(new StaticHandler());
    }

    public void onStart() {

    }
}