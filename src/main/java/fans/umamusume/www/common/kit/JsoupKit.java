package fans.umamusume.www.common.kit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupKit {

    private static final Whitelist whitelist = Whitelist.basic();

    /** 配置过滤化参数, 不对代码进行格式化 */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        whitelist.addAttributes(":all", "style");
    }
    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }

}
