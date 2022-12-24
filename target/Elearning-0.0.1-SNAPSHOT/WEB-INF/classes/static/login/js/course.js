$(document).ready(function() {
	$('#content').ckeditor();
	
	$('#fileUpload').change(function(event) {
		var files = event.target.files;
		var formData = new FormData();
		formData.append("file", files[0]);
		
		var path = $('#path').val();
		console.log(path);
		$.ajax({
			url:  path + 'file/upload',
			data : formData,
            type : "POST",
            enctype: 'multipart/form-data',
            processData: false, 
            contentType:false,
            success : function(data) {
            	$('#image').val(data);
            	$('#imgUpload').attr('src', path + data);
            	$('#imgUpload').removeClass('d-none');
            },
            error : function(err){
            	console.log(err);
            } 
		})
	})
});