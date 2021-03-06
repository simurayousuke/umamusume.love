package fans.umamusume.www.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("t_announcement", "id", Announcement.class);
		arp.addMapping("t_comment", "id", Comment.class);
		arp.addMapping("t_lottery", "id", Lottery.class);
		arp.addMapping("t_lottery_user", "id", LotteryUser.class);
		arp.addMapping("t_user", "uid", User.class);
	}
}

