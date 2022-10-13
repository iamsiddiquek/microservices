/**
 * 
 */
package com.iris.accounts.models;

import com.iris.accounts.entity.Accounts;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author MSK
 *
 */
@Getter
@Setter
@ToString
public class CustomerDetails {
	
	private Accounts accounts;
	private List<Loans> loans;
	private List<Cards> cards;
	
	

}
