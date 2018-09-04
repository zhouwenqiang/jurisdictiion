var permissionsGroupIds;
$().ready(function() {
	getPermissionsGroupTreeData();
	validateRule();
		 $("input[type=radio][name=status][value='"+1+"']").prop("checked", true);
});

$.validator.setDefaults({
	submitHandler : function() {
        getAllSelectNodes();
		save();
	}
});
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
function save() {
    $('#permissionsGroupIds').val(permissionsGroupIds);
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/user/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
	    ignore: ".ignore",
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2,
				remote : {
					url : "/sys/user/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						username : function() {
							return $("#username").val();
						}
					}
				}
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
			deptName : {
            				required : true
                    },
			email : {
				required : true,
				email : true
			},
			status: {
                    required: true
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
				minlength : icon + "用户名必须两个字符以上",
				remote : icon + "用户名已经存在"
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
			deptName : {
            				required : icon + "请选择部门"
            },
            status: {
                       required: icon + "状态必须选择一个"
          },
			email : icon + "请输入您的E-mail",

		},
		errorPlacement:function(error,element) {
                if (element.is(":radio"))
                    error.insertAfter(element.parent());
                else
                    error.insertAfter(element);
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
    $.ajax({
        type : "GET",
        url : "/inforeport/permissionsGroup/tree",
        success : function(termissionsGroupTree) {
            loadPermissionsGroupTree(termissionsGroupTree);
        }
    });
}
function loadPermissionsGroupTree(termissionsGroupTree) {
    $('#permissionsGroupTree').jstree({
        'core' : {
            'data' : termissionsGroupTree
        },
        "checkbox" : {
            "three_state" : true,
        },
        "plugins" : [ "wholerow", "checkbox" ]
    });
}
function getAllSelectNodes() {
    var ref = $('#permissionsGroupTree').jstree(true); // 获得整个树
    permissionsGroupIds = ref.get_selected(false).toString(); // 获得所有选中节点的，返回值为数组
    var semiGroupids="";//获得所有半选中节点
    $("#permissionsGroupTree").find(".jstree-undetermined").each(function(i, element) {
        /*permissionsGroupIds.push($(element).closest('.jstree-node').attr("id"));*/
        semiGroupids=semiGroupids==="_" ? semiGroupids+$(element).closest('.jstree-node').attr("id") : semiGroupids + ',' +$(element).closest('.jstree-node').attr("id");
    });
    permissionsGroupIds=permissionsGroupIds+"_"+semiGroupids;
}