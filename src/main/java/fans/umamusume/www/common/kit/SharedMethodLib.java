package fans.umamusume.www.common.kit;

import com.jfinal.kit.StrKit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedMethodLib {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM月dd日HH:mm 追记");

    public String _generateUuid() {
        return StrKit.getRandomUUID();
    }

    public String _formatDate(Date date){
        return dateFormat.format(date);
    }

    public String _formatDate2(Date date){
        return dateFormat2.format(date);
    }

    public char _formatProper(int proper){
        if(8==proper)
            return 'S';
        return (char)(72-proper);
    }

}
