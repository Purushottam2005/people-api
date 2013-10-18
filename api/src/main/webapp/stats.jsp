<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="statistics" type="org.hibernate.stat.Statistics"--%>
<h1>Hibernate statistics</h1>
<table style="width: 100%">
    <thead>

    </thead>
    <tbody>
    <tr>
        <td>Connection count</td>
        <td>${statistics.connectCount}</td>
    </tr>
    <tr>
        <td>Sessions opened</td>
        <td>${statistics.sessionOpenCount}</td>
    </tr>
    <tr>
        <td>Sessions closed</td>
        <td>${statistics.sessionCloseCount}</td>
    </tr>
    <tr>
        <td>Start time</td>
        <td><spring:eval expression="new java.util.Date(statistics.startTime)"/></td>
    </tr>
    <tr>
        <td>Transaction count</td>
        <td>${statistics.transactionCount}</td>
    </tr>
    <tr>
        <td>Successful transactions</td>
        <td>${statistics.successfulTransactionCount}</td>
    </tr>
    <tr>
        <td>Flush count</td>
        <td>${statistics.flushCount}</td>
    </tr>
    <tr>
        <td>Query execution max time</td>
        <td>${statistics.queryExecutionMaxTime}</td>
    </tr>
    <tr>
        <td>Query execution max time string</td>
        <td>${statistics.queryExecutionMaxTimeQueryString}</td>
    </tr>
    <tr>
        <td>Prepared statement count</td>
        <td>${statistics.prepareStatementCount}</td>
    </tr>
    <tr>
        <td>Second level cache hit count</td>
        <td>${statistics.secondLevelCacheHitCount}</td>
    </tr>
    <tr>
        <td>Second level cache miss count</td>
        <td>${statistics.secondLevelCacheMissCount}</td>
    </tr>
    <tr>
        <td>Second level cache put count</td>
        <td>${statistics.secondLevelCachePutCount}</td>
    </tr>

    </tbody>
</table>
