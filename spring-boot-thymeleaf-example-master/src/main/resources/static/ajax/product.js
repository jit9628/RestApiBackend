$(document).ready(function(e) {
	//==== category is load page loading time 
 
	$.ajax({
		url: 'http://localhost:8000/api/category/list/active',
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		success: function(
			data,
			textStatus,
			xhr) {
			arr = data["body"];
			arr.forEach(myFunction);
		},
		error: function(
			xhr,
			textStatus,
			errorThrown) {
			console.log(xhr);
		}
	});



	//========= submittion product ==============

	$('form').submit(function(e) {
		var productname = $('.productname').val();
		var stock = $('.stock').val();
		var category = $('.category').val();
		var serialnumber = $('.serialnumber').val();
		var size = $('.size').val();
		// ajax call start
		$.ajax({
			url: 'http://localhost:8000/api/product/saved',
			type: 'POST',
			dataType: 'json',
			contentType: "application/json",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			data: JSON.stringify({
				"productname": productname,
				"stocks": stock,
				"serialnumber": serialnumber,
				"categoryid": category,
				"size": size,	
				"price":"800"
			}),
			success: function(
				data,
				textStatus,
				xhr) {
				alert("SUCCESS");
			},
			error: function(
				xhr,
				textStatus,
				errorThrown) {
				alert("FAILLED");
			}
		});




		//ajax calll end 
	});

});
function myFunction(v, i, a) {
	$(".addvaluselectoption")
		.append("<option value='" + v.categoryid + "'>" + v.categoryname + "</option> ");
}






