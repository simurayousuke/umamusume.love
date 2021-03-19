package fans.umamusume.www.login;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.Redis;
import fans.umamusume.www.common.model.User;

public class LoginService {

    private User dao = new User().dao();

    private String hash(String password, String salt) {
        String ret = HashKit.sha256(password + salt);
        for (int i = 0; i < 2; i++) {
            ret = HashKit.sha256(ret + salt);
        }
        return ret;
    }

    public User findById(int id) {
        return dao.findFirst(dao.getSqlPara("user.findById", id));
    }

    private User findByUsername(String username) {
        return null == username ? null : dao.findFirst(dao.getSqlPara("user.findByUsername", username));
    }

    public User findByToken(String token) {
        User user = (null == token ? null : Redis.use("user").get(token));
//        return null == user ? null : findById(user.getUid());
        return user;
    }

    private String setAndGetToken(User user) {
        String token = StrKit.getRandomUUID();
        Redis.use("user").setex(token, 60 * 60 * 24, user);
        return token;
    }

    public boolean isLogin(String token) {
        return null != findByToken(token);
    }

    public String login(String username, String password) {
        User user = findByUsername(username);
        if (null == user)
            return null;
        if (!user.getPwd().equals(hash(password, user.getSalt())))
            return null;
        return setAndGetToken(user);
    }

    public void logout(String token) {
        if (null == token)
            return;
        Redis.use("user").del(token);
    }

    public String reg(String username, String password) {
        if (null != findByUsername(username))
            return null;
        User user = new User();
        user.setUsername(username);
        user.setSalt(StrKit.getRandomUUID());
        user.setPwd(hash(password, user.getSalt()));
        if (!user.save())
            return null;
        return setAndGetToken(user);
    }

}
