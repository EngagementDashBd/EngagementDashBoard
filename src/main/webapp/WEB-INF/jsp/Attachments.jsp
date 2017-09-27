<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Attachments</title>
<style>
#selectedFiles img {
	max-width: 125px;
	max-height: 125px;
	float: left;
	margin-bottom: 10px;
}
</style>
<c:if test="${fileUpload == 'success'}">
	<script>
		alert("File uploaded successfully !!");
		window.location="redirect.htm?pageName=Attachments";
	</script>
</c:if>
<c:if test="${fileUpload == 'failure'}">
	<script>
		alert("Error uploading files! Please check the file type!");
		window.location="redirect.htm?pageName=Attachments";
	</script>
</c:if>
<script>
	$(document).ready(function() {
		$("#bar").hide();
		$("#progressBar").hide();
		
		$("#uploadFile").validate({
			rules : {
				"fileData" : {
					required : true
				}
			},

			messages : {
				"fileData" : {
					required : 'Please upload file.',

				}
			}

		});

	});
</script>
<script>
	var totalFileLength, totalUploaded, fileCount, filesUploaded;

	function debug(s) {
		var debug = document.getElementById('debug');
		if (debug) {
			debug.innerHTML = debug.innerHTML + '<br/>' + s;
		}
	}

	function onUploadComplete(e) {
		totalUploaded += document.getElementById('file').files[filesUploaded].size;
		filesUploaded++;
		debug('complete ' + filesUploaded + " of " + fileCount);
		debug('totalUploaded: ' + totalUploaded);
		if (filesUploaded < fileCount) {
			uploadNext();
		} else {
			var bar = document.getElementById('bar');
			bar.style.width = '100%';
			bar.innerHTML = '100% complete';
			// alert('Finished uploading file(s)');
		}
	}

	function onFileSelect(e) {
		var files = e.target.files;
		var output = [];
		fileCount = files.length;
		totalFileLength = 0;
		for (var i = 0; i < fileCount; i++) {
			var file = files[i];
			output.push('<div id='+i+'>');

			output.push(file.name, ' (', file.size, ' bytes, ',
					file.lastModifiedDate.toLocaleDateString(), ')');
			output
					.push('<img src="css/images/deleteattachment.png" alt="Delete Attachment" onclick=removeDiv('
							+ i
							+ ');return false; style="width:30px;height:28px;border:0;">');
			output.push('<br/>');
			output.push('<br/>');

			totalFileLength += file.size;

			output.push('</div>');	
		}
		document.getElementById('selectedFiles').innerHTML = output.join('');
		debug('totalFileLength:' + totalFileLength);
	}

	function onUploadProgress(e) {
		if (e.lengthComputable) {
			$("#bar").show();
			$("#progressBar").show();
			var percentComplete = parseInt((e.loaded + totalUploaded) * 100
					/ totalFileLength);
			var bar = document.getElementById('bar');
			bar.style.width = percentComplete + '%';
			bar.innerHTML = percentComplete + ' % complete';
		} else {
			debug('unable to compute');
		}
	}

	function removeDiv(id) {
		$("#" + id).remove();

	}
	function onUploadFailed(e) {
		alert("Error uploading file");
	}

	function uploadNext() {
		var xhr = new XMLHttpRequest();
		var fd = new FormData();
		var file = document.getElementById('file').files[filesUploaded];
		fd.append("multipartFile", file);
		xhr.upload.addEventListener("progress", onUploadProgress, false);
		xhr.addEventListener("load", onUploadComplete, false);
		xhr.addEventListener("error", onUploadFailed, false);
		xhr.open("POST", "save-product");
		debug('uploading ' + file.name);
		xhr.send(fd);
	}

	function startUpload() {
		totalUploaded = filesUploaded = 0;
		uploadNext();
	}

	window.onload = function() {
		document.getElementById('file').addEventListener('change',
				onFileSelect, false);
		document.getElementById('uploadButton').addEventListener('click',
				startUpload, false);
	}
</script>
</head>

<body>
	<div style="width: 55%">
		<center>
			<h3>
				<b>UPLOAD FILES</b>
				<p style="color:red;">Do not upload executable files</p>
			</h3>
		</center>
		<div id='progressBar'
			style='height: 20px; border: 2px solid grey; margin-bottom: 10px'>
			<div id='bar' style='height: 100%; background: #33dd33; width: 0%'>
			</div>
		</div>

		<div style="float: left; width: 80%;">
			<form action="storeAttachments.htm" method="post"
				enctype="multipart/form-data" id="uploadFile">

				<br> <br> <input type="file" id="file" name="fileData"
					multiple required>
				<output id="selectedFiles"></output>
				<input class="btn btn-info dropdown-toggle" id="uploadButton"
					value="Upload" type="submit" value="upload">
				
				<br> <br>


			</form>
		</div>
	</div>
</body>
</html>
