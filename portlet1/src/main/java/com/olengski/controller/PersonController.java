package com.olengski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import the domain
import com.olengski.web.domain.Person;
import com.olengski.service.PersonService;
import com.olengski.common.ListWrapper;
import com.olengski.common.NameValuePair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.ArrayList;

@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	//@PreAuthorize("@sampleUserDetailsService.isAuthorizedToAccessData(#id)")	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable("id") String id, Principal principal) {
		Person person = personService.getPerson(id);
		if (person == null)
			return new Person();
		else
			return person;
	}

	@RequestMapping(value = "/person", headers = { "accept=application/json" }, method = RequestMethod.POST)
	public Person saveNewPerson(@RequestBody Person person) {
		personService.saveNewPerson(person);
		return person;
	}

	@RequestMapping(value = "/person/{id}", headers = { "accept=application/json" }, method = RequestMethod.PUT)
	public Person updatePerson(@RequestBody Person person) {
		personService.savePerson(person);
		return person;
	}

	@RequestMapping("/persons")
	public ListWrapper<Person> getAllPersons(@RequestParam("page") int pageNumber,
			@RequestParam("per_page") int pageSize,
			@RequestParam(value = "sort_by", required = false) String sortByAttributeName,
			@RequestParam(value = "order", required = false) String sortDirection) {
		return personService.getPersons(pageNumber, pageSize, sortByAttributeName, sortDirection);

	}

	//=============
}
