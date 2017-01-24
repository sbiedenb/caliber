package com.revature.caliber.controllers.qc;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.controllers.QCBatchController;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QCBatchControllerTest {

	private static ApplicationContext context;
	 	private static QCBatchController controller;
	 	
	 	
	 	@BeforeClass
	 	public static void preClass(){
	 		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	 		controller = context.getBean(QCBatchController.class);
	 	}

	 	/**
	 	 *  Tests qc batch controller method(getAllCurrentBatches)
	 	 *  - returns all current batches
	 	 */
	 	@Test
	 	public void getCurrentBatches(){
	 		ResponseEntity<Set<Batch>> entity = controller.getAllCurrentBatches();
	 		Set<Batch> set = entity.getBody();
	 		
	 		assertNotNull(set);
	 		assertEquals(100, set.size());
	 	}
	 	
	 	/**
	 	 * Tests qc batch controller method(getCurrentBatch)
	 	 * - returns a current batch with the given id
	 	 */
	 	@Test
	 	public void getCurrentBatch(){
	 		int batchId = 9;
	 		ResponseEntity<Batch> entity = controller.getCurrentBatch(batchId);
	 		Batch batch = entity.getBody();
	 		
	 		assertNotNull(batch);
	 		assertEquals(batchId, batch.getBatchId());
	 	}
}