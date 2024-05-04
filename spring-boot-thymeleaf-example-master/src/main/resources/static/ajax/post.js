
$(document).ready(function() {
	/*========== CALL FOR REGISTRATION  */
	/*-------------------------------  Account create--------------------------*/
	alert("Page Is Loaded..");
/*	$("#registerd")
		.click(
			function() {*/
/*
				var firstname = $("#firstname")
					.val();
				var lastname = $("#lastname")
					.val();
				var email = $("#email").val();
				var mobile = $("#mobile").val();
				var password = $("#password")
					.val();
				var cpassword = $("#cpassword")
					.val();

				var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
				event.preventDefault();
				*/
				/*returnvalue= checkFormField(firstname ,lastname,email,mobile,password);
				if(returnvalue!=""||returnvalue!=null){
					console.log(returnvalue);
				}*/

/*
				$("#validForm").validate();
				$("#validForm").validate({
					messages: {
						email: {
							required: 'Enter this!',

						}




					}
				});

				if (firstname == "" || firstname == null) {
					$("#first").text("please fill Firstname");
					$("#first").addClass("text-danger");
					$("#firstname").addClass("border border-danger");

				}
				else {

					$("#first").text("");
					$("#first").removeClass("text-danger");
					$("#firstname").removeClass("border border-danger");
					$("#firstname").addClass("border border-success");
				}






				if (lastname == "" || lastname == null) {
					$("#lastname").addClass("border border-danger");
					$("#second").text("please fill lastname");
					$("#second").addClass("text-danger");

				}
				else {
					$("#second").text("");
					$("#second").removeClass("text-danger");
					$("#lastname").removeClass("border border-danger");
					$("#lastname").addClass("border border-success");
				}




				if (email == null || email == "") {
					$("#email").addClass("border border-danger");



				}
				else {

					$("#email").removeClass("border border-danger");
					$("#email").addClass("border border-success");
				}
				if (mobile == null || mobile == "") {
					$("#mobile").addClass("border border-danger");
					$("#four").text("please fill mobile");
					$("#four").addClass("text-danger");

				}
				else {
					$("#four").text("");
					$("#four").removeClass("text-danger");
					$("#mobile").removeClass("border border-danger");
					$("#mobile").addClass("border border-success");
				}
				if (password == null || password == "") {
					$("#password").addClass("border border-danger");
					$("#five").text("please fill password");
					$("#five").addClass("text-danger");

				} else {
					$("#five").text("");
					$("#five").removeClass("text-danger");
					$("#password").removeClass("border border-danger");
					$("#password").addClass("border border-success");
				}
				if (cpassword == null || cpassword == "") {
					$("#cpassword").addClass("border border-danger");
					$("#six").text("please fill password");
					$("#six").addClass("text-danger");

				} else {
					$("#six").text("");
					$("#six").removeClass("text-danger");
					$("#cpassword").removeClass("border border-danger");
					$("#cpassword").addClass("border border-success");
				}
				*/
				
				$('form').submit(function(e){
					
				})
				
				$.ajax({
					url: 'http://localhost:8000/api/auth/signup',
					type: 'POST',
					dataType: 'json',
					contentType: "application/json",
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					data: JSON
						.stringify({
							"firstname": "Access",
							"lastname": "Area",
							"email": "access831@gmail.com",
							"mobile": "962871859",
							"password": "access@123"
						}),
					success: function(
						data,
						textStatus,
						xhr) {
						console.log(data);
						if (data["status"] == 200) {
							console
								.log("You Have Logger In ");
						
							
							
						}

					
					},
					error: function(
						xhr,
						textStatus,
						errorThrown) {
							console.log(xhr['responseJSON']);
						if (xhr['responseJSON'].status == 409) {

							FailureMessage(xhr['responseJSON'].message);
						}


					}
				});
				
				
				
		/*	});*/
	/*=====================================  login  =================================*/
	$("#login")
		.click(
			function() {

				var firstname = $("#username")
					.val();
				var lastname = $("#password")
					.val();
				event.preventDefault();
				$.ajax({
					url: '/api/auth/login',
					type: 'POST',
					dataType: 'json',
					contentType: "application/json",
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					data:
						$('#form').serialize(),

					/*	JSON.stringify({*/
					"username": firstname,
					"password": lastname,
					/*}
					),*/
					success: function(
						data,
						textStatus,
						xhr) {



						if (data["status"] == 200) {
							if (data["role"] == "ROLE_USER") {

								window.location.href("/api/auth/admindashboard");

							}

							if (data["role"] == "ROLE_ADMIN") {
								window.location.href("/api/auth/userdashboard");
							}



						}
						console
							.log(data);


					},
					error: function(
						xhr,
						textStatus,
						errorThrown) {
						//console.log(xhr["responseJSON"]);
						if (xhr["responseJSON"].status == "401") {
							//console.log("Something went wrong");
						}

					}
				});
			});


	/*=============== CALL For Product Add================== */
	/*
		$("#AddProductSubmit")
			.submit(function() {
				//	alert("success");
				
				//let formData = new FormData(); 
				alert("As you can see, the link no longer took you to jquery.com");
				event.preventDefault();
				var productname = $("#productname").val();
				var desc = $("#desc").val();
				var file = $("#file").val();
				var categoryname = $("#categoryname").val();
				console.log(productname);
				console.log(desc);
				console.log(file);
				console.log(categoryname);
				//	 formData.append("file", file.files[0]);
				// console.log("file is "+formData);
	
				$.ajax({
					url: 'http://localhost:8004/api/test/add/product',
					type: 'POST',
					dataType: 'json',
					//contentType: "application/json",
					enctype: 'multipart/form-data',
					processData: false,
					cache: false,
					headers: {
						'Accept': 'application/json',
						//'Content-Type':'application/json'
						//'content-Type':'multipart/form-data'
					},
					data: {
						"productname": productname,
						"discription": desc,
						"files": file,
						"catname": categoryname,
					},
					proccess: false,
					'contentType': 'multipart/form-data',
					success: function(data, textStatus, xhr) {
						if (data.status == "500") {
							console.log("current request is not multipart request ::");
						}
						alert(data);
					},
					error: function(
						xhr,
						textStatus,
						errorThrown) {
						alert("error thrown");
						console
							.log(errorThrown);
						console.log(xhr);
					}
				});
				
			});
			*/

	/*======================== for add category===============================*/
	$("#AddCategorySubmit")
		.click(function() {
			event.preventDefault();
			var categoryname = $("#categoryname").val();
			console.log(categoryname);
			$.ajax({
				url: 'api/test/add/category',
				type: 'POST',
				dataType: 'json',
				contentType: "application/json",
				headers: {
					'Accept': 'application/json',
				},
				data: {
					"categoryname": categoryname,
				},
				proccess: false,
				'contentType': 'multipart/form-data',
				success: function(data, textStatus, xhr) {
					if (data.status == "200") {
						//alert("Category Added");
					}
				},
				error: function(
					xhr,
					textStatus,
					errorThrown) {

				}
			});
		});
	/*==================== remove product  from cart =============*/
	$(".removecart").click(function() {

		var valll = $('.valuessid').text();

		//	console.log(valll);
		event.preventDefault();
		$.ajax({
			url: "/api/test/remodeproductfromcart/" + valll,
			type: 'GET',

			success: function(
				data,
				textStatus,
				xhr) {

				if (data["status"] == 200) {
					//console.log(data);

					//successMessage(data['message']);
					successResponseMessage(data['message']);
					/*	$('#message').removeClass("d-none").addClass("d-block");*/
					//alert("Your Product Is Removed From Cart ");
					//successMessage("Your Product Is Removed From Cart")

					//	window.setTimeout(()=>{window.location.href = "/api/test/shoping-cart";}, 5000);



				}
			},
			error: function(
				xhr,
				textStatus,
				errorThrown) {
				alert("error thrown");
				console
					.log(xhr);
			}
		});
	});



	/*================== Add To Cart Details=================== */
	$(".selectedsizevalue").click(function() {


		event.preventDefault();
		/*-- select size----- */
	var href=	$(".sp-current-big").attr('href');
	splits=href.split("/");
		orderimagevalue=splits[3];
		
		var val = $("input[name='size']:checked").val();
		var size = "&&size=" + val;
         var orderimage ="&&orderimage=" + orderimagevalue
       
		/*================ click add to  cart button ===========*/
		$("#cartadd").click(
			function() {
				var href = $('form').attr('action');
				//alert(href);
				var finalurl = href + size + orderimage;
				
				$.ajax({
					url: finalurl,
					type: 'GET',
					/* 	dataType: 'json', */
					success: function(
						data,
						textStatus,
						xhr) {

						console.log(data);
						/*successMessage("Product Add To Cart ..") ;*/
					

						if (data["status"] == 200) {
							//successMessage("Product Is Add To Cart");
								window.location.href = "/api/test/shoping-cart";
								//console.log(data);
									//$('#message').removeClass("d-none").addClass("d-block");
								
								//window.location.href = "api/auth/userdashboard";
			
							}

						/*if (data["status"] == 409) {
					failledMessage("Product Is Exists into Cart");
						//console.log(data);
							//$('#message').removeClass("d-none").addClass("d-block");
						
						//window.location.href = "/api/test/shoping-cart";
	
					}
					*/

						/*
							if (data["status"] == 401) {
						failledMessage("Please Logged In... ");
							//console.log(data);
								//$('#message').removeClass("d-none").addClass("d-block");
							
							
		
						}*/



					},
					error: function(
						xhr,
						textStatus,
						errorThrown) {
						//alert("error thrown");
						console.log(xhr);
						//console.log(xhr['status'])
						//console.log(errorThrown);
						if (xhr['responseJSON'].status == 401) {
							//console.log(xhr['responseJSON'].message);
							window.location.href = "/api/page/LogIn";
						}


						if (xhr['responseJSON'].status == 226) {
							failledMessage("This Product Is Already Add To Cart..");
							window.setTimeout(() => { window.location.href = "/api/page/Product-dtl-usr"; }, 4000);
						}

						/*	if (xhr['status']) {*/

						//	window.setTimeout(() => { window.location.href = "/api/page/Product-dtl-usr"; }, 4000);
						//	window.location.href = "/api/test/shoping-cart";

						/*	}*/

					}
				});




			}

		);



	});

});
/*======================== user readable messages=======*/
function successMessage(msg) {
	Swal.fire({
		position: 'top-end',
		position: 'center',
		icon: 'success',
		title: msg,
		showConfirmButton: false,
		timer: 5000
	})

	return 1;

}
function failledMessage(msg) {
	Swal.fire({
		position: 'top-end',
		position: 'center',
		icon: 'warning',
		title: msg,
		showConfirmButton: false,
		timer: 3000
	})

}

