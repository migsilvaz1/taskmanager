/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package taskmanager.services.service.impl;

import com.liferay.portal.aop.AopService;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import taskmanager.services.model.Task;
import taskmanager.services.model.impl.TaskImpl;
import taskmanager.services.service.base.TaskLocalServiceBaseImpl;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>taskmanager.services.service.TaskLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TaskLocalServiceBaseImpl
 */
@Component(property = "model.class.name=taskmanager.services.model.Task", service = AopService.class)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>taskmanager.services.service.TaskLocalService</code> via injection or a
	 * <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>taskmanager.services.service.TaskLocalServiceUtil</code>.
	 */

	public Task createTask() {
		Task task = taskPersistence.create(counterLocalService.increment(TaskImpl.class.getName()));
		task.setCreateDate(new Date());
		return task;
	}

	public List<Task> findByGroupIdCompanyIdUserId(long groupId, long companyId, long userId, int start, int end) {
		return taskPersistence.findByGroupIdCompanyIdUserId(groupId, companyId, userId, start, end);
	}

	public List<Task> findByToDo(long groupId, long companyId, long userId, int start, int end) {
		return taskFinder.findByToDo(groupId, companyId, userId, start, end);
	}
	
	public List<Task> findByDone(long groupId, long companyId, long userId, int start, int end) {
		return taskFinder.findByDone(groupId, companyId, userId, start, end);
	}

	public Task checkDone(long taskId) {
		Task task = taskPersistence.fetchByPrimaryKey(taskId);
		task.setDoneDate(new Date());
		return taskPersistence.update(task);
	}
	
	public Task uncheckDone(long taskId) {
		Task task = taskPersistence.fetchByPrimaryKey(taskId);
		task.setDoneDate(null);
		return taskPersistence.update(task);
	}
}