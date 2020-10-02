$(document).ready(function() {
	$(".deleteButton").on('click',function(e) {
		if (confirm("確定刪除此則留言?")) {
			return true;
		} else {
			return false;
		}
	});
})