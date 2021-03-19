#sql("findByUid")
SELECT *
FROM t_target
WHERE uid =
#para(0)
#end