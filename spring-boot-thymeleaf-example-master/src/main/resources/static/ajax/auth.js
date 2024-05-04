$(document).ready(function() {
	$('form').submit(
		function() {

			var username = $("#username")
				.val();
			var password = $("#password")
				.val();
			event.preventDefault();
			$.ajax({
				url: 'http://localhost:8000/api/auth/authuser',
				type: 'POST',
				dataType: 'json',
				contentType: "application/json",
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({ "username": username, "password": password }),
				success: function(
					data,
					textStatus,
					xhr) {
					if (data["statusCode"] == 200) {
			window.localStorage.setItem("token", data["token"]);
		//	window.location.href="admindashboard";
		console.log(data);
					}
				},
				error: function(
					xhr,
					textStatus,
					errorThrown) {
				}
			});
		});


});








