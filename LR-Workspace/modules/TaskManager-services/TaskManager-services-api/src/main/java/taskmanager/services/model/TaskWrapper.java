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

package taskmanager.services.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Task}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Task
 * @generated
 */
public class TaskWrapper
	extends BaseModelWrapper<Task> implements ModelWrapper<Task>, Task {

	public TaskWrapper(Task task) {
		super(task);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("taskId", getTaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("createDate", getCreateDate());
		attributes.put("doneDate", getDoneDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long taskId = (Long)attributes.get("taskId");

		if (taskId != null) {
			setTaskId(taskId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date doneDate = (Date)attributes.get("doneDate");

		if (doneDate != null) {
			setDoneDate(doneDate);
		}
	}

	/**
	 * Returns the company ID of this task.
	 *
	 * @return the company ID of this task
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this task.
	 *
	 * @return the create date of this task
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this task.
	 *
	 * @return the description of this task
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the done date of this task.
	 *
	 * @return the done date of this task
	 */
	@Override
	public Date getDoneDate() {
		return model.getDoneDate();
	}

	/**
	 * Returns the group ID of this task.
	 *
	 * @return the group ID of this task
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this task.
	 *
	 * @return the primary key of this task
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the task ID of this task.
	 *
	 * @return the task ID of this task
	 */
	@Override
	public long getTaskId() {
		return model.getTaskId();
	}

	/**
	 * Returns the title of this task.
	 *
	 * @return the title of this task
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the user ID of this task.
	 *
	 * @return the user ID of this task
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this task.
	 *
	 * @return the user uuid of this task
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this task.
	 *
	 * @return the uuid of this task
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this task.
	 *
	 * @param companyId the company ID of this task
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this task.
	 *
	 * @param createDate the create date of this task
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this task.
	 *
	 * @param description the description of this task
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the done date of this task.
	 *
	 * @param doneDate the done date of this task
	 */
	@Override
	public void setDoneDate(Date doneDate) {
		model.setDoneDate(doneDate);
	}

	/**
	 * Sets the group ID of this task.
	 *
	 * @param groupId the group ID of this task
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this task.
	 *
	 * @param primaryKey the primary key of this task
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the task ID of this task.
	 *
	 * @param taskId the task ID of this task
	 */
	@Override
	public void setTaskId(long taskId) {
		model.setTaskId(taskId);
	}

	/**
	 * Sets the title of this task.
	 *
	 * @param title the title of this task
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the user ID of this task.
	 *
	 * @param userId the user ID of this task
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this task.
	 *
	 * @param userUuid the user uuid of this task
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this task.
	 *
	 * @param uuid the uuid of this task
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected TaskWrapper wrap(Task task) {
		return new TaskWrapper(task);
	}

}