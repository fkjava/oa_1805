package org.fkjava.workflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { WorkflowConfig.class })
@SpringBootTest
public class WorkflowConfigTests
//extends AbstractJUnit4SpringContextTests //
		extends AbstractTransactionalJUnit4SpringContextTests//
{

	@Test
	public void test() {
		System.out.println("测试初始化流程引擎");
	}
}
