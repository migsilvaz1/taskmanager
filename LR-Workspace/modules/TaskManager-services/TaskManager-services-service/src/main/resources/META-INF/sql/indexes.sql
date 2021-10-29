create index IX_EDBAD8A9 on TM_Task (groupId, companyId, userId);
create index IX_FB9AD865 on TM_Task (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6A583427 on TM_Task (uuid_[$COLUMN_LENGTH:75$], groupId);