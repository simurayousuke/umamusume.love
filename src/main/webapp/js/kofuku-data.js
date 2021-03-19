let q1,q2,q3,q4,q5,__data,__total,max,min,average;

function piechart(){
    let data,total;
    let options = {
        scaleShowLabels: true, // 展示标签
        scaleLabel: "<%=label%>点:<%=value%>人",
        tooltipTemplate: "<%if (label){%><%=label%>点: <%}%><%= value %>人",
    };

    data=[];
    total=0.0;
    for(let i=0; i<7; i++){
        total+=(i+1)*q1[i];
        if(q1[i]>0)
            data.push({value:q1[i],label:i+1});
    }
    $("#q1").pieChart(data, options);
    $("#average-q1").text("平均 "+$.formatNum(total/__data.length,3)+" 点");

    data=[];
    total=0.0;
    for(let i=0; i<7; i++){
        total+=(i+1)*q2[i];
        if(q2[i]>0)
            data.push({value:q2[i],label:i+1});
    }
    $("#q2").pieChart(data, options);
    $("#average-q2").text("平均 "+$.formatNum(total/__data.length,3)+" 点");

    data=[];
    total=0.0;
    for(let i=0; i<7; i++){
        total+=(i+1)*q3[i];
        if(q3[i]>0)
            data.push({value:q3[i],label:i+1});
    }
    $("#q3").pieChart(data, options);
    $("#average-q3").text("平均 "+$.formatNum(total/__data.length,3)+" 点");

    data=[];
    total=0.0;
    for(let i=0; i<7; i++){
        total+=(i+1)*q4[i];
        if(q4[i]>0)
            data.push({value:q4[i],label:i+1});
    }
    $("#q4").pieChart(data, options);
    $("#average-q4").text("平均 "+$.formatNum(total/__data.length,3)+" 点");

    data=[];
    total=0.0;
    for(let i=0; i<7; i++){
        total+=(i+1)*q5[i];
        if(q5[i]>0)
            data.push({value:q5[i],label:i+1});
    }
    $("#q5").pieChart(data, options);
    $("#average-q5").text("平均 "+$.formatNum(total/__data.length,3)+" 点");
}

function init(data){
    __data=data.data;
    $("#title").text("合計 "+__data.length+" 件回答");
    __total=[];
    max=0;
    min=35;
    q1=[0,0,0,0,0,0,0];
    q2=[0,0,0,0,0,0,0];
    q3=[0,0,0,0,0,0,0];
    q4=[0,0,0,0,0,0,0];
    q5=[0,0,0,0,0,0,0];
    let _total=0.0;
    __data.forEach(v=>{
        q1[v.q1-1]++;
        q2[v.q2-1]++;
        q3[v.q3-1]++;
        q4[v.q4-1]++;
        q5[v.q5-1]++;
        let total = v.q1 + v.q2 + v.q3 + v.q4 + v.q5;
        _total+=total;
        __total.push(total);
        if(total>max)
            max=total;
        if(total<min)
            min=total;
    })
    average=$.formatNum(_total/__data.length,3);
    piechart();
    $("#max").text("最大得点 "+max);
    $("#min").text("最低得点 "+min);
    $("#average").text("平均得点 "+average);
}

$(document).ready(function(){
       $.ajax({url:"/api/v1/fujishiro/getkofuku",success:(data)=>{
                init(data);
            },error:()=>{
            $.error("データ読み込めませんでした。");
            }
        });
});