package fans.umamusume.www.common.kit;

import com.jfinal.kit.StrKit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedMethodLib {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd （E） HH:mm:ss");

    public String _generateUuid() {
        return StrKit.getRandomUUID();
    }

    public String _formatDate(Date date){
        return dateFormat.format(date);
    }

}
