package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.ConProcessDao;
import com.ruanko.model.ConProcess;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * Contract process data access layer implementation class
 */
public class ConProcessDaoImpl implements ConProcessDao{
	
	/**
	 * Determine whether the record of specified contract id is exist in the contract process table
	 * 
	 * @param conId Contract id
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException{
		boolean flag = false;// Operation flag
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement:specifies the amount of contract Id's records, "?" is a placeholder
			String sql = "select count(id) as n from t_contract_process where con_id = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conId);
			// Execute query operation
			rs = psmt.executeQuery();
			rs.next();
			int n =  rs.getInt("n"); // Parameter "n" represents the total number of records
			if (n > 0) {
				flag = true; // Designated contract ID exist,flag is set to true
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ConProcessDaoImpl.isExist");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * Add contract operation process information
	 * 
	 * @param  conProcess Contract process object
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess)  throws AppException{	
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement:save contact operation process information, "?" is a placeholder
			String sql = "insert into t_contract_process(con_id,user_id,type,state,content) values(?,?,?,?,?)";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getUserId());
			psmt.setInt(3, conProcess.getType());
			psmt.setInt(4, conProcess.getState());
			psmt.setString(5, conProcess.getContent());
		
			int result = psmt.executeUpdate();// Execute update
			
			if(result > 0){// If the affected lines greater than 0, the operation success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ConProcessDaoImpl.add");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * Query all contract id set that meet the conditions from contract process table
	 * 
	 * @param Contract process object
	 * @return Contract id set
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException {
		// Initialize conIds
		List<Integer> conIds = new ArrayList<Integer>();
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement:query contract id according to user id,contract operation type and operation state, "?" is a placeholder
			String sql = "select con_id from t_contract_process " +
					"where user_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conProcess.getUserId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			
			// Execute query operation
			rs = psmt.executeQuery();
			
			// Get information in result set by loop,and save it to conIds
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ConProcessDaoImpl.getConIds");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conIds;
	}
	
	/**
	 * Update operation status,content and time info of contract according to user id,contract id and operation type
	 * 
	 * @param  userId User id
	 * @param  conId Contract id
	 * @param  type Operation type
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException {
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare sql:update operation status,content and time info of contract according to user id,contract id and operation type
			String sql = "update t_contract_process set state = ?, content = ?, time = ? " 
					+"where user_id = ? and con_id = ? and type = ?";

			// Pre-compiled sql, and set the parameter values
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getState());
			psmt.setString(2, conProcess.getContent());

			// Date format is：yyyy-MM-dd hh:mm:ss     
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.sql.Timestamp time = new java.sql.Timestamp(conProcess.getTime().getTime());
			df.format(time); // Formatting time
			psmt.setTimestamp(3, time);
			psmt.setInt(4, conProcess.getUserId());
			psmt.setInt(5, conProcess.getConId());
			psmt.setInt(6, conProcess.getType());
			// Execute update, return the affected rows
			int count = psmt.executeUpdate();
			
			if (count > 0) {// If affected lines greater than 0, the update is successful
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.dao.impl.ConProcessDaoImpl.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * Query total number of eligible records from contract process table according to contract id,operation type and its processing state
	 * 
	 * @param con_id Contract id
	 * @param type Operation type
	 * @param state State corresponding to the operation type
	 * @return otal number of eligible records
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException{
		int totalCount = 0; // Initialize totalCount
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,Query total number of eligible records according to contract id,operation type and its processing state, "?" is a placeholder
			String sql = "select count(id) as n from t_contract_process "
				 +"where con_id = ? and type = ? and state = ?";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			// Execute query operation 
			rs = psmt.executeQuery();
			rs.next();
			totalCount =  rs.getInt("n");  // Parameter "n" represents the total number of records
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ConProcessDaoImpl.getTotalCount");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return totalCount;
	}
	
	/**
	 * Query contract process id set according to contract id,operation type and its corresponding operation state
	 * 
	 * @param conId Contract id
	 * @param type Operation type
	 * @param state Operation state that corresponding operation type
	 * @return Contract process id set
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException {
		// Initialize ids
		List<Integer> ids = new ArrayList<Integer>();
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement:query contract process id set according to contract id,operation type and its corresponding operation state, "?" is a placeholder
			String sql = "select id from t_contract_process " +
					"where con_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, conId);
			psmt.setInt(2, type);
			psmt.setInt(3, state);
			
			// Execute query operation 
			rs = psmt.executeQuery();
			
			// Get information in result set by loop,and save to Ids
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ConProcessDaoImpl.getIds");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	/**
	 *  Query contract process information according to contract process id
	 * 
	 * @param id Contract id
	 * @return Contract process object
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException {
		// Declare conProcess
		ConProcess conProcess = null;
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Define sql:query contract process information according to contract process id
			String sql = "select id,con_id,user_id,type,state,content,time "
					+"from t_contract_process "
					+"where id = ? and del = 0";

			//Pre-compiled sql, and set the parameter values
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id); //Set contract id
			
			// Query result set
			rs = psmt.executeQuery();

			// Get information in result set by loop,and encapsulate to conProcess entity
			if(rs.next()) {
				conProcess = new ConProcess();
				conProcess.setId(rs.getInt("id"));
				conProcess.setConId(rs.getInt("con_id"));
				conProcess.setUserId(rs.getInt("user_id"));
				conProcess.setType(rs.getInt("type"));
				conProcess.setState(rs.getInt("state"));
				conProcess.setContent(rs.getString("content"));
				conProcess.setTime(rs.getTimestamp("time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.ConProcessDaoImpl.getById");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conProcess;
	}
}
