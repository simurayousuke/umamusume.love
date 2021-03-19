#sql("findByShortUrl")
SELECT *
FROM t_short_url
WHERE short_url =
#para(0)
#end