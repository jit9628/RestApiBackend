$(document).ready(function(e) {
	$('form').submit(
		function() {
			var value = $("#floatingInput")
				.val();
				alert(value);
			
			$.ajax({
				url: 'http://localhost:8000/api/category/saved',
				type: 'POST',
				dataType: 'json',
				contentType: "application/json",
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({ "categoryname": value }),
				success: function(
					data,
					textStatus,
					xhr) {
					console.log(data)
					alert("SUCCESS");

				},
				error: function(
					xhr,
					textStatus,
					errorThrown) {
					console.log(xhr);
					alert("FAILS");
				}
			});
		});


});








