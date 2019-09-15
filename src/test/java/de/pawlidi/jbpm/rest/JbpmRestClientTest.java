package de.pawlidi.jbpm.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pawlidi.jbpm.rest.data.Container;
import de.pawlidi.jbpm.rest.data.ContainerData;
import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.ProcessInstance;
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
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertNotNull(containers.get().getResult());
		assertNotNull(containers.get().getResult().getContainers());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				System.out.println(data.getId());
			}
		}
	}

	@Test
	public void testProcessInstances() {
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		String containerId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		Optional<ProcessInstances> instances = client.getProcessInstances(containerId);
		assertTrue(instances.isPresent());
	}

	@Test
	public void testProcessInstance() {
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		String containerId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		Optional<ProcessInstances> instances = client.getProcessInstances(containerId);
		assertTrue(instances.isPresent());
		Collection<ProcessInstance> processInstances = instances.get().getProcessInstances();
		for (ProcessInstance processInstance : processInstances) {
			assertTrue(client.getProcessInstance(containerId, processInstance.getIntstanceId()).isPresent());
		}
	}
}
