package com.common.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.event.RowHandler;

public interface ibatisDao {
	public Object insert(String statement) throws DataAccessException;

	public Object insert(String statement, Object parameterObject)
			throws DataAccessException;

	public int update(String statement) throws DataAccessException;

	public int update(String statement, Object parameterObject)
			throws DataAccessException;

	public int delete(String statement) throws DataAccessException;

	public int delete(String statement, Object parameterObject)
			throws DataAccessException;

	public Object queryForObject(String statement) throws DataAccessException;

	public Object queryForObject(String statement, Object parameterObject)
			throws DataAccessException;

	public List<?> queryForList(String statement, Object parameterObject)
			throws DataAccessException;

	public void batchUpdate(String statement, List<?> list)
			throws DataAccessException;

	public void batchInsert(String statement, List<?> list)
			throws DataAccessException;

	public void batchDelete(String statement, List<?> list)
			throws DataAccessException;

	public SqlMapClient getSqlMapClientForTransaction();

	public void queryWithRowHandler(String statement, Object paramerterObject,
			RowHandler rowHandler) throws DataAccessException;
}
