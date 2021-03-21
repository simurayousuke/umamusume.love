package fans.umamusume.www.common;

import com.jfinal.server.undertow.UndertowServer;

public class Start {

    public static final String version = "0.4.1 Beta";
    public static boolean devMode;

    public static void main(String[] args) {
        Config.loadConfig();
        devMode = Config.p.getBoolean("devMode", false);
        UndertowServer.start(Config.class, 80, devMode);
    }

}
