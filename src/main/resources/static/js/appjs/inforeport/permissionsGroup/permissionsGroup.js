
var prefix = "/inforeport/permissionsGroup"
$(function() {
	load();
});

function load() {
	$('#exampleTable').bootstrapTreeTable(
					{
					    id : 'groupId',
                        code : 'groupId',
                        parentCode : 'parentId',
						type : "GET", // 请求数据的ajax类型
						url : prefix + "/list", // 服务器数据的加载地址
						//toolbar : '#exampleToolbar',
						ajaxParams : {}, // 请求数据的ajax的data属性
                        expandColumn : '0', // 在哪一列上面显示展开按钮
						striped : true, // 是否各行渐变色
                        bordered : true, // 是否显示边框
                        expandAll : false, // 是否全部展开
						columns : [
                                {
                                    visible:false,
                                    title : '编号',
                                    field : 'groupId',
                                    align : 'center',
                                    valign : 'center',
                                    width : '50px',
                                    checkbox : true
								},
                                {
									field : 'name', 
									title : '组名称',
                                    valign : 'center',
                                    witth :20
								},
                                {
									field : 'orderNum',
                                    title : '排序',
                                    align : 'center',
                                    valign : 'center'
								},
                                {
									field : 'delFlag',
                                    title : '状态',
                                    align : 'center',
                                    valign : 'center',
                                    formatter : function(item, index) {
                                        if (item.delFlag == '0') {
                                            return '<span class="label label-danger">禁用</span>';
                                        } else if (item.delFlag == '1') {
                                            return '<span class="label label-primary">正常</span>';
                                        }
                                    }
								},
                                {
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.groupId
												+ '\')"><i class="fa fa-edit"></i></a> ';
                                       var a = '<a class="btn btn-primary btn-sm ' + s_addfollowing_h + '" href="#" title="增加下級"  mce_href="#" onclick="add(\''
                                                + row.groupId
                                                + '\')"><i class="fa fa-plus"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.groupId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.groupId
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + a + d ;
									}
								} ]
					});
}
function reLoad() {
	load();
}
function add(pId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/' + pId
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'groupId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}


function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['groupId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}