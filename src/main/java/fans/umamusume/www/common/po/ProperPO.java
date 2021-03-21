package fans.umamusume.www.common.po;

import com.jfinal.log.Log;
import fans.umamusume.www.common.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProperPO {

    private int id;
    private int speed = 100;
    private int stamina = 100;
    private int power = 100;
    private int konjyou = 100;
    private int iq = 100;

    private static ProperPO[] distance_proper_list = null;
    private static ProperPO[] ground_proper_list = null;
    private static ProperPO[] style_proper_list = null;

    private static final Log LOGGER = Log.getLog(ProperPO.class);

    public enum type {distance, ground, style}

    ;

    public static ProperPO[] getProperList(type t) {
        Connection c = Config.createMasterConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "";
            int i = 0;
            ResultSet rs;
            switch (t) {
                case distance:
                    if (distance_proper_list != null)
                        return distance_proper_list;
                    distance_proper_list = new ProperPO[9];
                    sql = "select id,proper_rate_speed,proper_rate_power from race_proper_distance_rate;";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        if (i > 8)
                            break;
                        distance_proper_list[i++] = new ProperPO(rs, t);
                    }
                    rs.close();
                    break;
                case ground:
                    if (ground_proper_list != null)
                        return ground_proper_list;
                    ground_proper_list = new ProperPO[9];
                    sql = "select id, proper_rate from race_proper_ground_rate;";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        if (i > 8)
                            break;
                        ground_proper_list[i++] = new ProperPO(rs, t);
                    }
                    rs.close();
                    break;
                case style:
                    if (style_proper_list != null)
                        return style_proper_list;
                    style_proper_list = new ProperPO[9];
                    sql = "select id, proper_rate from race_proper_runningstyle_rate;";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        if (i > 8)
                            break;
                        style_proper_list[i++] = new ProperPO(rs, t);
                    }
                    rs.close();
                    break;
            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            switch (t) {
                case distance:
                    distance_proper_list = null;
                    break;
                case ground:
                    ground_proper_list = null;
                    break;
                case style:
                    style_proper_list = null;
                    break;
            }
            LOGGER.error(e.getMessage());
        }
        switch (t) {
            case distance:
                return distance_proper_list;
            case ground:
                return ground_proper_list;
            case style:
                return style_proper_list;
        }
        return null;
    }

    public ProperPO(ResultSet rs, type t) throws SQLException {
        id=rs.getInt(1);
        speed = rs.getInt(2) / 100;
        switch (t) {
            case distance:
                power = rs.getInt(3) / 100;
                break;
            case ground:
            case style:
                power = speed;
                stamina = speed;
                konjyou = speed;
                iq = speed;
                break;
        }

    }

    public ProperPO() {
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStamina() {
        return stamina;
    }

    public int getPower() {
        return power;
    }

    public int getKonjyou() {
        return konjyou;
    }

    public int getIq() {
        return iq;
    }
}
