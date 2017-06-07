package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.ConState;
import com.ruanko.utils.AppException;

/**
 * Contract state Data Access Layer Interface
 */
public interface ConStateDao {

	/**
	 * Add contract operation state information
	 * 
	 * @param  conState Contract status object
	 * @return boolean   Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException;
	
	/**
	 * Query contract id set that meet the conditions according to the contract type
	 * 
	 * @param type Operation type
	 * @return contract id set
	 * @throws AppException
	 */
	public List<Integer> getConIdsByType(int type) throws AppException;
	
	/**
	 * Query contract state information according to contract id and type
	 * 
	 * @param conId Contract id
	 * @param type Operation type
	 * @return Contract state object
	 * @throws AppException
	 */
	public ConState getConState(int conId, int type) throws AppException;
	
}
