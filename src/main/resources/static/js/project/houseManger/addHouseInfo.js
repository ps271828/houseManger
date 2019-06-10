$(function () {
    crackTypeChange('00');
})

function deleteProjectItem(sort) {
    $("#item" + sort).remove();
}

var sort = 0;

function buildAddItem() {
    var allItem = $("#projectItemInfo").children("div");
    if (allItem.length > 1) {
        sort = parseInt(allItem.eq(allItem.length - 2).find("input").eq(0).val()) + 1;
        allItem.eq(allItem.length - 2).find("input").eq(0).val(sort);
    }

    var itemContent = '<div id="item' + sort + '" class="layui-row" style="margin-top: 25px;">';
    itemContent += '<input type="hidden" value="' + sort + '" />';
    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '构件序号';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="serialNum' + sort + '" style="width: 98.5%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

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
    itemContent += '构件方向';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="radio" name="itemDirection' + sort + '" value="0" >东';
    itemContent += '<input type="radio" name="itemDirection' + sort + '" value="1" >南';
    itemContent += '<input type="radio" name="itemDirection' + sort + '" value="2" >西';
    itemContent += '<input type="radio" name="itemDirection' + sort + '" value="3" >北';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row crack_item_style">';
    itemContent += '<div id="crackItem' + sort + '0" class="layui-row">';
    itemContent += '<input type="hidden" value="' + sort + '" />';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '裂缝性质';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="radio" name="crackType' + sort + '0" value="0" >装饰面层';
    itemContent += '<input type="radio" name="crackType' + sort + '0" value="1" >构建';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '墙面';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="radio" name="wallDamageType' + sort + '0" value="0" >龟裂';
    itemContent += '<input type="radio" name="wallDamageType' + sort + '0" value="1" >空鼓';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '裂缝方向';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="checkbox" name="crackDirection' + sort + '0" value="0" >斜';
    itemContent += '<input type="checkbox" name="crackDirection' + sort + '0" value="1" >竖';
    itemContent += '<input type="checkbox" name="crackDirection' + sort + '0" value="3" >水';
    itemContent += '<input type="checkbox" name="crackDirection' + sort + '0" value="4" >不规则';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '最大宽度mm';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="maxLength' + sort + '0" style="width: 98.5%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '示意图';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="exmapleImage' + sort + '0" type="file" style="width: 100%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md12" style="text-align: right">';
    itemContent += '<button onclick="deleteCrackItem(\'' + sort + '0\')">刪除裂缝项</button>';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md12" >';
    itemContent += '<button onclick="addCrackItem(\'' + sort + '\')">新增裂缝项</button>';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '全景图';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="fullExampleImage' + sort + '" type="file" style="width: 100%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '其他';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="others' + sort + '" style="width: 98.5%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md12" style="text-align: right">';
    itemContent += '<button onclick="deleteProjectItem(' + sort + ')">刪除此构件项</button>';
    itemContent += '</div>';
    itemContent += '</div>';

    return itemContent;
}

function addProjectItem() {
    var item = buildAddItem();
    $(item).insertBefore($("#projectItemInfo").children("div").eq($("#projectItemInfo").children("div").length - 1))
    crackTypeChange(sort + '0');
}

function saveProjectInfo() {
    var param = buildSaveData();

    if (Object.keys(param).length == 0) {
        return;
    }

    var formData = new FormData();
    formData.append("houseMainInfoVo", param);

    $.ajax({
        url : "../houses/saveHouseInfo",
        data : param,
        type : "post",
        contentType: false, // 注意这里应设为false
        processData: false,
        cache: false,
        success : function (data) {
            if (data.code == '000000') {
                alert(data.data)
            }else {
                alert(data.info);
            }
        }
    })
}

