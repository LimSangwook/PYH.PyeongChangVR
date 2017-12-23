package com.common.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.client.event.RowHandler;

@Repository
public class CommonDao extends SqlMapClientDaoSupport implements ibatisDao{
	
	@Resource(name = "sqlMapClient")
	public void init(SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}

	public Object insert(String statement) throws DataAccessException {
		return super.getSqlMapClientTemplate().insert(statement);
	}
		
	public int update(String statement) throws DataAccessException {
		return super.getSqlMapClientTemplate().update(statement);
	}

	public int delete(String statement) throws DataAccessException {
		return super.getSqlMapClientTemplate().delete(statement);
	}

	public Object queryForObject(String statement) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForObject(statement);
	}

	public Object insert(String statement, Object parameterObject)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().insert(statement,
				parameterObject);
	}

	public int update(String statement, Object parameterObject)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().update(statement,
				parameterObject);
	}

	public int delete(String statement, Object parameterObject)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().delete(statement,
				parameterObject);
	}

	public Object queryForObject(String statement, Object parameterObject)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForObject(statement,
				parameterObject);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryForList(String statement, Object parameterObject)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(statement,
				parameterObject);
	}
		
	public List<?> queryForObjectList(String statement, Object parameterObject)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(statement,
				parameterObject);
	}

	public List<?> queryForObjectList(String statement) throws DataAccessException {
		return queryForObjectList(statement, null);
	}
	
	public void batchUpdate(final String statement, final List<?> list)
			throws DataAccessException {
		super.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator<?> iter = list.iterator(); iter.hasNext();) {
					executor.update(statement, iter.next());
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	public void batchDelete(final String statement, final List<?> list)
			throws DataAccessException {
		super.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator<?> iter = list.iterator(); iter.hasNext();) {
					executor.delete(statement, iter.next());
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void batchInsert(final String statement, final List<?> list)
			throws DataAccessException {
		super.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator<?> iter = list.iterator(); iter.hasNext();) {
					executor.insert(statement, iter.next());
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public void queryWithRowHandler(String statement, Object paramerterObject,
			RowHandler rowHandler) throws DataAccessException {
		super.getSqlMapClientTemplate().queryWithRowHandler(statement,
				paramerterObject, rowHandler);
	}

	public SqlMapClient getSqlMapClientForTransaction() {
		return super.getSqlMapClient();
	}
}
