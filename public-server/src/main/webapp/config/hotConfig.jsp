<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sunshine.publicserver.utils.HotDeployUtil" %>
<%
    String configName = request.getParameter("name");
    String configVal = request.getParameter("updatedVal");
    HotDeployUtil.refresh(configName, configVal);
%>

<html>
<head>
    <title>添加监控</title>
    <style>
        .body {
            margin: 15px 20px 15px 20px;
        }
    </style>
</head>
<body>
<div class="body">
    <h3>刷出监控</h3>
    <div>
        <form id="form" action="">
            <input type="text" name="name" /><span>例如代码中为QMonitor.recordeOne("tts test")。则输入tts test</span><br />
            <input type="submit" value="添加" />
            <br />
        </form>
    </div>
</div>
</body>
</html>
