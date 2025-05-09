document.getElementById('registeration-form').addEventListener('submit',function(event){
	event.preventDefault();
	
	var fullName = document.getElementById('fullname').value.trim();
	var username = document.getElementById('username').value.trim();
	var email = document.getElementById('email').value.trim();
	var password = document.getElementById('password').value;
	var mobile = document.getElementById('mobile').value.trim();
	var gender = document.getElementById('gender').value.trim();
	
	fetch('register', {method:'post',headers:{"Content-type":"application/x-www-form-urlencoded"},
	body:"fullName="+fullName+"&username="+username+"&email="+email+"&password="+password+"&mobile="+mobile+"&gender="+gender})
	.then(function(res){
		if(res.status === 200){
			displaySuccessResponse(res)
		}
		else{
			displayErrorResponse(res)
		}
		
	})
})

function displaySuccessResponse(res){
	return res.text().then(function(response){
		location.href=response;
	})
	
}

function displayErrorResponse(res){
	return res.json().then(function(response){
		console.log(response)
		if(response.property=='USERNAME'){
			document.getElementById('username-caution').innerText=response.message;
		}
	})
}