
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
			$("#content").append("<div>"+data.content+"</div>");
			
			
		
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
			$("#content").append("<div class='tmsg_text'>"+data.content+"</div>");
		}
		
