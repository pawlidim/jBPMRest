package de.pawlidi.jbpm.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pawlidi.jbpm.rest.data.Container;
import de.pawlidi.jbpm.rest.data.ContainerData;
import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.ProcessInstances;

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
		assertNotNull(containers.getResult());
		assertNotNull(containers.getResult().getContainers());
		assertFalse(containers.getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.getResult().getContainers();
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				System.out.println(data.getId());
			}
		}
	}

	@Test
	public void testProcessInstances() {
		Containers containers = client.getContainers();
		assertFalse(containers.getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.getResult().getContainers();
		String containerId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		ProcessInstances instances = client.getProcessInstances(containerId);
		assertNotNull(instances);
	}
}
