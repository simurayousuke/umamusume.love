package fans.umamusume.www.common.base;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MyValidator extends Validator {

    protected abstract void validate(Controller c) ;

    protected abstract void handleError(Controller c) ;

    protected boolean isSameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
