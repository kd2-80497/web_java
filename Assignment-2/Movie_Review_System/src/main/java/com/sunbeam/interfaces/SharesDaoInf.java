/**
 * 
 */
package com.sunbeam.interfaces;

/**
 * @author Ritu Powar
 *
 */


public interface SharesDaoInf {
	public void add(int rid,int uid) throws Exception;
	public void delete(int rid) throws Exception;
}
