<%@page import="java.util.List"%>
<%@page import="taskmanager.services.model.Task"%>

<%@ include file="/init.jsp" %>

<%
	List<Task> tasksToDo = (List<Task>) request.getAttribute("tasksToDo");
	List<Task> tasksDone = (List<Task>) request.getAttribute("tasksDone");
%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">

<portlet:actionURL name='createOrUpdateTask' var="createOrUpdateTaskURL" />
<portlet:actionURL name='deleteTask' var="deleteTaskURL" />
<portlet:resourceURL var="resourceUrl" />

<div class="container-fluid">
	<!-- PAGE TITLE -->
	<div class="row">
		<div id="tituloPrincipal" class="col-md-12">
			<h1><liferay-ui:message key="portlet.title"/></h1>
		</div>
	</div>
	<!-- PAGE TITLE END -->
	
	<!-- MAIN DIV -->
	<div class="row">
	
		<!-- LISTS DIV -->
		<div id="itemList" class="col-md-6">
		
			<!-- TODO LIST DIV -->
			<div id="todoList" class="row table-responsive">
				<div id="todoListTitle" class="offset-md-11">
					<button type="button" id="create" class="btn btn-outline-secondary"><liferay-ui:message key="button.create"/></button>
				</div>


				<table id="toDoTable" class="table table-striped table-hover"<%if(tasksToDo.isEmpty()){%> style="display:none" <%}%>>
					<tbody id="bodyToDo">
			<%
				for(Task task: tasksToDo){
			%>
						<tr data-taskid="<%=task.getTaskId() %>">
							<td class="taskTitle"><%=task.getTitle() %></td>
							<td class="taskDescription"><%=task.getDescription() %></td>
							<td class="col-md-1"><input type="checkbox" data-taskid="<%=task.getTaskId() %>" class="form-check-input"></td>
						</tr>
			<%
				}
			%>
					</tbody>
				</table>

				<!-- NO TASKS TO DO -->
				<div id="toDoMessage" class="alert alert-primary" role="alert" <%if(!tasksToDo.isEmpty()){%> style="display:none" <%}%>><liferay-ui:message key="todo.list.empty"/></div>

			</div>
			<!-- TODO LIST DIV END-->
			
			<!-- DONE LIST DIV -->
			<div id="doneList" class="row table-responsive">
				<div id="doneListTitle" class="col-md-12">
					<button type="button" id="verCompletadas" class="btn btn-outline-secondary"><i id="chevron" class="bi bi-chevron-compact-right"></i> Completadas</button>
				</div>
				<div id="collapsable" style="display: none">
				<table id="doneTable" class="table table-success table-hover" <%if(tasksDone.isEmpty()){%> style="display:none" <%}%>>
					<tbody id="bodyDone">
			<%
				for(Task task: tasksDone){
			%>
						<tr data-taskid="<%=task.getTaskId() %>">
							<td class="taskTitle"><%=task.getTitle() %></td>
							<td class="taskDescription"><%=task.getDescription() %></td>
							<td class="col-md-1"><input type="checkbox" data-taskid="<%=task.getTaskId() %>" class="form-check-input" checked></td>
						</tr>
			<%
				}
			%>
					</tbody>
				</table>
				</div>
				<!-- NO TASKS TO DO -->
				<div id="doneMessage" class="alert alert-primary" role="alert"<%if(!tasksDone.isEmpty()){%> style="display:none" <%}%>><liferay-ui:message key="done.list.empty"/></div>

			</div>
			<!-- DONE LIST DIV END-->
			
		</div>
		<!-- LISTS DIV END -->
		
		<!-- CREATION DIV -->
		<div id="edit" class="col-md-6" style="display:none">
			<aui:form action="<%=createOrUpdateTaskURL %>" name="fm">
				<aui:input type="hidden" id="taskId" name="taskId" />
				
				<div class="col-md-12">
					<aui:input id="title" name="title" label="create.title">
						<aui:validator name="required" />
					</aui:input>
				</div>
				
				<div class="col-md-12">
					<aui:input type="textarea" id="description" name="description" label="create.description" />
				</div>
				
				<aui:button-row>
				     <aui:button type="submit" id="saveButton" cssClass="btn btn-outline-secondary" />
				     <aui:button type="submit" id="deleteButton" value="button.delete" cssClass="btn btn-outline-secondary" />
				</aui:button-row>
			</aui:form>
		</div>
		<!-- CREATION DIV END-->
		
	</div>
	<!-- MAIN DIV END -->
</div>

<script type="text/javascript">
	var createOrUpdateTaskURL = '<%=createOrUpdateTaskURL %>';
	var deleteTaskURL = '<%=deleteTaskURL %>';
	var resourceUrl = '<%=resourceUrl %>';
	var nameSpace = '<portlet:namespace />';
	var ajax_error = '<liferay-ui:message key="ajax.error"/>';
</script>