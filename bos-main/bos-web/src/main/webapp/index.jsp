<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BOS管理系统 首页</title>
		<link href="favicon.ico" rel="icon" type="image/x-icon" />
		<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
		
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="js/ztree/css/zTreeStyle/zTreeStyle.css">
		<script type="text/javascript" src="js/ztree/js/jquery.ztree.all.min.js"></script>
		<script type="text/javascript">
			$(function() {
				
				//设置左边的树菜单--使用简单的json数据
				//1.设置全局参数
				var setting={
						data:{
							simpleData:{
								enable:true,
								pIdKey:'_parentId'								
							}
						},
						callback:{
							//给所有的tree树绑定点击事件
							onClick:clickTree
						}
				};
				//一般来说我们的树菜单是不会写死的，在这里我们选择异步的方式加载树--访问后台获得当前用户的资源
				$.post("user/findMyMenus.action",function(data){
					//data就是我们加载完回应给我们的数据
						$.fn.zTree.init($("#tree"), setting, data);
				},"json")
				
				
				
				
				// 页面加载后 右下角 弹出窗口
				window.setTimeout(function(){
					$.messager.show({
						title:"消息提示",
						msg:'欢迎登录，超级管理员！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
						timeout:5000
					});
				},3000);
				
				$("#btnCancel").click(function(){
					$('#editPwdWindow').window('close');
				});
				
				$("#btnEp").click(function(){
					alert("修改密码");
				});
				
				// 设置全局变量 保存当前正在右键的tabs 标题 
				var currentRightTitle  ;
			});
			
			/*******顶部特效 *******/
			/**
			 * 更换EasyUI主题的方法
			 * @param themeName
			 * 主题名称
			 */
			changeTheme = function(themeName) {
				var $easyuiTheme = $('#easyuiTheme');
				var url = $easyuiTheme.attr('href');
				var href = url.substring(0, url.indexOf('themes')) + 'themes/'
						+ themeName + '/easyui.css';
				$easyuiTheme.attr('href', href);
				var $iframe = $('iframe');
				if ($iframe.length > 0) {
					for ( var i = 0; i < $iframe.length; i++) {
						var ifr = $iframe[i];
						$(ifr).contents().find('#easyuiTheme').attr('href', href);
					}
				}
			};
			// 退出登录
			function logoutFun() {
				$.messager
				.confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
					if (isConfirm) {
						location.href = 'user/logout.action';
					}
				});
			}
			// 修改密码
			function editPassword() {
				$('#editPwdWindow').window('open');
			}
			// 版权信息
			function showAbout(){
				$.messager.alert("bos v2.0综合物流管理平台","设计: 传智播客<br/> 管理员邮箱: itcast_search@163.com <br/>");
			}
			
			//树节点的点击事件
			function clickTree(event, treeId, treeNode){
				//只有当该节点不是父节点的时候才打开选项卡
				if(treeNode.isParent){
					//拿到整颗树的对象 --参数的树的id
					var treeObj = $.fn.zTree.getZTreeObj("tree");
					//获取所有选中的树的节点
					var nodes = treeObj.getSelectedNodes();
					if (nodes.length>0) {
						//展开当前指定节点的子节点的元素,省略后面的参数折叠状态会自动切换。
						treeObj.expandNode(nodes[0]);
					}					
					return ;
				}
					//点击之后打开一个选项卡
					//获取选中树节点的名称
					var name=treeNode.name;
					//先判断这个选项卡是否存在，如果存在 则选中
					if($("#tabs").tabs("exists",name)){
						//如果存在,则选中它
						$("#tabs").tabs("select",name);
					}else{
						//如果不存在，则新建一个新的选项卡
						$("#tabs").tabs("add",{
							title:name,
							closable:true,
							/*注意，加载选项卡内容是时候，如果选用的是href,属性,则只能读取对象文件body区域的内容。
							//所以此处，我们采用iframe标签来加载页面的数据。*/
							content:"<iframe src="+treeNode.pageUrl+" style='width:100%;height:100%;border:0'></iframe>"
						});
					}
				}
				
			
			//
		</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="height:70px;padding:10px;">
			<div>
				<img src="./images/logo.png" border="0">
			</div>
			<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
				[<strong>超级管理员</strong>]，欢迎你！您使用[<strong>${loginUser.username}</strong>]IP登录！
			</div>
			<div style="position: absolute; right: 5px; bottom: 10px; ">
				<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
				<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
			</div>
			<div id="layout_north_pfMenu" style="width: 120px; display: none;">
				<div onclick="changeTheme('default');">default</div>
				<div onclick="changeTheme('gray');">gray</div>
				<div onclick="changeTheme('black');">black</div>
				<div onclick="changeTheme('bootstrap');">bootstrap</div>
				<div onclick="changeTheme('metro');">metro</div>
			</div>
			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
				<div onclick="editPassword();">修改密码</div>
				<div onclick="showAbout();">联系管理员</div>
				<div class="menu-sep"></div>
				<div onclick="logoutFun();">退出系统</div>
			</div>
		</div>
		<div data-options="region:'west',split:true,title:'菜单导航'" style="width:200px">
			<!-- 加载菜单树 -->
			<ul id="tree" class="ztree"></ul>
		</div>
		<div data-options="region:'center'">
			<div id="tabs" fit="true" class="easyui-tabs" border="false">
				<div title="消息中心" id="subWarp" style="width:100%;height:100%;overflow:hidden">
					<iframe src="home.jsp" style="width:100%;height:100%;border:0"></iframe>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height:50px;padding:10px;">
			<table style="width: 100%;">
				<tbody>
					<tr>
						<td style="width: 400px;">
							<div style="color: #999; font-size: 8pt;">
								BOSv2.0综合物流管理平台 | Powered by <a href="http://www.itcast.cn/">传智播客</a>
							</div>
						</td>
						<td style="width: *;" class="co1"><span id="online" style="background: url(./images/online.png) no-repeat left;padding-left:18px;margin-left:3px;font-size:8pt;color:#005590;">在线人数:1</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!--修改密码窗口-->
		<div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false" maximizable="false" icon="icon-save" style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<table cellpadding=3>
						<tr>
							<td>新密码：</td>
							<td>
								<input id="txtNewPass" type="Password" class="txt01" />
							</td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td>
								<input id="txtRePass" type="Password" class="txt01" />
							</td>
						</tr>
					</table>
				</div>
				<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
					<a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)">确定</a>
					<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
				</div>
			</div>
		</div>

		<div id="mm" class="easyui-menu" style="width:120px;">
			<div data-options="name:'Close'">关闭当前窗口</div>
			<div data-options="name:'CloseOthers'">关闭其它窗口</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-cancel',name:'CloseAll'">关闭全部窗口</div>
		</div>

	</body>

</html>