<%@page contentType="text/html; charset=utf8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>CTB Research Group</title>
		<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
		 <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script> 
		<script src="/resources/common/js/common.js"></script>
		<script src="/resources/services/user/login/js/login.js"></script>
		<script type="text/javascript">
		
		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-35404210-1']);
		  _gaq.push(['_trackPageview']);
		
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
		
		</script>
		<style type="text/css">
			body {
				padding-top: 60px;
			}
			.footer {
			  padding: 70px 0;
			  margin-top: 70px;
			  border-top: 1px solid #e5e5e5;
			  background-color: #f5f5f5;
			}
			.footer p {
			  margin-bottom: 0;
			  color: #777;
			}
			.footer-links {
			  margin: 10px 0;
			}
			.footer-links li {
			  display: inline;
			  margin-right: 10px;
			}
		</style>
	</head>
	<body>
		<div class="navbar navbar-fixed-top navbar-inverse">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="#">CTB Research Group</a>
					<!--
					<p class="navbar-text pull-right">
						Hi, there.
					</p>
					-->
					<ul class="nav">
						<!--
						<li class="active">
						-->
						<li>
							<a href="/common/web_template.jsp?body_path=/home.jsp">Home</a>
						</li>
						<!--
						<li class="divider-vertical"></li>
						-->
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Pilot Services
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/common/web_template.jsp?body_path=/services/chat/chat_view.jsp">Chat</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/chat/history.do">Chat History</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/utilities/qrcode/text2qrcode.html">Text to QR Code</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/utilities/kakao/index.html">Kakao Talk</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/games/slot/slot-iframe-wrapper.html">Slot Machine</a>
								<li><a href="/common/web_template.jsp?body_path=/services/clock/world_clock.do">World Clock</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/utilities/facebook/like.html">Facebook Like</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/utilities/facebook/like_in_javascript.html">Facebook Like in JavaScript</a></li>
								<li><a href="/common/web_template.jsp?body_path=/services/facebook/friend_list.do">Facebook Friend List</a></li>
							</ul>
						</li>
						<!--
						<li class="divider-vertical"></li>
						-->
						<li><a href="/common/web_template.jsp?body_path=/about_us.do">About Us</a></li>
						<!--
						<li class="divider-vertical"></li>
						-->
						<c:if test="${sessionScope.user.role == 'staff'}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									Admin
									<b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a href="/common/web_template.jsp?body_path=/services/admin/user/list_users_view.do">Users</a></li>
									<li><a href="/common/web_template.jsp?body_path=/services/admin/stat/list_daily_visit_logs.do">Visit Statistics</a></li>
									<li>
										<a href="/common/web_template.jsp?body_path=/services/qrcode/qrcode_history.do">
											QR Code Generation History
										</a>
									</li>
								</ul>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="span3">
					<div class="well">
						<c:choose>
							<c:when test="${sessionScope.user == null}">
								<jsp:include page="/services/user/login/login_window.jsp" />
							</c:when>
							<c:otherwise>
								<jsp:include page="/services/user/login/logout_window.jsp" />
							</c:otherwise>
						</c:choose>
						<p>
						<b>Temporarily for mobile devices</b>
						<ul>
							<li><a href="/common/web_template.jsp?body_path=/services/study/algorithm_contest/show.do">Algorithm Contest</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/games/arkanoid/arkanoid.html">Arkanoid</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/game/score/leaderboard.do?game_sequence=1">Arkanoid Leaderboard</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/games/remember_photos/remember_photos.html">Remember Photos</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/chat/chat_view.jsp">Chat</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/chat/history.do">Chat History</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/utilities/qrcode/text2qrcode.html">Text to QR Code</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/utilities/kakao/index.html">Kakao Talk</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/games/slot/slot-iframe-wrapper.html">Slot Machine</a>
							<li><a href="/common/web_template.jsp?body_path=/services/clock/world_clock.do">World Clock</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/utilities/facebook/like.html">Facebook Like</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/utilities/facebook/like_in_javascript.html">Facebook Like in JavaScript</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/facebook/friend_list.do">Facebook Friend List</a></li>
							<li><a href="/common/web_template.jsp?body_path=/services/games/leaderboard.do">Leaderboard</a></li>
						</ul>
						</p>
					</div>
				</div>
				<div class="span9">
					<!--
					<div class="well">
					-->
						<jsp:include page="${param.body_path}" />
					<!--
					</div>
					-->
				</div>
			</div>
		</div>
		<footer class="footer">
			<div class="container">
				<p>Copyright@ 2012 CTB Research Group</p>
			</div>
		</footer>
	</body>
</html>
