package com.devwork.common.ibatis.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devwork.common.ibatis.com.AbstractDAO;
import com.devwork.common.map.CommonMap;

import org.springframework.stereotype.Repository;

/**
 * com.devwork.common.ibatis.dao 
 *    |_ CommonDAO.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@Repository("commonDAO")
public class CommonDAO extends AbstractDAO{

  @SuppressWarnings("rawtypes")
  public Object insert(String queryID, HashMap param)throws Exception{
	  return super.insert(queryID, param);
  }

  @SuppressWarnings("rawtypes")
  public int update(String queryID, HashMap param)throws Exception{
	  return super.update(queryID, param);
  }

  @SuppressWarnings("rawtypes")
  public int delete(String queryID, HashMap param)throws Exception{
	  return super.delete(queryID, param);
  }

  @SuppressWarnings("rawtypes")
  public CommonMap select(String queryID, HashMap param)throws Exception{
	  return (CommonMap)selectByPk(queryID, param);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public HashMap<String, Object> selectByPkMap(String queryID, HashMap param)throws Exception{
	  return (HashMap<String, Object>)selectByPk(queryID, param);
  }

  @SuppressWarnings("rawtypes")
  public String selectByPkStr(String queryID, HashMap param)throws Exception{
	  return (String)selectByPk(queryID, param);
  }

  @SuppressWarnings("rawtypes")
  public List selectList(String queryID, HashMap param)throws Exception{
	  return super.list(queryID, param);
  }
  
  @SuppressWarnings("rawtypes")
  public List selectListStr(String queryID, String str)throws Exception{
	  return super.list(queryID, str);
  }
  
  @SuppressWarnings({ "deprecation", "rawtypes" })
  public int selectListTotCnt(String queryID, HashMap param){
	  return (Integer)getSqlMapClientTemplate().queryForObject(queryID, param);
	  //return ((Integer)getSqlSession().selectOne(queryID, param)).intValue();
	  //return (Integer)getSqlMapClientTemplate().queryForObject(queryID, param)).intValue();
  }

  @SuppressWarnings("rawtypes")
  public List getPageList(String queryID, Object parameterObject, int iPageSet, int iCurrentPage)throws SQLException{
	  return listWithPaging(queryID, parameterObject, iCurrentPage, iPageSet);
  }

  @SuppressWarnings("rawtypes")
  public List selectArrayList(String queryID, HashMap<String, ArrayList<String>> map)throws SQLException{
	 return super.list(queryID, map );
  }
  
}