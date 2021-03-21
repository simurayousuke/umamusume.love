#sql("all")
select id,
       title,
       content,
       priority,
       view_count,
       like_count,
       create_time,
       update_time,
       tag,
       has_tag
from t_announcement order by priority desc,update_time desc;
#end
