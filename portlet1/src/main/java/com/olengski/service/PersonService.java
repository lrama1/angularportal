package com.olengski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import the domain
import com.olengski.web.domain.Person;
import com.olengski.common.ListWrapper;
import com.olengski.dao.PersonDAO;

@Service
public class PersonService {

	@Autowired
	PersonDAO personDAO;

	public ListWrapper<Person> getPersons(int pageNumber, int pageSize, String sortByAttribute, String sortDirection) {
		return personDAO.getPersons(pageNumber, pageSize, sortByAttribute, sortDirection);
	}

	public Person getPerson(String id) {
		return personDAO.getPerson(id);
	}

	public void saveNewPerson(Person person) {
		personDAO.saveNewPerson(person);
	}

	public void savePerson(Person person) {
		personDAO.savePerson(person);
	}
}