function buildSaveData() {
    var obj = new Object();
    var formData = new FormData();
    //工程名称
    var projectNmae = $('#projectNmae').val();
    if ($.trim(projectNmae) == "") {
        alert("工程名称不能为空，请重新输入！");
        return new Object();
  ``  }
    formData.append("projectNmae", projectNmae);

    //门牌号

    //检测日期
    var checkDate = $('#checkDate').val();
    if ($.trim(checkDate) == "") {
        alert("检测日期不能为空，请重新输入！");
        return new Object();
    }
    // obj.checkDate = checkDate;
    formData.append("checkDate", checkDate);

    var masterName = $('#masterName').val();
    if ($.trim(masterName) == "") {
        alert("户主姓名不能为空，请重新输入！");
        return new Object();
    }
    obj.masterName = masterName;
    formData.append("masterName", masterName);

    var itemArr = $('#projectItemInfo').children('div');
    var itemList = [];
    for (var i = 0; i <= itemArr.length - 2; i++) {
        var currItem = itemArr.eq(i);
        var currItemSort = currItem.find('input').eq(0).val();

        var itemObj = new Object();

        var itemSerial = $('#serialNum' + currItemSort).val();
        if ($.trim(itemSerial) == "") {
            alert("第" + i + "个构件序列号为空，请重新输入！");
            return new Object();
        }
        itemObj.itemSerial = itemSerial;

        var itemLocation = $('input:radio[name="itemLocation' + currItemSort + '"]:checked').val();
        if ($.trim(itemLocation) == "") {
            alert("第" + i + "个构件位置未选择，请重新选择！");
            return new Object();
        }
        itemObj.itemLocation = itemLocation;

        var itemDirection = $('input:radio[name="itemDirection' + currItemSort + '"]:checked').val();
        if ($.trim(itemDirection) == "") {
            alert("第" + i + "个构件方向未选择，请重新选择！");
            return new Object();
        }
        itemObj.itemDirection = itemDirection;

        var crackArr = currItem.find('.crack_item_style').children('div');
        var crackList = [];
        for (var j = 0; j <= crackArr.length - 2; j++) {
            var currCrack = crackArr.eq(j);

            var crackObj = new Object();

            var crackSort = currItemSort + currCrack.find('input').eq(0).val();

            var crackType = $('input:radio[name="crackType' + crackSort + '"]:checked').val();
            if ($.trim(crackType) == "") {
                alert("第" + i + "个构件,第" + j + "个裂缝项裂缝类型没有选择，请重新选择！");
                return new Object();
            }
            crackObj.crackType = crackType;

            if (crackType == '1') {
                var wallDamageType = $('input:radio[name="wallDamageType' + crackSort + '"]:checked').val();
                if ($.trim(wallDamageType) == "") {
                    alert("第" + i + "个构件,第" + j + "个裂缝项墙面损坏类型没有选择，请重新选择！");
                    return new Object();
                }
                crackObj.wallDamageType = wallDamageType;
            }

            var crackDirection = $('input:checkbox[name="crackDirection' + crackSort + '"]:checked').val();
            if (crackDirection.length == 0) {
                alert("第" + i + "个构件,第" + j + "个裂缝项裂缝方向没有选择，请重新选择！");
                return new Object();
            }
           /* var directValue = "";
            crackDirection.each(function () {
                directValue += $(this).val() + ",";
            })*/
            crackObj.crackDirection = crackDirection;

            var maxLength = $('#maxLength' + crackSort).val();
            if (isNaN($.trim(maxLength))) {
                alert("第" + i + "个构件,第" + j + "个裂缝项裂缝长度非法，请重新输入！");
                return new Object();
            }
            crackObj.maxLength = maxLength;

            var exampleImage = $('#exmapleImage' + crackSort).val();
            var suffix = exampleImage.substring(exampleImage.lastIndexOf(".") + 1, exampleImage.length).toUpperCase();
            if (suffix != "BMP" && suffix != "JPG" && suffix != "JPEG" && suffix != "PNG" && suffix != "GIF") {
                alert("第" + i + "个构件,第" + j + "个裂缝项示意图不为图片格式，请重新选择！");
                return new Object();
            }
            crackObj.exampleImageFile = document.getElementById('exmapleImage' + crackSort).files[0];
            crackList.push(crackObj)
        }

        itemObj.itemCrackVoList = crackList;

        var fullExampleImage = $('#fullExampleImage' + currItemSort).val();
        var suffix = fullExampleImage.substring(fullExampleImage.lastIndexOf(".") + 1, fullExampleImage.length).toUpperCase();
        if (suffix != "BMP" && suffix != "JPG" && suffix != "JPEG" && suffix != "PNG" && suffix != "GIF") {
            alert("第" + i + "个构件全景图不为图片格式，请重新选择！");
            return new Object();
        }
        itemObj.fullExampleImage = document.getElementById('fullExampleImage' + currItemSort).files[0];

        var othersInfo = $('#others' + currItemSort).val();
        itemObj.comment = othersInfo;

        itemList.push(itemObj);
    }
    obj.houseItemVoList = itemList;
    return obj;
}

