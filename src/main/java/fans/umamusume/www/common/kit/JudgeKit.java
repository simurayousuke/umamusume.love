package fans.umamusume.www.common.kit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeKit {

    public static boolean isHttpUrl(String url) {
        String regex = "^((https|http)://)(.*)[.](.*)$";
//        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
//                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(url.trim());
        return mat.matches();
    }

    public static void main(String[] args) {
        ArrayList<String> urls = new ArrayList<String>(Arrays.asList("https://www.zhuangcloud.cn/s",
                "https://zhuangcloud.cn/s",
                "https://zhuangcloud.cn/s/U36Bzi",
                "https://www.openzui.com/#search/icon-link",
                "https://t.bilibili.com/?spm_id_from=666.25.b_696e7465726e6174696f6e616c486561646572.30",
                "hahaha",
                "自分が一番好きな季節と言うと、春だ。春にキーワードを付けると、私は生命力を選ぶ。木は緑色に染まられ、花が咲く。何もかも冬の居眠りから目覚めてくる感じがする。放課後、いつも帰る途中で並木の陰をゆっくり歩いている。私にとってこれはとても素敵な時間である。葉の間を透いて漏れてくる光を浴びながら、道端でこっそり生きている雑草や野良の花を目で味わうのがなかなか辞められない。そうすると、頭の中の悩み事が一斉に飛んでしまい、目の前の時間を楽しむことができるからと思う。いつの間にか、蜂も働き始めた。春は短い、知らぬ間になくなっていく。気がついたらもう夏になっていた。本当に物哀れな季節だな。よし、今年も頑張らないとな。",
                "https://zhuangcloud.cn/share?token=cdd37f92eb7e4b2f878425d6b6d7efa9&date=2020-07-19&_locale=ja_JP&shorten=true",
                "2.3"
        ));
        urls.forEach((value) -> {
            System.out.println(value + ":" + isHttpUrl(value));
        });

    }

}
