package de.pawlidi.jbpm.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pawlidi.jbpm.rest.data.Container;
import de.pawlidi.jbpm.rest.data.ContainerData;
import de.pawlidi.jbpm.rest.data.Containers;
import de.pawlidi.jbpm.rest.data.ProcessDefinition;
import de.pawlidi.jbpm.rest.data.ProcessDefinitions;
import de.pawlidi.jbpm.rest.data.ProcessInstance;
import de.pawlidi.jbpm.rest.data.ProcessInstanceStatus;
import de.pawlidi.jbpm.rest.data.ProcessInstances;
import de.pawlidi.jbpm.rest.data.TaskSummary;
import de.pawlidi.jbpm.rest.data.TaskSummaryList;

/**
 * 
 * @author PAWLIDIM
 *
 */
public class JbpmRestClientTest {

	private static final String URL = "http://localhost:8181";
	private static final String USER = "wbadmin";
	private static final String PASSWORD = "wbadmin";
	private JbpmRestClient client;
	private Map<String, String> processParams;

	@Before
	public void setUp() throws Exception {
		client = new JbpmRestClient(URL, USER, PASSWORD);
		processParams = new HashMap<>();
		processParams.put("key", "myAppTest");
		processParams.put("actorId", "Test app");
		processParams.put("result", "Test result");
		processParams.put("decision", "Task decision");
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
				System.out.println("Container id " + data.getId());
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
		Optional<ProcessInstances> instances = client.getProcessInstances(containerId, null, 999, null, null,
				ProcessInstanceStatus.Active);
		assertTrue(instances.isPresent());
		for (ProcessInstance inst : instances.get().getProcessInstances()) {
			System.out.println("Process instance " + inst.getIntstanceId() + " = " + inst.getName());
		}
	}

	@Test
	public void testProcessDefinitions() {
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
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		for (ProcessDefinition def : processDefinitions) {
			System.out.println(def.getId());
		}
	}

	@Test
	public void startProcess() {
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		String containerId = null;
		String processId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		for (ProcessDefinition def : processDefinitions) {
			processId = def.getId();
			break;
		}
		Optional<Long> instanceId = client.startProcess(containerId, processId, processParams);
		assertTrue(instanceId.isPresent());
		assertTrue(client.activateTask(containerId, instanceId.get(), USER));
	}

	@Test
	public void testUpdateVariables() {
		processParams.put("key", "NewMyAppTest");
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		String containerId = null;
		String processId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		for (ProcessDefinition def : processDefinitions) {
			processId = def.getId();
			break;
		}
		Optional<Long> instanceId = client.startProcess(containerId, processId, processParams);
		assertTrue(instanceId.isPresent());
		assertTrue(client.updateVariables(containerId, instanceId.get(), processParams));
	}

	@Test
	public void testAllVariables() {
		processParams.put("key", "NewMyAppTest");
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		String containerId = null;
		String processId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		for (ProcessDefinition def : processDefinitions) {
			processId = def.getId();
			break;
		}
		Optional<Long> instanceId = client.startProcess(containerId, processId, processParams);
		assertTrue(instanceId.isPresent());
		assertTrue(client.updateVariables(containerId, instanceId.get(), processParams));

		Optional<Map<String, String>> fromServer = client.getProcessVariables(containerId, instanceId.get());
		assertTrue(fromServer.isPresent());
		assertTrue(fromServer.get().get("key").equals("NewMyAppTest"));
	}

	@Test
	public void testProcessInstancesByInitiator() {
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
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		String processName = null;
		for (ProcessDefinition def : processDefinitions) {
			processName = def.getName();
			break;
		}
		Optional<ProcessInstances> instances = client.getProcessInstancesForInitiator(USER, null, 999, processName,
				null, null, ProcessInstanceStatus.Active);
		assertTrue(instances.isPresent());
		for (ProcessInstance inst : instances.get().getProcessInstances()) {
			System.out.println(
					"Process instance for user " + USER + " >> " + inst.getIntstanceId() + " = " + inst.getName());
		}
	}

	@Test
	public void testTasksForInstance() {
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
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		String processName = null;
		for (ProcessDefinition def : processDefinitions) {
			processName = def.getName();
			break;
		}
		Optional<ProcessInstances> instances = client.getProcessInstancesForInitiator(USER, null, 999, processName,
				null, null, ProcessInstanceStatus.Active);
		assertTrue(instances.isPresent());
		for (ProcessInstance inst : instances.get().getProcessInstances()) {
			System.out.println(
					"Process instance for user " + USER + " >> " + inst.getIntstanceId() + " = " + inst.getName());
			Optional<TaskSummaryList> tasksResponse = client.getTasksForInstance(new Long(inst.getIntstanceId()), null,
					999, null, null, null);
			assertTrue(tasksResponse.isPresent());
			List<TaskSummary> taskSummaries = tasksResponse.get().getTaskSummaries();
			assertNotNull(taskSummaries);
			for (TaskSummary task : taskSummaries) {
				System.out.println("Instance " + inst.getIntstanceId() + " with task " + task.getName());
			}
		}

	}

	@Test
	public void testCompleteTask() {
		Optional<Containers> containers = client.getContainers();
		assertTrue(containers.isPresent());
		assertFalse(containers.get().getResult().getContainers().isEmpty());
		Collection<Container> elms = containers.get().getResult().getContainers();
		String containerId = null;
		String processId = null;
		for (Container container : elms) {
			for (ContainerData data : container.getContainerDataList()) {
				containerId = data.getId();
				break;
			}
		}
		Optional<ProcessDefinitions> defs = client.getProcessDefinitions(containerId, null, 999, null, null);
		assertTrue(defs.isPresent());
		Collection<ProcessDefinition> processDefinitions = defs.get().getProcessDefinitions();
		for (ProcessDefinition def : processDefinitions) {
			processId = def.getId();
			break;
		}
		Optional<Long> instanceId = client.startProcess(containerId, processId, processParams);
		assertTrue(instanceId.isPresent());
		assertTrue(client.activateTask(containerId, instanceId.get(), USER));
		assertTrue(client.completeTask(containerId, instanceId.get(), true, USER, processParams));
	}

}
