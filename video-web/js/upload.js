$(function() {
	$("#excelImport").click(function() {
		$('#fileupload').click();
	});
	$('#fileupload').change(function() {
		var uploadFiles = $('#fileupload').prop('files');
			$("#textfield").val(uploadFiles[0].name);
				startUpload(uploadFiles)
	})
});
        $(function() {
			
            var dropZone = document.getElementById('drop-zone');
            var uploadForm = $('#js-upload-form');
            
            uploadForm.on('submit', function(e) {
                var uploadFiles = $('#fileupload').prop('files');
                e.preventDefault()
                startUpload(uploadFiles)
            })

            dropZone.ondrop = function(e) {
                e.preventDefault();
                this.className = 'upload-drop-zone';
                $("#textfield").val('');//清空手动浏览上传文件的input
                startUpload(e.dataTransfer.files)
            }
            dropZone.ondragover = function() {
                this.className = 'upload-drop-zone drop';
                return false;
            }
            dropZone.ondragleave = function() {
                this.className = 'upload-drop-zone';
                return false;
            }
        });

        function startUpload(files) {
            // 上传的文件
            console.log(files)
            $('.js-upload-finished').html(
                '<h3>已上传文件</h3>' +
                '<div class="list-group">' +
                '<a href="#" class="list-group-item list-group-item-success">' +
                '<span class="badge alert-success pull-right">Success</span>' +
                files[0].name + '</a></div>'
            )
            return
        }