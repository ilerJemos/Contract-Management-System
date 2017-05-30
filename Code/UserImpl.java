package com.jebysun.contractmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jebysun.contractmanager.dao.UserDAO;
import com.jebysun.contractmanager.form.UserForm;
import com.jebysun.contractmanager.util.DataBaseConnection;

public class UserImpl implements UserDAO {
	Connection con;
	public UserImpl() {
		con = DataBaseConnection.getConnection();
	}

	public boolean delete(UserForm user) {
		int i = 0;
		String sql = "delete from t_user where id=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, user.getId());
			i = pst.executeUpdate();
			DataBaseConnection.closeAll(null, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i>0?true:false;
	}

	public UserForm login(UserForm user) {
		String sql = "select * from t_user where username=? and password=?";
		UserForm _user = null;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				_user = new UserForm();
				_user.setId(rs.getInt("id"));
				_user.setUsername(rs.getString("username"));
				_user.setPassword(rs.getString("password"));
				_user.setZwmc(rs.getString("zwmc"));
				_user.setRealName(rs.getString("realname"));
				_user.setCreateable(rs.getInt("createable"));
				_user.setReadable(rs.getInt("readable"));
				_user.setUpdateable(rs.getInt("updateable"));
				_user.setDeleteable(rs.getInt("deleteable"));
				_user.setCreatedate(rs.getDate("createdate"));
				_user.setType(rs.getInt("type"));
			}
			DataBaseConnection.closeAll(rs, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return _user;
	}

	public boolean register(UserForm user) {
		int i = 0;
		String sql = "insert into t_user values(null,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getZwmc());
			pst.setString(4 ,user.getRealName());
			pst.setInt(5, user.getReadable());
			pst.setInt(6, user.getUpdateable());
			pst.setInt(7, user.getCreateable());
			pst.setInt(8, user.getDeleteable());
			pst.setDate(9, user.getCreatedate());
			pst.setInt(10,user.getType());
			i = pst.executeUpdate();
			DataBaseConnection.closeAll(null, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i>0?true:false;
	}

	public boolean updateZl(UserForm user) {
		int i = 0;
		String sql = "update t_user set username=?,password=? where id=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3,user.getId());
			i = pst.executeUpdate();
			DataBaseConnection.closeAll(null, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i>0?true:false;
	}
	
	public boolean updateQx(UserForm user) {
		int i = 0;
		String sql = "update t_user set zwmc=?,readable=?,createable=?,updateable=?,deleteable=? where id=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getZwmc());
			pst.setInt(2, user.getReadable());
			pst.setInt(3, user.getCreateable());
			pst.setInt(4, user.getUpdateable());
			pst.setInt(5, user.getDeleteable());
			pst.setInt(6,user.getId());
			i = pst.executeUpdate();
			DataBaseConnection.closeAll(null, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i>0?true:false;
	}

	public List allUsers(int start) {
		List allUsers = new ArrayList();
		UserForm user;
		String sql = "select * from t_user where type=0 limit ?,10;";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, start);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				user = new UserForm();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				allUsers.add(user);
			}
			DataBaseConnection.closeAll(rs, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}

	public UserForm readUserInfo(UserForm user) {
		String sql = "select * from t_user where id=?;";
		UserForm _user = null;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, user.getId());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				_user = new UserForm();
				_user.setId(rs.getInt("id"));
				_user.setUsername(rs.getString("username"));
				_user.setZwmc(rs.getString("zwmc"));
				_user.setRealName(rs.getString("realname"));
				_user.setReadable(rs.getInt("readable"));
				_user.setUpdateable(rs.getInt("updateable"));
				_user.setCreateable(rs.getInt("createable"));
				_user.setDeleteable(rs.getInt("deleteable"));
				_user.setCreatedate(rs.getDate("createdate"));
			}
			DataBaseConnection.closeAll(rs, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return _user;
	}

	public int countAllUsers() {
		String sql = "select count(*) from t_user where type=0;";
		int count = 0;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			DataBaseConnection.closeAll(rs, pst, con);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return count;
	}

}
