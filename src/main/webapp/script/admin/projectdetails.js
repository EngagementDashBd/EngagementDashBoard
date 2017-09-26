$(document).ready(function() {
	var validator = $("#addproject").validate({
	//	errorLabelContainer:$("#validationErrors"),
		
		rules: {
			"projectName": {
				//required: true,
				
				maxlength: 10,
			}
		},
		messages:{
			"projectName": {
				required: 'Project Name is required.',
				//minlength: 'Project Name should be minimum 3 characters.',
				maxlength: 'Project Name should be maximum 10 characters.'
			}
		}
	});

});