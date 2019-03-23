/* {
	
	// for adding a loader
	$(window).load(function(){
		setTimeout(function() {
			$(".se-pre-con").fadeOut("slow");
		}, 500);			
	});	
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
 */

// solving the active menu problem
$(function() {
	switch (menu) {

	case 'About':
		$('#about').addClass('active');
		break;
	case 'contact':
		$('#contact').addClass('active');
		break;
	case 'All the products':
		$('#listProducts').addClass('active');
		break;
	case 'Product Management':
		$('#manageProduct').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;
	default:
		if (menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		console.log('#a_'+menu);
		break;
	}

	/*
	 * var products = [ [ '1', 'veera' ], [ '2', 'Hari' ], [ '3', 'Ramu' ], [
	 * '4', 'Krishna' ], [ '5', 'RamaKrishna' ], ];
	 */
	
	// DATA TABLE CODE

	var $table = $('#productListTable');

	if ($table.length) {
		// console.log("hello veeru!");

		var jsonUrl = '';
		if (window.categoryId == '') {

			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}
		$table.DataTable({

			lengthMenu : [ [ 3, 5, 10, -1 ],
					[ '3Records', '5Records', '10 Records', 'All' ] ],
			pageLength : 5,

			ajax : {

				url : jsonUrl,
				dataSrc : ''

			},
			columns : [
				
				{
					data:'code',
					bSortable : false,
					mRender(data,type,row)	{
						return '<img src="'+window.contextRoot+'/resoureces/images/'+data+'.jpg" class="dataTableImg"/>';
	
					}
				},

			{
				data : 'name'

			},

			{
				data : 'brand'
			},

			{
				data : 'unitPrice',
				mRender:function(data,type,row){
					return '&#8377; '+data
				}
			},

			{
				data : 'quantity',
					mRender:function(data,type,row){
						
						if(data <1){
							return '<span style="color:red">out of Stock<span>';            
						}
						
					return data ;
				}	
			} ,
			
			{
				data :'id',
				bSortable:false,
				mRender:function(data,type,row){
					
					
					var str='';
					
					str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span><a/> &#160';
					
					if(row.quantity <1){
						
						str+='<a href="javascript:void(0)" class="btn btn-success disabled" ><span class="glyphicon glyphicon-shopping-cart"></span</a>';	
					}
					else{
						str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span</a>';
					}
					
					
					return str;
					
				}
				
			}
			
			
			]

		});

	}
	
	/* Dis miss the code after the 3s */
	
var $alert=$('.alert');

	if($alert.length){
		
		setTimeout(function(){
			$alert.fadeOut("slow");
		},3000)
		
	}


/*
 * Acivation of the dialog box for acive & deactive
 * 
 */
/*
$('.switch input[type="checkbox"]').on('change',function(){
	
	var checkbox=$(this);
	var checked=checkbox.prop('checked');
	var dMsg=(checked)? 'You want to Activate the product ?':
						'You Want to deactivate the product ?'
	
	var value=checkbox.prop('value');
	bootbox.confirm({
		
		size:'medium',
		title:'Product Activation & Deactivate the product?',
		message:dMsg,
		callback:function(conf){
			
			if(conf){
				console.log(value);
				bootbox.alert({
							size:'medium',
					title:'Information',
					message:'You are going to perform Operation on Product' +value
					
				});	
				
			}
			
			else{
				
				checkbox.prop('checked',!checked);
			}
			
			
		}
		
		
		
	});
});
	*/
	/* DATA TABLE FOR ADMIN */
	
	
	var $adminProductsTable = $('#adminProductsTable');

	if ($adminProductsTable.length) {
		// console.log("hello veeru!");

		var jsonUrl=window.contextRoot +'/json/data/admin/all/products';
	
		$adminProductsTable.DataTable({

			lengthMenu : [ [ 30, 50, 10, -1],
					[ '30Records', '50Records', '10 Records', 'All' ] ],
			pageLength :30,

			ajax : {

				url : jsonUrl,
				dataSrc : ''

			},
			columns : [
		{
			data:'id'
			
		},
		
	    {
					data:'code',
					bSortable : false,
					mRender:function(data,type,row)	{
						return '<img src="'+window.contextRoot+'/resoureces/images/'+data+'.jpg" class="adminDataTableImg"/>';
	
					}
				},

			{
				data : 'name'

			},

			{
				data : 'brand'
			},

		

			{
				data : 'quantity',
					mRender:function(data,type,row){
						
						if(data <1){
							return '<span style="color:red">out of Stock<span>';            
						}
						
					return data ;
				}	
			} ,
			
			{
				data : 'unitPrice',
				mRender:function(data,type,row){
					return '&#8377; '+data
				}
			},
			
			{
				data :'active',
				bSortable:false,
				mRender:function(data,type,row){
					
					var str='';
					str+='<label class="switch">';
					
					if(data){
					
						str+='<input type="checkbox" checked="checked" value="'+row.id+'">';
						
					}
					else{
						
						str+='<input type="checkbox" value="'+row.id+'">';
						
					}
					str+='<div class="slider"></div><label>';
					
					return str;
						
				}

			},
			{
				data:'id',
				bSortable:false,
			mRender:function(data,type,row){
				
				var str='';
				str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
			
				return str;
			}
			
			
				
			}
			
			
			],
			
			
			initComplete:function(){
				var api=this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					
					var checkbox=$(this);
					var checked=checkbox.prop('checked');
					var dMsg=(checked)? 'You want to Activate the product ?':
										'You Want to deactivate the product ?'
					
					var value=checkbox.prop('value');
					bootbox.confirm({
						
						size:'medium',
						title:'Product Activation & Deactivate the product?',
						message:dMsg,
						callback:function(conf){
							
							if(conf){
								console.log(value);
								
							var activationUrl=window.contextRoot+'/manage/product/'+value+'/activation';
								
						$.post(activationUrl,function(data){
						
							bootbox.alert({
								size:'medium',
								title:'Information',
								message:data
							});	
							
						});	
	
							}
							
							else{
								
								checkbox.prop('checked',!checked);
							}
							
							
						}
						
						
						
					});
				});
				
			}

		});
	}
		
		});
	