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
            ,{field:'projectName', width:'15%', title: '工程名称'}
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
            //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
            var curWwwPath=window.document.location.href;
            //获取主机地址之后的目次，如： uimcardprj/share/meun.jsp
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            //获取主机地址，如： http://localhost:8083
            var localhostPaht=curWwwPath.substring(0,pos);
            window.location.href = localhostPaht + "/houses/downLoadPdf?houseId=" + data.id;
        } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
            });
        }else if (obj.event == 'detail') {
            var id = data.id + data.checkDate;
                //标志为false 新增一个tab项
            window.parent.btnTabAdd("../houses/editHouseInfo?houseId=" + data.id, id, "房屋详情");
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            window.parent.btnTabChange(id);
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