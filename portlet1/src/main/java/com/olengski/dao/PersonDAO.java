package com.olengski.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import the domain
import com.olengski.web.domain.Person;
import com.olengski.common.ListWrapper;
import com.olengski.dao.PersonDAO;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Repository
public class PersonDAO {

	//private List<Person> allPerson = new ArrayList<Person>();
	private Map<String, Person> allData = new LinkedHashMap<String, Person>();

	@PostConstruct
	public void init() {

		InputStream is = getClass().getResourceAsStream("/sampledata/Persons.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				String data[] = line.split(",");
				Person person = new Person();
				//person.setId(data[0]);
				//person.setFirstName(data[1]);
				//person.setLastName(data[2]);
				person.setPersonId(data[0]);
				person.setFirstName(data[1]);
				person.setLastName(data[2]);
				allData.put(data[0], person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Person getPerson(String id) {
		return allData.get(id);
	}

	public void saveNewPerson(Person person) {
	}

	public void savePerson(Person person) {
	}

	public ListWrapper<Person> getPersons(int page, int pageSize, String sortByAttributeName, String sortDirection) {

		List<Person> allDataList = new ArrayList<Person>(allData.values());
		List<Person> partialPage = new ArrayList<Person>();
		int end = (page * pageSize);
		int start = (end) - pageSize;
		int totalPages = roundUp(allDataList.size(), pageSize);

		if (end > allDataList.size())
			end = allDataList.size();
		if (start < allDataList.size())
			partialPage = allDataList.subList(start, end);
		Long totalRows = new Long(allDataList.size());

		ListWrapper<Person> listWrapper = new ListWrapper<Person>();
		listWrapper.setRows(partialPage);
		listWrapper.setTotalRecords(totalRows.intValue());
		listWrapper.setLastPage(totalPages);
		return listWrapper;
	}

	private int roundUp(int num, int divisor) {
		return (num + divisor - 1) / divisor;
	}
}
