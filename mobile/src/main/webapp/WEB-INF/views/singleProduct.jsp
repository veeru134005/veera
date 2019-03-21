<div class="container">

	<!-- BreadCrumb -->

	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="${context}/home">Home</a>
				<li><a href="${context}/show/all/products">Prouducts</a>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	</div>

	<div class="row">

		<div class="col-xs-12 col-sm-4">

			<div class="thumbnail">

				<img src="${images}/${product.code}.jpg" class="img img-responsive" />

			</div>
		</div>

		<!-- product description -->
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name}</h3>
			<hr />

			<p>${product.description}</p>

			<hr />

			<h4>
				price:<strong> &#8377; ${product.unitPrice} /-</strong>
			</h4>
			<hr />

			

	<c:choose>
	
		<c:when test="${product.quantity < 4}">
			<h6>Qty.Available:<span style="color:red">out of stock</span></h6>
			
			<a href="javascript:void(0)" class="btn btn-success disabled"><strike> <span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</strike></a>
		</c:when>
	
	<c:otherwise>
	
		<h6>Qty.Available:${product.quantity}</h6>
		<a href="${context}/cart/add/${product.id}/product" class="btn btn-success"> <span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</a>
	
	</c:otherwise>
	</c:choose>

<%-- <a href="${context}/cart/add/${product.id}/product" class="btn btn-success"> <span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</a> --%>
				
				 <a href="${context}/show/all/products" class="btn btn-primary">Back</a>

				
		</div>


	</div>



</div>
