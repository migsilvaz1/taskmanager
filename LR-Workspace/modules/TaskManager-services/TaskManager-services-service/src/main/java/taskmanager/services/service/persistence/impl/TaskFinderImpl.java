package taskmanager.services.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import taskmanager.services.model.Task;
import taskmanager.services.model.impl.TaskImpl;
import taskmanager.services.service.persistence.TaskFinder;

@Component(service = TaskFinder.class)
public class TaskFinderImpl extends TaskFinderBaseImpl implements TaskFinder{
	
	private static final Log _log = LogFactoryUtil.getLog(TaskFinderImpl.class.getName());
	
	public List<Task> findByToDo(long groupId, long companyId, long userId, int start, int end){
		Session session = null;
        try {
            session = this.openSession();

            String sql = _customSQL.get(getClass(), "taskToDo");

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("T", TaskImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(companyId);
            qPos.add(groupId);
            qPos.add(userId);
            return (List<Task>) QueryUtil.list(q, this.getDialect(), start, end);
        } catch (Exception e) {
            _log.error(e, e);
        } finally {
            this.closeSession(session);
        }
        return null;
	}
	
	public List<Task> findByDone(long groupId, long companyId, long userId, int start, int end){
		Session session = null;
        try {
            session = this.openSession();

            String sql = _customSQL.get(getClass(), "taskDone");

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("T", TaskImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(companyId);
            qPos.add(groupId);
            qPos.add(userId);
            return (List<Task>) QueryUtil.list(q, this.getDialect(), start, end);
        } catch (Exception e) {
            _log.error(e, e);
        } finally {
            this.closeSession(session);
        }
        return null;
	}

	@Reference
	private CustomSQL _customSQL;
}
