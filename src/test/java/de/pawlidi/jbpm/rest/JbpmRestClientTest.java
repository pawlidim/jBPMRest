package de.pawlidi.jbpm.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pawlidi.jbpm.rest.data.Containers;

public class JbpmRestClientTest {

	private static final String URL = "http://192.168.99.100";
	private static final String USER = "wbadmin";
	private static final String PASSWORD = "wbadmin";
	private JbpmRestClient client;

	@Before
	public void setUp() throws Exception {
		client = new JbpmRestClient(URL, USER, PASSWORD);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetContainers() {
		Containers containers = client.getContainers();
		assertNotNull(containers);
		assertFalse(containers.getContainers().isEmpty());
	}

}
