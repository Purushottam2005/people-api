<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="statistics" type="org.hibernate.stat.Statistics"--%>
<h1>Hibernate statistics</h1>
<table style="width: 100%">
    <thead>
    <th style="width: 20%; text-align: left">
        Property
    </th>
    <th style="text-align: left">
        Value
    </th>
    </thead>
    <tbody>
    <tr>
        <td>Start time</td>
        <td><spring:eval expression="new java.util.Date(statistics.startTime)"/></td>
    </tr>
    <tr>
        <td>Connection count</td>
        <td>${statistics.connectCount}</td>
    </tr>
    <tr>
        <td>Sessions closed/opened</td>
        <td>${statistics.sessionCloseCount}/${statistics.sessionOpenCount}</td>
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
        <td>${statistics.queryExecutionMaxTime} ms @ ${statistics.queryExecutionMaxTimeQueryString}</td>
    </tr>
    <tr>
        <td>Prepared statement count</td>
        <td>${statistics.prepareStatementCount}</td>
    </tr>
    <tr>
        <td>Second level cache hit percent</td>
        <td>${100 * statistics.secondLevelCacheHitCount / (statistics.secondLevelCacheHitCount + statistics.secondLevelCacheMissCount)}
            %
        </td>
    </tr>
    <tr>
        <td>Second level cache hit/miss/put count</td>
        <td>${statistics.secondLevelCacheHitCount}/${statistics.secondLevelCacheMissCount}/${statistics.secondLevelCachePutCount}</td>
    </tr>
    <%--@elvariable id="cacheList" type="java.util.List<net.sf.ehcache.Cache>"--%>
    <c:forEach items="${cacheList}" var="cache">
        <tr>
            <td>${cache.name}</td>

            <td>
                ${fn:length(cache.keys)} entries (<c:forEach items="${cache.keys}" var="key">${key} </c:forEach>)
            </td>
        </tr>

    </c:forEach>

    </tbody>
</table>