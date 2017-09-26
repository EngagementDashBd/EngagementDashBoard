
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accordian - Prototype</title>

</head>
<body>
	<div class="panel-group" id="accordion">
		<c:forEach var="menuItem" items="${menuList}" varStatus="status">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse${status.index}"><span
							class="glyphicon glyphicon-folder-close"> </span>&nbsp;${menuItem.title}</a>
					</h4>
				</div>

				<div id="collapse${status.index}" class="panel-collapse collapse">
					<div class="panel-body">
						<table class="table">
							<c:if test="${!empty menuItem.children}">
								<c:forEach var="childMenu1" items="${menuItem.children}">

									<tr>
										<td><span class="glyphicon glyphicon-pencil text-primary"></span><a
											href="<c:url value="${childMenu1.menuAction }"/>">&nbsp;${childMenu1.title}</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</div>
				</div>
			</div>
		</c:forEach>


	</div>

</body>
</html>