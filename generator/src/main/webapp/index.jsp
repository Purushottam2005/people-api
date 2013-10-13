<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Generator</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

    <script src="<c:url value="/static/js/jQuery.js"/>"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="/static/js/generator.js"/>"></script>
</head>
<body>
<div class="container">
    <div class="form-group">
        <label for="inputUrl">Endpoint</label>
        <input id="inputUrl"
               type="url"
               class="form-control"
               value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/api">
    </div>

    <div class="form-group">
        <label for="method">Endpoint</label>
        <select id="method" class="form-control">
            <option value="GET">GET</option>
            <option value="POST">POST</option>
            <option value="PUT">PUT</option>
            <option value="DELETE">DELETE</option>
        </select>
    </div>

    <ul id="apiNav" class="nav nav-pills nav-justified">
        <li class="back"><a>Back</a></li>
        <li class="refresh"><a>Refresh</a></li>
    </ul>

    <div class="form-group">
        <label for="inputData">Data</label>
        <textarea id="inputData" class="form-control" rows="10">
{
    size: 5,
    sort:"firstName,desc"
}
        </textarea>
    </div>

    <pre id="output"></pre>
</div>
</body>
</html>