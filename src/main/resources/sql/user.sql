#sql("findByUsername")
SELECT *
FROM t_user
WHERE username =
#para(0)
#end
#sql("findById")
SELECT *
FROM t_user
WHERE uid =
#para(0)
#end