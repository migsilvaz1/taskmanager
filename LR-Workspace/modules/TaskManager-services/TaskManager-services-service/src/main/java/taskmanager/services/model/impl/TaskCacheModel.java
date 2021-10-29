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

package taskmanager.services.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import taskmanager.services.model.Task;

/**
 * The cache model class for representing Task in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TaskCacheModel implements CacheModel<Task>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TaskCacheModel)) {
			return false;
		}

		TaskCacheModel taskCacheModel = (TaskCacheModel)object;

		if (taskId == taskCacheModel.taskId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, taskId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", taskId=");
		sb.append(taskId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", doneDate=");
		sb.append(doneDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Task toEntityModel() {
		TaskImpl taskImpl = new TaskImpl();

		if (uuid == null) {
			taskImpl.setUuid("");
		}
		else {
			taskImpl.setUuid(uuid);
		}

		taskImpl.setTaskId(taskId);
		taskImpl.setGroupId(groupId);
		taskImpl.setCompanyId(companyId);
		taskImpl.setUserId(userId);

		if (title == null) {
			taskImpl.setTitle("");
		}
		else {
			taskImpl.setTitle(title);
		}

		if (description == null) {
			taskImpl.setDescription("");
		}
		else {
			taskImpl.setDescription(description);
		}

		if (createDate == Long.MIN_VALUE) {
			taskImpl.setCreateDate(null);
		}
		else {
			taskImpl.setCreateDate(new Date(createDate));
		}

		if (doneDate == Long.MIN_VALUE) {
			taskImpl.setDoneDate(null);
		}
		else {
			taskImpl.setDoneDate(new Date(doneDate));
		}

		taskImpl.resetOriginalValues();

		return taskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		taskId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		createDate = objectInput.readLong();
		doneDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(taskId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(doneDate);
	}

	public String uuid;
	public long taskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String title;
	public String description;
	public long createDate;
	public long doneDate;

}