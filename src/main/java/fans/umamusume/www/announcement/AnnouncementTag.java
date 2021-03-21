package fans.umamusume.www.announcement;

import java.util.HashMap;
import java.util.Map;

public class AnnouncementTag {

    private int type;

    private String tag;
    private int clazz;

    public static final Map<Integer,String > types=new HashMap<Integer, String>(){{
       put(0,"新功能");
       put(1,"修复");
    }};

    public AnnouncementTag() {
    }

    public AnnouncementTag(int type) {
        this.type = type;
    }
}
