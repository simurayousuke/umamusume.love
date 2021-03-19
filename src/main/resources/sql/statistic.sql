#sql("unionByUidAndDate")
SELECT F.fid,
       F.ean,
       F.foodname,
       C.calorie * L.weight / 100      calorie,
       C.protein * L.weight / 100      protein,
       C.fat * L.weight / 100          fat,
       C.carbohydrate * L.weight / 100 carbohydrate,
       C.sodium * L.weight / 100       sodium,
       C.salt * L.weight / 100         salt,
       C.cholesterol * L.weight / 100  cholesterol,
       C.sugar * L.weight / 100        sugar,
       C.vitaminA * L.weight / 100     vitaminA,
       C.vitaminD * L.weight / 100     vitaminD,
       C.vitaminE * L.weight / 100     vitaminE,
       C.vitaminK * L.weight / 100     vitaminK,
       C.vitaminB1 * L.weight / 100    vitaminB1,
       C.vitaminB2 * L.weight / 100    vitaminB2,
       C.vitaminB6 * L.weight / 100    vitaminB6,
       C.vitaminB12 * L.weight / 100   vitaminB12,
       C.vitaminC * L.weight / 100     vitaminC,
       C.calcium * L.weight / 100      calcium,
       C.iron * L.weight / 100         iron,
       C.magnesium * L.weight / 100    magnesium,
       C.zinc * L.weight / 100         zinc,
       C.potassium * L.weight / 100    potassium,
       L.type,
       L.weight
FROM t_log L
         LEFT JOIN t_food F ON L.fid = F.fid
         LEFT JOIN t_composition C ON F.composition = C.cid

WHERE L.uid =#para(0)
  AND TO_DAYS(L.meal_date) = TO_DAYS(#para(1))
#end

#sql("unionByUidAndDateAndType")
        SELECT F.fid,
        F.ean,
        F.foodname,
        C.calorie * L.weight / 100 calorie,
        C.protein * L.weight / 100 protein,
        C.fat * L.weight / 100 fat,
        C.carbohydrate * L.weight / 100 carbohydrate,
        C.sodium * L.weight / 100 sodium,
        C.salt * L.weight / 100 salt,
        C.cholesterol * L.weight / 100 cholesterol,
        C.sugar * L.weight / 100 sugar,
        C.vitaminA * L.weight / 100 vitaminA,
        C.vitaminD * L.weight / 100 vitaminD,
        C.vitaminE * L.weight / 100 vitaminE,
        C.vitaminK * L.weight / 100 vitaminK,
        C.vitaminB1 * L.weight / 100 vitaminB1,
        C.vitaminB2 * L.weight / 100 vitaminB2,
        C.vitaminB6 * L.weight / 100 vitaminB6,
        C.vitaminB12 * L.weight / 100 vitaminB12,
        C.vitaminC * L.weight / 100 vitaminC,
        C.calcium * L.weight / 100 calcium,
        C.iron * L.weight / 100 iron,
        C.magnesium * L.weight / 100 magnesium,
        C.zinc * L.weight / 100 zinc,
        C.potassium * L.weight / 100 potassium,
        L.type,
        L.weight
        FROM t_log L
        LEFT JOIN t_food F ON L.fid = F.fid
        LEFT JOIN t_composition C ON F.composition = C.cid
        WHERE L.uid =#para(0)
            AND TO_DAYS(L.meal_date) = TO_DAYS(#para(1))
                    AND L.type =
#para(2)
#end
