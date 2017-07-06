<%@page import="java.util.List"%>
<%@page import="com.cqust.chat.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    public String isActive(boolean bool){
			return bool?"在线":"离线";
		}    
    %>
    <%
    	String path = request.getServerName() + ":" + request.getServerPort()  +request.getContextPath();
   		Student stu = (Student)request.getSession(false).getAttribute("stu");
   		Long id = stu.getId();
   		String bPath = request.getContextPath();
   		
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" href="<%=bPath%>/css/talk.css">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

<body style="background: url('<%=bPath%>/image/back.gif');">

	<div class="left">
		<a class="btn btn-info" onclick="refresh()">刷新列表</a>
		<br/>
		<table class="table table-bordered" >
		<tbody id="stulist">
				<%
					List<Student> stus = (List<Student>)request.getSession(false).getAttribute("stus");
					for(int i=0;i<stus.size();i++){
						Student istu = stus.get(i);
				%>
				<tr>
				<th>
					<%
					if(istu.isActive()){
					%>
					<label ><%=istu.getName()+"+"+istu.getGrade()+"<Lable style='color:#CE000D;'>["+isActive(istu.isActive()) +"]<Lable/>"%></label>
					<%
					}else{
					%>
					<label ><%=istu.getName()+"+"+istu.getGrade()+"<Lable>["+isActive(istu.isActive()) +"]<Lable/>"%></label>
					<%
					}
					%>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<th>
					<label>
					<label style="display:none;"><%=istu.getName()%></label>
					<label style="display:none;"><%=istu.getId()%></label>
					<button class="btn btn-info sendTo">发送消息</button>
					</label>
				</th>
					
				</tr>
				<%} %>
				</tbody>
		</table>
	</div>

	<div id="talkBlock">
		<h3>欢迎：${sessionScope.stu.name }</h3>
		<label id="contactUser"></label>
		<label id="to" style="display: none;">1</label>
		<div id="content"></div>
		<br/>
		<input type="text" placeholder="请输入要发送的信息" id="msg" class="msg" >
		<input type="button" value="发送" class="send btn btn-info" onclick="sendMsg()" >
		<input type="button" value="清空" class="clear btn btn-info" onclick="clearAll()">
	</div>

</body>
<script type="text/javascript">
		var path = "<%=path%>"
		var id = "<%=id%>";
		var websocket;
		if ('WebSocket' in window) {
			console.log("ws://" + path + "//ws?id="+id);
			websocket = new WebSocket("ws://" + path + "//ws?id="+id);
			
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://" + path + "/ws"+uid);
		} else {
			websocket = new SockJS("http://" + path + "/ws/sockjs"+uid);
		}
		websocket.onopen = function(event) {
			console.log("WebSocket:已连接");
			console.log(event);
		};
		websocket.onmessage = function(event) {
			/* var data=JSON.parse(event.data);
			console.log("WebSocket:收到一条消息",data);
			var textCss=data.from==-1?"sfmsg_text":"fmsg_text";
			$("#content").append("<div class='fmsg'><label class='name'>"+data.fromName+"&nbsp;"+data.date+"</label><div class='"+textCss+"'>"+data.text+"</div></div>");
			scrollToBottom(); */
			console.log(event.data);
			var data =JSON.parse(event.data);
			document.getElementById("to").innerHTML = data.from;
			$("#contactUser").html("正在与<<"+data.fromName+">>聊天");
			$("#content").append("<div class='fmsg'><label class='name'>"+data.fromName+"&nbsp;"+data.date+"</label><div class=recive>"+data.content+"</div></div>");
			scrollToBottom();
		};
		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		}
		
		function sendMsg(){
			var msg = $("#msg").val();
			var data = {};
			data["from"] = <%=stu.getId()%>;
			data["fromName"] = "<%=stu.getName()%>";
			data["to"] = parseInt($("#to").text());
			data["content"] = msg;
			 
			websocket.send(JSON.stringify(data));
			$("#content").append("<div class='fmsg'><label class='name'>"+
					data.fromName+"&nbsp;"+new Date().Format("yyyy-MM-dd hh:mm:ss")+
					"</label><div class=msg>"+data.content+"</div></div>");
			scrollToBottom();
			$("#msg").val("");
		}
		//设置发送消息的监听事件
		$(".sendTo").bind("click",function(){
			console.log($(this).prev().text());
			$("#to").html($(this).prev().text());
			$("#contactUser").html("正在与<<"+$(this).prev().prev().text()+">>聊天")
		});

		
		
		Date.prototype.Format = function (fmt) { //author: meizz 
		    var o = {
		        "M+": this.getMonth() + 1, //月份 
		        "d+": this.getDate(), //日 
		        "h+": this.getHours(), //小时 
		        "m+": this.getMinutes(), //分 
		        "s+": this.getSeconds(), //秒 
		        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		        "S": this.getMilliseconds() //毫秒 
		    };
		    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		    return fmt;
		}
		
		function clearAll(){
			$("#content").empty();
		}
		
		function refresh(){
			var url = "<%=bPath%>/msg/refreshList";
			$.get(url,function(data,textStatus){
				console.log(data);
				$("#stulist").empty();
				$.each(data,function(i,obj){
					console.log(obj.name);	
					$("#stulist").append(
					"<tr>"+
					"<th><label>"+obj.name+"+"+obj.grade+"</label>"
					+switchActive(obj.active)+"</th>&nbsp;&nbsp;&nbsp;&nbsp;"
					+'<th><label>'+
					'<label style="display:none;">'+obj.name+'</label>'+
					'<label style="display:none;">'+obj.id+'</label>'+
					'<button class="btn btn-info sendTo">发送消息</button>'+
					'</label></th>'+
					'</tr>');
				});
				$(".sendTo").bind("click",function(){
					console.log($(this).prev().text());
					$("#to").html($(this).prev().text());
					$("#contactUser").html("正在与<<"+$(this).prev().prev().text()+">>聊天")
				});
			});
		}
		
		function switchActive(bool){
			if(bool){
				return "<label style='color:#CE000D;'>[在线]</label>";
			}else{
				return "<label>[离线]</label>";
			}
		}
		
		function scrollToBottom(){
			var div = document.getElementById('content');
			div.scrollTop = div.scrollHeight;
		}
	</script>
</html>