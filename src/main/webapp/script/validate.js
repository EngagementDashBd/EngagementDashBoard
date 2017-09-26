function formValidation()
{
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;
	if(password == confirmPassword)
		{
		return true;
		}
	else 
		{
		alert("Passwords Mismatch");
		document.getElementById('password').value = "";
		document.getElementById('confirmPassword').value = "";
		return false;
		}
}