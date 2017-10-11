<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello


	<div>
		<table>
			<c:forEach items="${model}" var="model">
				<tr>
					<td><c:out value="${model.name}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	
	
	
	<select>
	    <c:forEach var="item" items="${model}">
	       <option>${item.name}</option>
	    </c:forEach>
    </select>
    
</body>
</html>