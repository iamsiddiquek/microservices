/**
 * 
 */
package com.iris.accounts.controller;

import com.iris.accounts.config.AccountsServiceConfig;
import com.iris.accounts.entity.Accounts;
import com.iris.accounts.entity.Customer;
import com.iris.accounts.entity.Properties;
import com.iris.accounts.models.Cards;
import com.iris.accounts.models.CustomerDetails;
import com.iris.accounts.models.Loans;
import com.iris.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

/**
 * @author M Siddique
 *
 */

@RestController
@Slf4j
public class AccountsController {

	private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountsServiceConfig accountsConfig;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders httpHeaders;


	
	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		logger.info("Start of My Account method.");
		return accountsRepository.findByCustomerId(customer.getCustomerId());

	}
	
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		return ow.writeValueAsString(properties);
	}


	@PostMapping("/myCustomerDetails")
	public CustomerDetails getMyCustomerDetails(@RequestBody Customer customer) {
		log.info("START hitting the MyCustomerDetails Endpoint...");
		CustomerDetails customerDetails = new CustomerDetails();

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

		Customer cus = new Customer();
		cus.setCustomerId(1);
		HttpEntity<Customer> request = new HttpEntity<>(cus);

		ResponseEntity<Object> loansListObject = restTemplate.postForEntity("http://localhost:8090/myLoans", request, Object.class);
		ResponseEntity<Object> cardsListObject = restTemplate.postForEntity("http://localhost:9000/myCards", request, Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper
				.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
				.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);


		List<Loans> loansList =  objectMapper.convertValue(loansListObject.getBody(), new TypeReference<>() {});
		List<Cards> cardsList =  objectMapper.convertValue(cardsListObject.getBody(), new TypeReference<>() {});


		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loansList);
		customerDetails.setCards(cardsList);

		log.info("END hitting the MyCustomerDetails Endpoint...");

		return customerDetails;
	}





}