function sendLogInPage() {
	window.location.href = "/api/page/LogIn";
}

function FailureMessage(msg) {
	Swal.fire({
		icon: 'error',
		title: 'Oops...',
		text: msg,
		/* footer: '<a href="">Why do I have this issue?</a>'*/
		showConfirmButton: false,
		timer: 3000
	})
}

function funcabc(id) {
	var vall = $('input[name="flexRadioDefault"]:checked').val();
	alert("selected value is ::" + vall);
	$("#placeorder").click(function() {
		alert("Your Order Is Place Your Address :: ");
		// call ajax 
		$.ajax({
			type: 'GET',
			url: '/api/user/bookproduct?adrid=' + vall,
			success: function(data) {

				if ($.isEmptyObject(data['message'])) {

					$("#mainaddressbody").addClass(
						"d-none");
				} else {

					arr = data["message"];


					//console.log(arr);



				}


			},
			error: function(data) {
				//console.log("error");
				//console.log(data);
			}

		});
		//$('#placeorder').prop('disabled', true)
	});




	/*== check test f==== */

	/*check form field */

	/*function  checkFormField(firstname ,lastname,email,mobile,password){
		
		if(firstname!=null || firstname!=""){
			return "Please Fill FirstName";
			
		}else if(lastname!=null || lastname!=""){
			return "Please Fill FirstName";
	
		}else if(email!=null || email!=""){
			return "Please Fill FirstName";
	
		}else if(mobile!=""||mobile!=null){
			return "Please Fill FirstName";	
		}else if(password!=null||password!=""){
			return "Please Fill FirstName";
	
		}
		
	}*/



}



