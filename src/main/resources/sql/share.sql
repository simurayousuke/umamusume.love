#sql("findByToken")
SELECT *
FROM t_share
WHERE token =
#para(0)
#end