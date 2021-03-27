package fans.umamusume.www.simulate;

import fans.umamusume.www.common.base.MyController;

public class SimulateController extends MyController {

    public void index(){
        title("育成模拟器");
        render("index.html");
    }

}
