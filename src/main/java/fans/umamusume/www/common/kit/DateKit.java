package fans.umamusume.www.common.kit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateKit {

    public static boolean isSameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

}
