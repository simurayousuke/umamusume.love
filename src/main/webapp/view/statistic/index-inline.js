<script>
    let breakfastData=[
        #if(_breakfast)
        #for(i : _breakfast)
    {foodname:'#(i["foodname"]??_res.get("total"))', weight:#(i["weight"]),calorie:#(i["calorie"]), protein: #(i["protein"]),
        fat: #(i["fat"]), carbohydrate: #(i["carbohydrate"]),fid:#(i["fid"]??-1)},
    #end
    #end
    ];

    let lunchData=[
        #if(_lunch)
        #for(i : _lunch)
    {foodname:'#(i["foodname"]??_res.get("total"))', weight:#(i["weight"]),calorie:#(i["calorie"]), protein: #(i["protein"]),
        fat: #(i["fat"]), carbohydrate: #(i["carbohydrate"]),fid:#(i["fid"]??-1)},
    #end
    #end
    ];

    let dinnerData=[
        #if(_dinner)
        #for(i : _dinner)
    {foodname:'#(i["foodname"]??_res.get("total"))', weight:#(i["weight"]),calorie:#(i["calorie"]), protein: #(i["protein"]),
        fat: #(i["fat"]), carbohydrate: #(i["carbohydrate"]),fid:#(i["fid"]??-1)},
    #end
    #end
    ];
</script>