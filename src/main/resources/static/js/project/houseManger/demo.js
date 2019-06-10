function deleteItem(sort) {
    $("#item" + sort).remove();
}

layui.use(['form', 'upload'], function () {
    var $ = layui.jquery,
        upload = layui.upload;

    picupload("#uploadImage0", "#preview0");

    function picupload(id, pic) {
        upload.render({
            elem: id,
            multiple: true,
            auto: false,
            choose: function (obj) {
                //预读本地文件示例，不支持ie8
                debugger;
                $(pic).children().remove();
                obj.preview(function (index, file, result) {
                    $(pic).append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style="width:100px;height:100px">')
                });
            },
            done: function (res) {
            }
        });
    }

    var sort = 0;

    function buildAddItem() {
        var allItem = $("#projectItemInfo").children("div");
        if (allItem.length > 1) {
            sort = parseInt(allItem.eq(allItem.length - 2).find("input").eq(0).val()) + 1;
        }

        var itemContent = '<div id="item' + sort + '" class="layui-row">';
        itemContent += '<input type="hidden" value="' + sort + '" />';
        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '构件位置';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<input type="radio" name="itemLocation' + sort + '" value="0" >墙面';
        itemContent += '<input type="radio" name="itemLocation' + sort + '" value="1" >天棚';
        itemContent += '<input type="radio" name="itemLocation' + sort + '" value="2" >地面';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '裂缝性质';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<input type="radio" name="crackType' + sort + '" value="0" >装饰面层';
        itemContent += '<input type="radio" name="crackType' + sort + '" value="1" >构建';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '墙面';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<input type="radio" name="wallDamageType' + sort + '" value="0" >龟裂';
        itemContent += '<input type="radio" name="wallDamageType' + sort + '" value="1" >空鼓';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '裂缝方向';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<input type="checkbox" name="crackDirection' + sort + '" value="0" >斜';
        itemContent += '<input type="checkbox" name="crackDirection' + sort + '" value="1" >竖';
        itemContent += '<input type="checkbox" name="crackDirection' + sort + '" value="3" >水';
        itemContent += '<input type="checkbox" name="crackDirection' + sort + '" value="4" >不规则';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '最大宽度mm';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<input id="maxLength' + sort + '" style="width: 100%;" />';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '示意图';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<div style="border: #C2C2C2 1px solid;width: 100%;color: #C2C2C2;">';
        itemContent += '<div id="uploadImage' + sort + '" style="display: inline-block;text-align: center;border: #C2C2C2 1px solid;margin-top: 5px;margin-bottom: 5px;">';
        itemContent += '<i class="layui-icon layui-icon-add-circle-fine" style="font-size: 80px; "></i><br />';
        itemContent += '添加图片';
        itemContent += '</div>';
        itemContent += '<div id="preview' + sort + '" style="display: inline-block;vertical-align: top;margin-top: 5px;"></div>';
        itemContent += '</div>';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md2">';
        itemContent += '其他';
        itemContent += '</div>';
        itemContent += '<div class="layui-col-md10">';
        itemContent += '<input id="others' + sort + '" style="width: 100%;" />';
        itemContent += '</div>';
        itemContent += '</div>';

        itemContent += '<div class="layui-row">';
        itemContent += '<div class="layui-col-md12" style="text-align: right">';
        itemContent += '<button onclick="deleteItem(' + sort + ')">刪除此构件项</button>';
        itemContent += '</div>';
        itemContent += '</div>';

        return itemContent;
    }

    window.Add = function () {
        var item = buildAddItem();
        var projectItemInfoChild = $("#projectItemInfo").children("div");
        $(item).insertBefore(projectItemInfoChild.eq(projectItemInfoChild.length - 1));
        picupload("#uploadImage" + sort + "", "#preview" + sort + "")
    }
})

function saveProjectInfo() {

}