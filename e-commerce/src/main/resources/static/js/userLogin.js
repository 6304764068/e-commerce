document.getElementById('login-form').addEventListener('submit',function(event){
	event.preventDefault();
	
	var mobileOrEmail = document.getElementById('mobileOrEmail').value.trim();
	var password = document.getElementById('password').value;
	
	fetch('login',{method:'post',headers:{'content-type':"application/x-www-form-urlencoded"},body:"mobileOrEmail="+mobileOrEmail+"&password="+password})
	.then(function(response){
		if(response.status===200){
			displaySuccessResponse(response)
		}
		else{
			displayErrorResponse(response)
		}
	})
	
})

function displaySuccessResponse(res){
	return res.text().then(function(response){
		location.href=response
	})
	
}

function displayErrorResponse(res){
	return res.json().then(function(response){
		
		document.getElementById('emailOrMobile-caution').innerText='';
		document.getElementById('password-caution').innerText='';
		
		if(response.property=='MOBILE_OR_EMAIL'){
			document.getElementById('emailOrMobile-caution').innerText=response.message;
		}
		else if(response.property=='PASSWORD'){
			document.getElementById('password-caution').innerText=response.message;
		}
	})
}

