$(document).ready(function(e) {
alert("Product Page Is Loaded .. ");
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
					console.log(data);
						arr = data["body"];
		console.log("array is :"+arr);
			arr.forEach(myFunction);
				},
				error: function(
					xhr,
					textStatus,
					errorThrown) {
			console.log(xhr);
				}
			});
		
});

function myFunction(v, i, a) {
	
	console.log(v)
	console.log(i); 
	console.log(a);
	$(".addvaluselectoption")
		.append("<option value='"+v.categoryid+"'>" + v.categoryname + "</option> ");

}






