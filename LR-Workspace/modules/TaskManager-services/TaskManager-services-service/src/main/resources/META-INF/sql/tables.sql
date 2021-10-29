create table TM_Task (
	uuid_ VARCHAR(75) null,
	taskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	title VARCHAR(250) null,
	description VARCHAR(2000) null,
	createDate DATE null,
	doneDate DATE null
);