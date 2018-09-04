
var prefix = "/inforeport/separate"
var pid="";
var imeino="";
$(function() {
    exampleTable();
    //groupSelectData(1);
});

$('.nav-tabs li a').click( function (e) {
    e.preventDefault()
    $(this).tab('show')
    var _id=$(this).attr('href');
    switch(_id){
        case "#tab-1":
            exampleTable();
            break;
        case "#tab-2":
            //exampleTable2();
            break;
        case "#tab-3":
            break;
        default:
            exampleTable();
            break;
    }
});
function gotoImei(groupid){
    pid=groupid;
    exampleTable2();
    $('#mytab a[href="#tab-2"]').tab('show');
    $('#exampleTable2').bootstrapTable('refresh');
    //$('#mytab li:eq(1) a').tab('show');
}
function gotoDetails(imei){
    imeino=imei;
    exampleTable3();
    $('#mytab li:eq(2) a').tab('show');
    $('#exampleTable3').bootstrapTable('refresh');
}
function exampleTable() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listcity", // 服务器数据的加载地址
                        showRefresh : true,
					//	showToggle : true,
						showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : queryParams,
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
                                    visible:false,
									field : 'groupid',
									title : '主键标示'
								},
								{
									field : 'name',
									title : '地市名称'
								},
																{
									field : 'imeinoct',
									title : '设备总数'
								},
																{
									field : 'sptts',
									title : '报警设备数'
								},
																{
									field : 'bimeicount',
									title : '报警次数'
								},
								{
                                    visible:true,
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
                                        var f = '<a class="btn btn-success btn-sm" href="#" title="IMEI统计"  mce_href="#" onclick="gotoImei(\''
                                            + row.groupid
                                            + '\')"><i class="fa fa-key"></i></a> ';
										return f ;
									}
								} ]
					});
}

function exampleTable2() {
    $('#exampleTable2')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/listregion", // 服务器数据的加载地址
                showRefresh : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : queryParams,
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        visible:false,
                        field : 'groupid',
                        title : '主键标示'
                    },
                    {
                        field : 'name',
                        title : '地区名称'
                    },
                    {
                        field : 'imeino',
                        title : 'IMEI号'
                    },
                    {
                        field : 'sptts',
                        title : '报警总数'
                    },
                    {
                        visible:true,
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="gotoDetails(\''
                                + row.imeino
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return f;
                        }
                    } ]
            });
}

function exampleTable3() {
    $('#exampleTable3')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/listdetails", // 服务器数据的加载地址
                //	showToggle : true,
                //	showColumns : true,
                showRefresh : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : queryParams,
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        field : 'brandname',
                        title : '品牌名'
                    },
                    {
                        field : 'tename',
                        title : '型号名'
                    },
                    {
                        field : 'bindimei',
                        title : 'IMEI号'
                    },
                    {
                        field : 'promsisdn',
                        title : '手机号'
                    },
                    {
                        visible:false,
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d ;
                        }
                    } ]
            });
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
laydate.render({
    elem: '#startDate',
    value: new Date(),
    isInitValue: true,
    showBottom: false
});
laydate.render({
    elem: '#endDate',
    value: new Date(),
    isInitValue: true,
    showBottom: false,
    format: 'yyyy-MM-dd'
});

function queryParams() {
    return {
        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
        /*limit: params.limit,
        offset:params.offset,*/
        /*groupId:$("#setgroupid").val(),*/
        pid:pid,
        startDate:$("#startDate").val(),
        endDate:$("#endDate").val(),
        imeino:imeino
    };
}
/*选择下拉框*/
function groupSelectData(id) {
    $.ajax({
        type : 'post',
        url : prefix + "/groupSelectData",
        data : {
            'pid' : id
        },
        success : function(datas) {//返回list数据并循环获取
            var select = $("#setgroupid");
            for (var i = 0; i < datas.length; i++) {

                select.append("<option value='"+datas[i].groupid+"'>" + datas[i].name + "</option>");
            }
            $('.selectpicker').selectpicker('val', '');
            $('.selectpicker').selectpicker('refresh');
        }
    });
}