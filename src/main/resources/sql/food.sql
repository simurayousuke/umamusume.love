#sql("unionByFid")
SELECT F.fid, F.ean, F.foodname, F.uploader upid, C.*, U.username uploader
FROM t_food F
         LEFT JOIN t_composition C ON F.composition = C.cid
         left join t_user U on F.uploader = U.uid
WHERE F.fid =
#para(0)
#end

#sql("paginateByBarcode")
SELECT F.fid,
       F.ean,
       F.foodname,
       F.uploader upid,
       C.calorie,
       C.protein,
       C.fat,
       C.carbohydrate,
       U.username uploader
FROM t_food F
         LEFT JOIN t_composition C ON F.composition = C.cid
         left join t_user U on F.uploader = U.uid
WHERE F.ean =
#para(0)
#end

#sql("paginateByUid")
SELECT F.fid,
       F.ean,
       F.foodname,
       F.uploader upid,
       C.calorie,
       C.protein,
       C.fat,
       C.carbohydrate,
       U.username uploader
FROM t_food F
         LEFT JOIN t_composition C ON F.composition = C.cid
         left join t_user U on F.uploader = U.uid
WHERE F.uploader =
#para(0)
#end

#sql("paginateAll")
SELECT F.fid,
       F.ean,
       F.foodname,
       F.uploader upid,
       C.calorie,
       C.protein,
       C.fat,
       C.carbohydrate,
       U.username uploader
FROM t_food F
         LEFT JOIN t_composition C ON F.composition = C.cid
         left join t_user U on F.uploader = U.uid
#end
