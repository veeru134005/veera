
<p class="lead">Shop Name</p>




<div class="list-group">

		<c:forEach  items="${catagories}" var="ca">

			<a href="${context}/show/category/${ca.id}/products" class="list-group-item" id="a_${ca.name}">${ca.name}</a>

		</c:forEach>

	</div>
</div>


<%-- ${context}/show/category/${ca.id}/products --%>