function addCrackItem(itemNum) {
    //获取对应构件的裂缝的序号
    var currSort = '0';
    var lastSecondDiv = undefined;
    $("#projectItemInfo").children('div').each(function () {
        if ($(this).find('input').eq(0).val() == itemNum) {
            lastSecondDiv = $(this).find('.crack_item_style').eq(0).children('div');
        }
    });
    if ($("#projectItemInfo").find('.crack_item_style').length > 0) {
        currSort = lastSecondDiv.eq(lastSecondDiv.length - 2).find('input').eq(0).val();
    }

    var crackNum = itemNum + (parseInt(currSort) + 1);

    var itemContent = '<div id="crackItem' + crackNum + '" class="layui-row">';
    itemContent += '<input type="hidden" value="' + sort + '" />';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '裂缝性质';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="radio" name="crackType' + crackNum + '" value="0" >装饰面层';
    itemContent += '<input type="radio" name="crackType' + crackNum + '" value="1" >构建';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '墙面';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="radio" name="wallDamageType' + crackNum + '" value="0" >龟裂';
    itemContent += '<input type="radio" name="wallDamageType' + crackNum + '" value="1" >空鼓';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '裂缝方向';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input type="checkbox" name="crackDirection' + crackNum + '" value="0" >斜';
    itemContent += '<input type="checkbox" name="crackDirection' + crackNum + '" value="1" >竖';
    itemContent += '<input type="checkbox" name="crackDirection' + crackNum + '" value="3" >水';
    itemContent += '<input type="checkbox" name="crackDirection' + crackNum + '" value="4" >不规则';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '最大宽度mm';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="maxLength' + crackNum + '" style="width: 98.5%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md2">';
    itemContent += '示意图';
    itemContent += '</div>';
    itemContent += '<div class="layui-col-md10">';
    itemContent += '<input id="exmapleImage' + crackNum + '" type="file" style="width: 100%;" />';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '<div class="layui-row">';
    itemContent += '<div class="layui-col-md12" style="text-align: right">';
    itemContent += '<button onclick="deleteCrackItem(\'' + crackNum + '\')">刪除裂缝项</button>';
    itemContent += '</div>';
    itemContent += '</div>';

    itemContent += '</div>';

    $(itemContent).insertBefore(lastSecondDiv.last());
    lastSecondDiv.eq(lastSecondDiv.length - 2).find('input').eq(0).val(crackNum);
    crackTypeChange(crackNum);
}

function deleteCrackItem(deleteCrackItem) {
    $("#crackItem" + deleteCrackItem).remove();
}

function crackTypeChange(sortNum) {
    $("input:radio[name='crackType" + sortNum + "']").change(function () {
        if ($(this).val() == '1') {
            $("input:radio[name='wallDamageType" + sortNum + "']").eq(0).parent('div').parent('div').hide();
        } else {
            $("input:radio[name='wallDamageType" + sortNum + "']").eq(0).parent('div').parent('div').show();
        }
    });

}