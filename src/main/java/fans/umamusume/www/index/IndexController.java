package fans.umamusume.www.index;

import fans.umamusume.www.common.base.MyController;

public class IndexController extends MyController {

    public void index() {
        //todo remake home page
        title("主页");
        render("index.html");
    }

    public void file(){
        title("文件下载");
        render("file.html");
    }

    public void ads(){
        renderText("google.com, pub-4096429106764610, DIRECT, f08c47fec0942fa0");
    }
}
