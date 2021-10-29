/*
 * Link events
 */
$(document).ready(function() {
	
	$('tr').click(function() {
		if($(this).find('input').prop('checked')){
			$('#' + nameSpace + 'saveButton').prop('disabled', true);
		} else {
			$('#' + nameSpace + 'saveButton').prop('disabled', false);
		}
		var taskId = $(this).data('taskid');
		var title = $(this).find('.taskTitle').text();
		var description = $(this).find('.taskDescription').text();

		$('#' + nameSpace + 'taskId').val(taskId);
		$('#' + nameSpace + 'title').val(title);
		$('#' + nameSpace + 'description').val(description);
		
		$( '#edit' ).show( 'slide' );
	});
	
	$('.form-check-input').click(function(event) {
		var taskId = $(this).data('taskid');
		var operation = $(this).prop('checked') ? 1 : 0;
		taskStateChange(operation, taskId, $(this).parent().parent());
		event.stopPropagation();
	});

	$('#create').click(function() {
		$('#' + nameSpace + 'taskId').val('');
		$('#' + nameSpace + 'title').val('');
		$('#' + nameSpace + 'description').val('');
		$('#' + nameSpace + 'saveButton').prop('disabled', false);
		$( '#edit' ).show( 'slide' );
	});
	
	$('#' + nameSpace + 'saveButton').click(function() {
		$('#' + nameSpace + 'fm').attr('action', createOrUpdateTaskURL);
	});
	
	$('#' + nameSpace + 'deleteButton').click(function() {
		$('#' + nameSpace + 'fm').attr('action', deleteTaskURL);
	});
	
	$('#verCompletadas').click(function() {
		$("#collapsable").slideToggle( "slow" );
		if($('#chevron').hasClass('bi-chevron-compact-right')){
			$('#chevron').removeClass('bi-chevron-compact-right');
			$('#chevron').addClass('bi-chevron-compact-down');
		} else {
			$('#chevron').removeClass('bi-chevron-compact-down');
			$('#chevron').addClass('bi-chevron-compact-right');
		}
	});
});

function taskStateChange(operation, taskId, domElement) {
	console.log("ajax")
	$.ajax({
		url : resourceUrl,
		data : {
			operation : operation,
			taskId: taskId
		},
		type : "POST",
		dataType : "json",
	})
	.done(function(json) {
		console.log(json);
		if(json.status === 'ok'){
			if(operation === 1){
				addDone(domElement);
			} else {
				addToDo(domElement);
			}
		} else {
			alert(ajax_error);
		}
	})
	.fail(function(xhr, status, errorThrown) {
		console.log("Error: " + errorThrown);
		console.log("Status: " + status);
	});
}

function addDone(domElement){
	$('#bodyDone').append(domElement);
	checkTables();
}

function addToDo(domElement){
	$('#bodyToDo').append(domElement);
	checkTables();
}

function checkTables(){
	console.log('checkTables');
	if($('#bodyDone tr').length === 0){
		$('#doneMessage').show();
		$('#doneTable').hide();
		console.log('1');
	} else {
		$('#doneTable').show();
		$('#doneMessage').hide();
		console.log('2');
	}
	
	if($('#bodyToDo tr').length === 0){
		$('#toDoMessage').show();
		$('#toDoTable').hide();
		console.log('3');
	} else {
		$('#toDoTable').show();
		$('#toDoMessage').hide();
		console.log('4');
	}
}