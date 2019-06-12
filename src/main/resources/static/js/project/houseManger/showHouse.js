layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#houses'
        ,url:'../houses/queryHouses'
        ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev' , 'page', 'next', 'skip'] //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            ,groups: 1 //只显示 1 个连续页码
            ,first: false //不显示首页
            ,last: false //不显示尾页

        },
        limits:[10,20,50]
        ,cols: [[
            {field:'id', width:'10%', title: 'ID'}
            ,{field:'projectNmae', width:'15%', title: '工程名称'}
            ,{field:'houseNum', width:'15%', title: '房屋编号', sort: true}
            ,{field:'checkDate', width:'10%', title: '检测日期', templet: function(d){
                    return formatDate(new Date(d.checkDate));
                }
            },{ width:178, align:'15%', toolbar: '#barDemo'}
        ]]
    });

    table.on('tool(demo)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){

        } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });
});

function formatDate(now) {
    var year = now.getFullYear();
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var date = ("0" + now.getDate()).slice(-2);
    var hour = ("0" + (now.getHours())).slice(-2);
    var minute = ("0" + (now.getMinutes())).slice(-2);
    var second = now.getSeconds();
    return year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":" + second;
}