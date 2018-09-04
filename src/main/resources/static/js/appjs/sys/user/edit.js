// 以下为官方示例
var permissionsGroupIds;
$().ready(function() {
    getPermissionsGroupTreeData();
	validateRule();
	// $("#signupForm").validate();
});

$.validator.setDefaults({
	submitHandler : function() {
        getAllSelectNodes();
		update();
	}
});
function update() {
    $('#permissionsGroupIds').val(permissionsGroupIds);
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/user/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function setCheckedRoles() {
	var roleIds = $("#roleIds").val();
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			email : {
				required : true,
				email : true
			},
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required"
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
			username : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上"
			},
			password : {
				required : icon + "请输入您的密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			},
			email : icon + "请输入您的E-mail",
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}
function getPermissionsGroupTreeData() {
    var userId = $('#userId').val();
    $.ajax({
        type : "GET",
        url : "/inforeport/permissionsGroup/tree/"+userId,
        success : function(data) {
            loadPermissionsGroupTree(data);
        }
    });
}
function loadPermissionsGroupTree(data) {
    $('#permissionsGroupTree').jstree({
        'core' : {
            'data' : data
        },
        "checkbox" : {
            "three_state" : true,
        },
        "plugins" : [ "wholerow", "checkbox" ]
    });
    $('#permissionsGroupTree').jstree('open_all');
}
function getAllSelectNodes() {
    var ref = $('#permissionsGroupTree').jstree(true); // 获得整个树
    permissionsGroupIds = ref.get_selected(false).toString(); // 获得所有选中节点的，返回值为数组
	var semiGroupids="_";//获得所有半选中节点
    $("#permissionsGroupTree").find(".jstree-undetermined").each(function(i, element) {
        /*permissionsGroupIds.push($(element).closest('.jstree-node').attr("id"));*/
        semiGroupids=semiGroupids==="_" ? semiGroupids+$(element).closest('.jstree-node').attr("id") : semiGroupids + ',' +$(element).closest('.jstree-node').attr("id");
    });
    permissionsGroupIds=permissionsGroupIds+semiGroupids;
}