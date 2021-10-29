package taskmanager.portlet.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import taskmanager.portlet.constants.TaskManagerConstants;
import taskmanager.portlet.constants.TaskManagerPortletKeys;
import taskmanager.services.model.Task;
import taskmanager.services.service.TaskLocalService;

/**
 * @author miguel.silva
 */
@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/libs/jquery-3.6.0.js",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.display-name=TaskManager",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGER,
		"javax.portlet.supported-locale=en",
        "javax.portlet.supported-locale=es",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TaskManagerPortlet extends MVCPortlet {

	/**
	 * Logger
	 */
	private static Log _log = LogFactoryUtil.getLog(TaskManagerPortlet.class);

	/**
	 * Services
	 */
	@Reference
	private volatile TaskLocalService _taskLocalService;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();

		List<Task> tasksToDo = getTaskLocalService().findByToDo(TaskManagerUtil.getGroupId(renderRequest),
				TaskManagerUtil.getCompanyId(renderRequest), user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		List<Task> tasksDone = getTaskLocalService().findByDone(TaskManagerUtil.getGroupId(renderRequest),
				TaskManagerUtil.getCompanyId(renderRequest), user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute("tasksToDo", tasksToDo);
		renderRequest.setAttribute("tasksDone", tasksDone);
		super.render(renderRequest, renderResponse);
	}

	public void createOrUpdateTask(ActionRequest actionRequest, ActionResponse actionResponse) {
		long taskId = ParamUtil.getLong(actionRequest, "taskId", TaskManagerConstants.NO_VALUE_LONG);
		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");

		if (title != null && !title.isEmpty()) {
			Task task;
			try {
				if (taskId == TaskManagerConstants.NO_VALUE_LONG) {
					task = getTaskLocalService().createTask();
					task.setCompanyId(TaskManagerUtil.getCompanyId(actionRequest));
					task.setGroupId(TaskManagerUtil.getGroupId(actionRequest));
					task.setUserId(TaskManagerUtil.getUserId(actionRequest));
				} else {
					task = getTaskLocalService().getTask(taskId);
				}

				task.setTitle(title);
				task.setDescription(description);
				getTaskLocalService().updateTask(task);
			} catch (PortalException e) {
				_log.error(e, e);
				SessionErrors.add(actionRequest, "update-error");
			}

		} else {
			SessionErrors.add(actionRequest, "title-empty");
		}
	}

	public void deleteTask(ActionRequest actionRequest, ActionResponse actionResponse) {
		long taskId = ParamUtil.getLong(actionRequest, "taskId", TaskManagerConstants.NO_VALUE_LONG);
		if (taskId != TaskManagerConstants.NO_VALUE_LONG) {
			try {
				getTaskLocalService().deleteTask(taskId);
			} catch (PortalException e) {
				_log.error(e, e);
				SessionErrors.add(actionRequest, "delete-error");
			}
		} else {
			SessionErrors.add(actionRequest, "delete-error");
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String operationString = resourceRequest.getParameter("operation");
		String taskIdString = resourceRequest.getParameter("taskId");
		
		int operation = (operationString != null && !operationString.isEmpty()) ? Integer.parseInt(operationString) : TaskManagerConstants.NO_VALUE_INT;
		long taskId = (taskIdString != null && !taskIdString.isEmpty()) ? Long.parseLong(taskIdString) : TaskManagerConstants.NO_VALUE_LONG;
		
		PrintWriter writer = resourceResponse.getWriter();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		
		if (taskId != TaskManagerConstants.NO_VALUE_LONG) {
			
			switch (operation) {
			case TaskManagerConstants.OPERATION_UNDO_TASK:
				getTaskLocalService().uncheckDone(taskId);
				response.put(TaskManagerConstants.JSON_RESPONSE_STATUS, TaskManagerConstants.JSON_RESPONSE_OK);
				break;

			case TaskManagerConstants.OPERATION_DO_TASK:
				getTaskLocalService().checkDone(taskId);
				response.put(TaskManagerConstants.JSON_RESPONSE_STATUS, TaskManagerConstants.JSON_RESPONSE_OK);
				break;
				
			default:
				response.put(TaskManagerConstants.JSON_RESPONSE_STATUS, TaskManagerConstants.JSON_RESPONSE_KO);
				break;
			}
		} else {
			response.put(TaskManagerConstants.JSON_RESPONSE_STATUS, TaskManagerConstants.JSON_RESPONSE_KO);
		}
		writer.print(response);
		super.serveResource(resourceRequest, resourceResponse);
	}

	public TaskLocalService getTaskLocalService() {
		return _taskLocalService;
	}

}