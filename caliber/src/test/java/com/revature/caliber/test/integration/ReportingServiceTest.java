package com.revature.caliber.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Address;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.ReportingService;


public class ReportingServiceTest extends CaliberTest {

	private static final String EMAIL = "email@email.com";
	private static final String TITLE = "A title:";
	private static final String REVATURE = "Revature";
	
	private static Logger log = Logger.getLogger(ReportingServiceTest.class);
	private static List<Trainee> trainees;
	
	private ReportingService reportingService;
	
	private static final int TEST_BATCH_ID = 2150;
	private static final int TEST_BATCH_ID2 = 2200;
	private static final int TEST_ASSESSMENT_WEEK = 6;
	private static final int TEST_TRAINEE_ID = 5460;
	private static final double FLOATING_NUMBER_VARIANCE = .01;
	
	@Autowired
	public BatchDAO batchDAO;

	public static List<Trainee> getTrainees() {
		return trainees;
	}

	public static void setTrainees(List<Trainee> trainees) {
		ReportingServiceTest.trainees = trainees;
	}

	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/*
	 * Arrange Dummy Data to look like this
	 * 
	 * |  Trainee  |   Week 1  |   Week 2  |   Week 3  |   Week 4  |   Week 5  |
	 * |-----------|-----------|-----------|-----------|-----------|-----------|
	 * | Trainee 1 |     10,50 |     20,50 |     30,50 |     40,50 |     50,50 |
	 * | Trainee 2 |     20,50 |     30,50 |     40,50 |     50,50 |     60,50 |
	 * | Trainee 3 |     30,50 |     40,50 |     50,50 |     60,50 |     70,50 |
	 * 
	 * Everything past that amount of detail doesn't really matter for Utility functions
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		trainees = new ArrayList<>();
		Batch batch = new Batch();
		batch.setWeeks(5);
		for (int i = 1; i < 4; i++) {
			Trainee trainee = new Trainee("Trainee" + i, "java", EMAIL, batch);
			trainee.setTraineeId(i);
			Set<Grade> grades = new HashSet<>();
			for (int j = 1; j < 6; j++) {
				Assessment assess1 = new Assessment(TITLE + j, batch, 100, AssessmentType.Exam, j, new Category());
				Assessment assess2 = new Assessment(TITLE + j, batch, 100, AssessmentType.Exam, j,new Category());
				grades.add(new Grade(assess1, trainee, new Date(), j * 10 + (i - 1) * 10));
				grades.add(new Grade(assess2, trainee, new Date(), 50));
			}
			trainee.setGrades(grades);
			trainees.add(trainee);
		}
	}

	/**
	 * Create several Dummy grades with dummy assessments and dummy categories
	 * Run tested method and test to see if: -result set is right size -results
	 * are the correct values
	 * 
	 * Method should be flexible enough to allow any list of grades with
	 * categories
	 **/
	@Test
	public void utilAvgSkillsTest() {
		log.info("TEST UTILITY AVERAGE SKILL");
		String catOne = "CatOne";
		String catTwo = "CatTwo";
		Assessment assessment1 = new Assessment("title", new Batch(), 200, null, 5, new Category(catOne, true));
		Assessment assessment2 = new Assessment("title two", new Batch(), 200, null, 5, new Category(catTwo, true));
		Grade grade1 = new Grade(assessment1, null, null, 150);
		Grade grade2 = new Grade(assessment1, null, null, 200);
		Grade grade3 = new Grade(assessment2, null, null, 100);
		Grade grade4 = new Grade(assessment2, null, null, 150);
		List<Grade> grades = new ArrayList<>();
		grades.add(grade1);
		grades.add(grade2);
		grades.add(grade3);
		grades.add(grade4);
		Map<Category, Double[]> results = reportingService.utilAvgSkills(grades);

		// Get all keys
		List<Category> keys = new ArrayList<>();
		for (Category cat : results.keySet()) {
			keys.add(cat);
		}
		// check that the result set is the right size
		assertEquals(2, keys.size());
		assertEquals(keys.get(0).getSkillCategory(), catOne);
		assertNotEquals(keys.get(0).getSkillCategory(), catTwo);
		assertEquals((Double) 175.0, results.get(keys.get(0))[0]);
		assertEquals(keys.get(1).getSkillCategory(), catTwo);
		assertNotEquals(keys.get(1).getSkillCategory(), catOne);
		assertEquals((Double) 125.0, results.get(keys.get(1))[0]);
	}
	
	/**
	 * 
	 * ReportingService.utilReplaceCategoryWithSkillName(Map<Category, Double[]> skills)
	 * 	Created two maps different maps of Categories with defined skill names
	 * 	compare the names of the keys in the returned map to what is saved when creating the skills
	 * 		to make sure they match
	 * 
	 * */
	@Test
	public void utilReplaceCategoryWithSkillNameTest() {
		log.info("TEST UTILITY REPLACE CATEGORY WITH SKILL NAME");
		Map<Category, Double[]> skills = new HashMap<>();
		Double[] values = { (double) 20, (double) 10 };
		skills.put(new Category("Name One", true), values);
		skills.put(new Category("Name Two", true), values);
		Map<String, Double> replaced = reportingService.utilReplaceCategoryWithSkillName(skills);
		List<String> keys = new ArrayList<>();
		for (Entry<String, Double> entry : replaced.entrySet()) {
			keys.add(entry.getKey());
		}
		assertEquals("Name One", keys.get(0));
		assertEquals("Name Two", keys.get(1));
	}
	
	/**
	 * 
	 * ReportingService.utilAvgBatchWeekValue(List<Trainee> trainees, Integer week)
	 * 	Use pre-initialized data as parameter in utilAvgBatchWeekValue to save expected
	 * 		weighted average for two different weeks
	 * 	Compare saved values to actual, easily calculated averages to make sure they match
	 * 
	 * */
	@Test
	public void utilAvgBatchWeekValueTest() {
		log.info("TEST UTILITY AVERAGE BATCH WEEK VALUE");
		Double actualWeekOne = reportingService.utilAvgBatchWeekValue(trainees, 1);
		Double actualWeekTwo = reportingService.utilAvgBatchWeekValue(trainees, 2);
		Double expectedWeekOne = 35.0;
		Double expectedWeekTwo = 40.0;

		assertEquals(expectedWeekOne, actualWeekOne);
		assertEquals(expectedWeekTwo, actualWeekTwo);
	}



	/**
	 * Validates if the overall average for the batch over a certain number of weeks
	 * is calculated correctly, given a List of trainees, an AssessmentType, and number 
	 * of weeks. Utilizes the dummy data created in the BeforeClass, to make the 
	 * comparisons easier to calculate and compare.
	 */
	@Test
	public void utilAvgBatchOverallWithThreeParamsTest() {
		log.info("Calculate Average Batch Grade with UtilAvgBatchOverallWithThreeParams()");
		AssessmentType assessmentType = AssessmentType.Exam;
		Map<Integer, Double[]> results = reportingService.utilAvgBatchOverall(trainees, assessmentType, 3);
		double[] possAvg = { 35.0, 40.0, 45.0 };
		for (int i = 1; i < 4; i++) {
			log.info(results.get(i));
			assertEquals(new Double(possAvg[i - 1]), results.get(i)[0]);
		}
	}

	/**
	 * Validates if the trainee's average for a week is calculated correctly, given 
	 * a set of grades and specific week number. Utilizes the dummy data created in 
	 * the BeforeClass, to make the comparisons easier to calculate and compare.
	 */
	@Test
	public void utilAvgTraineeWeekWithTwoParamsTest() {
		log.info("Calculate One Trainee's Average for all Exams in a Given Week");
		Double expectedAverage = 30.0;
		int selectedTrainee = 0; // Selects first trainee in dummy batch
		Set<Grade> grades = trainees.get(selectedTrainee).getGrades();
		Double actualAverage = reportingService.utilAvgTraineeWeek(grades, 1);
		assertEquals(expectedAverage, actualAverage);
	}

	/**
	 * Validates if the average for each trainee in a batch for a specific week is 
	 * calculated correctly, given a List of trainees and specific week number. 
	 * Utilizes the dummy data created in the BeforeClass, to make the comparisons 
	 * easier to calculate and compare.
	 */
	@Test
	public void utilAvgBatchWeekWithTwoParamsTest() {
		log.info("Calculate Each Trainee's Average for a Given Week");
		double[] averages = { 30.0, 35.0, 40.0 }; // Week 1 averages for all 3 trainees
		for (int i = 0; i < 3; i++) {
			Set<Grade> grades = trainees.get(i).getGrades();
			Double avg = 0d;
			avg = reportingService.utilAvgTraineeWeek(grades, 1);
			assertEquals(new Double(averages[i]), avg);
		}
	}

	/*
	 * Test utilAvgBatch with any number of weeks entered With the weeks for the
	 * batch set to 5, I use a range from -1 to 6 to see if it works for the
	 * boundaries and even past them
	 */
	@Test
	public void utilAvgBatchTest() {
		log.info("Calculate Average Batch Grade with UtilAvgBatch()");
		// Calculated by hand with dummy data above
		double[] posAvg = { 0.0, 35, 37.5, 40, 42.5, 45 };
		for (int i = 0; i < 6; i++) {
			assertEquals(new Double(posAvg[i]), reportingService.utilAvgBatch(trainees, i));
		}
	}

	@Test
	public void utilAvgTraineeOverallWithTwoParamsTest() {
		log.info("Calculate Average grade per week for a trainee");
		// Calculated by hand with dummy data above
		double[] weekAvgs = { 30, 35, 40, 45, 50 };
		for (int i = 0; i < 5; i++) {
			assertEquals(new Double(weekAvgs[i]),
					reportingService.utilAvgTraineeOverall(trainees.get(0).getGrades(), i + 1).get(i + 1));
		}
	}

	@Test
	// Need a specific batch to get trainees
	public void utilAvgBatchOverallWithTwoParamsTest() {
		log.info("Calculating batch averages per week");
		double[] overallAvg = { 35, 40, 45, 50, 55 };
		for (int i = 0; i < 5; i++) {
			assertEquals(new Double(overallAvg[i]), reportingService.utilAvgBatchOverall(trainees, i + 1).get(i + 1));
		}
	}

	/**
	  * Test:
	  * utilAvgTraineeWeek to verify that the average grade for a trainee's week is correct
	  * given the week, assessment type, and all grades that week.
	  */
	@Test
	public void utilAvgTraineeWeekWithThreeParamTest() {
		log.info("UtilAvgTraineeWeekWithThreeParam Test");
		double[] possAvg = { 30.0, 100.0, 2.0 };
		Double[] actual = reportingService.utilAvgTraineeWeek(1, AssessmentType.Exam, trainees.get(0).getGrades());
		for (int i = 0; i < 3; i++) {
			assertEquals(new Double(possAvg[i]), actual[i]);
		}
	}

	/**
	  * Test:
	  * utilAvgBatchWeek to verify that the average grade for a batch's week is correct
	  * given all trainees for the batch, the week, and assessment type.
	  */
	@Test
	public void utilAvgBatchWeekWithThreeParamTeat() {
		log.info("UtilAvgBatchWeekWithThreeParam Test");
		double[] possAvg = { 30.0, 35.0, 40.0 };
		int pos = 0;
		Map<Trainee, Double[]> actual = reportingService.utilAvgBatchWeek(trainees, 1, AssessmentType.Exam);
		for (Trainee trainee : trainees) {
			assertEquals(new Double(possAvg[pos]), actual.get(trainee)[0]);
			pos++;
		}
	}

	/**
	  * Test:
	  * utilAvgTraineeOverall to verify that the average grade for all weeks of trainee is correct
	  * given all the grades and assessment type.
	  */
	@Test
	public void utilAvgTraineeOverallWithThreeParamTest() {
		log.info("UtilAvgTraineeOverallWithThreeParam Test");
		int weeks = 5;
		double[] possAvg = { 30.0, 35.0, 40.0, 45.0, 50.0 };
		Map<Integer, Double[]> actual = reportingService.utilAvgTraineeOverall(
				trainees.get(0).getGrades(), AssessmentType.Exam, weeks);
		for (int i = 0; i < weeks; i++) {
			assertEquals(new Double(possAvg[i]), actual.get(i + 1)[0]);
		}
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.ReportingService#getBatchComparisonAvg(List<Batch> batches)
	 */
	@Test
	public void getBatchComparisonAvgTest(){
		log.info("Testing the ReportingService.getBatchCommparisonAvg(List<Batch> batches)");

		List<Batch> batches = batchComparisonInit();
		
		List<Batch> singleBatch1 = new ArrayList<>();
		singleBatch1.add(batches.get(0));
		
		List<Batch> singleBatch2 = new ArrayList<>();
		singleBatch2.add(batches.get(1));
		
		double avg = reportingService.getBatchComparisonAvg(singleBatch1);
		double expected = 80;
		log.info(avg);
		assertTrue(Math.abs(avg-expected)<.0001);
		
		avg = reportingService.getBatchComparisonAvg(singleBatch2);
		expected = 90;
		assertTrue(Math.abs(avg-expected)<.0001);
		
		avg = reportingService.getBatchComparisonAvg(batches);
		expected = 85;
		assertTrue(Math.abs(avg-expected)<.0001);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.ReportingService#batchComparisonFilter(List<Batch> batches, String skill, String training)
	 */
	@Test
	public void batchComparisonFilterTest(){
		log.info("Testing the ReportingService.batchComparisonFilter(List<Batch> batches, String skill, String training)");
		
		String allSkills = "(All)";
		String allTraining = "(All)";
		
		String j2eeSkill = "J2EE";
		String revatureTraining = REVATURE;
		String universityTraining = "University";
		
		
		List<Batch> batches = reportingService.batchComparisonFilter(batchDAO.findAll(), allSkills, allTraining);
		int expected = 6;
		int actual = batches.size();
		assertEquals(expected, actual);
		

		//There is only one type of training in the setup.sql, so this is the same result "(All)"
		//but uses a different code path
		batches = reportingService.batchComparisonFilter(batchDAO.findAll(), j2eeSkill, universityTraining);
		expected = 2;
		actual = batches.size();
		assertEquals(expected, actual);
		

		batches = reportingService.batchComparisonFilter(batchDAO.findAll(), allSkills, revatureTraining);
		expected = 4;
		actual = batches.size();
		assertEquals(expected, actual);
		
		batches = reportingService.batchComparisonFilter(batchDAO.findAll(), allSkills, universityTraining);
		expected = 2;
		actual = batches.size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.services.ReportingService.getBatchOverallBarChart
	 */
	@Test
	public void getBatchOverallBarChartTest() {
		log.info("Testing getBatchOverallBarChart(int batchId)");
		// Positive Testing
		Map<String, Double> results = reportingService.getBatchOverallBarChart(2200);
		assertTrue("Test size of result set", results.size() == 15);
		assertTrue("Contains expected trainee", results.containsKey("Chen, Andrew"));
		assertTrue("Test accurate average calculation", Math.abs(results.get("Chen, Andrew").doubleValue()-84.14575) < .001);

		// Negative Testing
		// Grab non-existent batch
		try {
			reportingService.getBatchOverallBarChart(-1111);
			fail();
		} catch (NullPointerException e) {
			log.debug(e);
		}
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.services.ReportingService.getBatchWeekTraineeBarChart
	 */
	@Test
	public void getBatchWeekTraineeBarChartTest() {
		log.info("Testing getBatchWeekTraineeBarChart(int batchId, int TraineeId, int week)");
		// Positive testing
		Map<String, Double[]> results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, 1);
		assertNotNull("Results exist", results);
		assertTrue("Test size of result set", results.size() == 1);
		assertTrue("Result contains exam", results.containsKey("Exam"));
		//Check if values are within an acceptable margin of error (floating point, can't compare directly)
		assertTrue("Exam contains expected values",
				 Math.abs(results.get("Exam")[0] - 93.0) < 0.001 &&  Math.abs(results.get("Exam")[1] - 85.625) < 0.001 &&  Math.abs(results.get("Exam")[2] - 100) < 0.001);

		// Invalid TraineeID
		try {
			reportingService.getBatchWeekTraineeBarChart(2100, -123421, 1);
			fail();
		} catch (NoSuchElementException e) {
			log.info(e);
		}
		//Test non-existent week.
		results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, -1000);
		assertTrue("Check invalid week", results.size() == 0);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.services.ReportingService.getBatchOverallTraineeBarChart
	 */
	@Test
	public void getBatchOverallTraineeBarChartTest() {
		log.info("Testing getBatchOverallTraineeBarChart(int batchId, int TraineeId)");
		// Training
		Map<String, Double[]> results = reportingService.getBatchOverallTraineeBarChart(2201, 5531);
		assertNotNull("Results exist", results);
		assertTrue("Test size of result set ", results.size() == 4);
		assertTrue("Test data exists", results.containsKey("Exam"));
		
		Double[] myVals = results.get("Verbal");
		assertTrue("Test values of Verbal exam",  Math.abs(myVals[0] - 69.2) < 0.001 &&  Math.abs(myVals[1] - 82.0) < 0.001);

		// Invalid TraineeID
		try {
			reportingService.getBatchOverallTraineeBarChart(2100, -123421);
			fail();
		} catch (NoSuchElementException e) {
			log.info(e);
		}
	}
	
	/**
	 *  Test method:
	 *  @see com.revature.caliber.services.ReportingService.getBatchWeekQcOverallBarChart(Integer batchId, Integer week)
	 */
	@Test
	public void getBatchWeekQcOverallBarChartTest(){
		log.info("Testing method: getBatchWeekQcOverallBarChart(Integer batchId, Integer week)");
		Note testNote = reportingService.getBatchWeekQcOverallBarChart(2201, 7);
		//Note: I was pulling the getContent from testNote, and it would return a different string sometimes.
		assertEquals(testNote.getWeek(),(short)7);
		//invalid batch
		testNote = reportingService.getBatchWeekQcOverallBarChart(22, 7);
		assertNull(testNote);
		//invalid batch
		testNote = reportingService.getBatchWeekQcOverallBarChart(2201, -5);
		assertNull(testNote);

	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#utilSeparateQCTraineeNotesByWeekTest()
	 * 
	 * 
	 */
	@Test
	public void utilSeparateQCTraineeNotesByWeekTest() {

		log.info("Testing the ReportingService.utilSeperateQCTraineeNotesByWeek");

		int[] statusPoorCountPerWeek = { 0, 0, 0, 3, 0, 0, 0 };
		int[] statusAverageCountPerWeek = { 3, 0, 0, 0, 3, 0, 0 };
		int[] statusGoodCountPerWeek = { 0, 3, 0, 0, 0, 3, 0 };
		int[] statusSuperstarCountPerWeek = { 0, 0, 3, 0, 0, 0, 3 };

		Batch batch = createTestBatchWithQCNotes();

		//positive testing
		Map<Integer, Map<QCStatus, Integer>> results = reportingService.utilSeparateQCTraineeNotesByWeek(batch);
		for (int i = 1; i <= batch.getWeeks(); i++) {
			//grab the QCStatus map made by utilSeperateQCTraineeNotesByWeek for the week
			Map<QCStatus, Integer> weekStatusCount = results.get(i);
			for (QCStatus status : weekStatusCount.keySet()) {
				int expectCount = weekStatusCount.get(status);
				switch (status) {
				case Poor: 
					assertEquals(expectCount, statusPoorCountPerWeek[i - 1]);
					break;
				case Average: 
					assertEquals(expectCount, statusAverageCountPerWeek[i - 1]);
					break;
				case Good: 
					assertEquals(expectCount, statusGoodCountPerWeek[i - 1]);
					break;
				case Superstar: 
					assertEquals(expectCount, statusSuperstarCountPerWeek[i - 1]);
					break;
				default: 
					assertTrue("QCStatus " + status + "  not checked during test", false);
				}
			}
		}
		
		//Negative Testing
		try {
			reportingService.utilSeparateQCTraineeNotesByWeek(null);
		} catch (NullPointerException e) {
			log.debug(e);
		}
	}
	
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchWeekAvgBarChart(int batchId, int week)
	 */
	@Test
	public void getBatchWeekAvgBarChartTest() {
		log.info("Testing getBatchWeekAvgBarChartTest");
		Map <String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(2201, 5);

		assertTrue( (Double) Math.abs(weekAvgBarChart.get("Project")[0]- 88.75) < 0.001);
		assertTrue( (Double) Math.abs(weekAvgBarChart.get("Exam")[0]- 76.109375) < 0.001);
		assertTrue( (Double) Math.abs(weekAvgBarChart.get("Verbal")[0]- 74.375) < 0.001);
	}
	
	/**
	 * Tests method:
	 * com.revature.caliber.services.ReportingService.getBatchWeekSortedBarChart(int batchId, int week)
	 */
	@Test
	public void getBatchWeekSortedBarChartTest(){
		
		log.info("getBatchWeekSortedBarChartTest");
		Map<String, Double> test =reportingService.getBatchWeekSortedBarChart(2050, 2);
		assertTrue( (Double) Math.abs(test.get("Fouche, Issac")- 96.29) < 0.001);
		assertTrue( (Double) Math.abs(test.get("Castillo, Erika")- 89.63) < 0.001);
		assertEquals(6, test.size());
		
	}
	
	
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchWeekPieChart(Integer batchId, Integer weekNumber)
	 */
	@Test
	public void getBatchWeekPieChartTest() {
		log.info("Testing ReportingService.getBatchWeekPieChart");
		Map<QCStatus, Integer> pieChart = reportingService.getBatchWeekPieChart(2201, 7);
		
		for (QCStatus key : pieChart.keySet()) {
			
			log.info("key: "+ key+ " " + pieChart.get(key));
			
		}

		log.info("Testing getBatchWeekPieChart");
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Superstar));
		assertEquals( (Integer) 9, (Integer) pieChart.get(QCStatus.Good));
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Average));
		assertEquals( (Integer) 7, (Integer) pieChart.get(QCStatus.Poor));
		
	}
	
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.pieChartCurrentWeekQCStatus(Integer batchId)
	 */
	@Test
	public void pieChartCurrentWeekQCStatusTest() {
		log.info("Testing ReportingService.pieChartCurrentWeekQCStatus()");
		Integer batchId = 2201;
		Map<QCStatus, Integer> pieChart = reportingService.pieChartCurrentWeekQCStatus(batchId);
		
		for (QCStatus key : pieChart.keySet()) {
			log.info("key: "+ key+ " " + pieChart.get(key));	
		}
		
		assertNotNull(pieChart);
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Superstar));
		assertEquals( (Integer) 9, (Integer) pieChart.get(QCStatus.Good));
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Average));
		assertEquals( (Integer) 7, (Integer) pieChart.get(QCStatus.Poor));	
	}
	
	/**
	 *  Tests methods:
	 *  com.revature.caliber.services.ReportingService.getAllBatchesCurrentWeekQCStackedBarChart
	 */
	@Test
	public void getAllBatchesCurrentWeekQCStackedBarChartTest() {
		log.info("Testing ReportingService.getAllBatchesCurrentWeekQCStackedBarChar()");
		List<Object> object = reportingService.getAllBatchesCurrentWeekQCStackedBarChart();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> test = (Map<String, Object>) object.get(0);
		
		for (int i = 0; i < object.size(); i++) {
			log.info("Batch number " + i + ": " + object.get(i));	
		}

		// find a way to acquire the map separately, then iterate through its keys
		
		@SuppressWarnings("unchecked")
		Map<QCStatus, Integer> qcStatus = (Map<QCStatus, Integer>) test.get("qcStatus");
		
		//asserts batch ID
		assertEquals((Integer) 2201, (Integer) test.get("id"));
		
		//asserts QCStatus values for Poor, Good, Superstar, and Average
		assertEquals((Integer) 7, (Integer) qcStatus.get(QCStatus.Poor));
		assertEquals((Integer) 9, (Integer) qcStatus.get(QCStatus.Good));
		assertEquals((Integer) 0, (Integer) qcStatus.get(QCStatus.Superstar));
		assertEquals((Integer) 0, (Integer) qcStatus.get(QCStatus.Average));
		
		//asserts the label
		
		LocalDate expect = LocalDate.now();
		expect = expect.minusDays(7);
		
		assertEquals(expect + "...Patrick", (String) test.get("label"));
		
		//asserts the address
		Address address = (Address) test.get("address");
	
		assertEquals("65-30 Kissena Blvd, CEP Hall 2", address.getStreet());
			
	}
		
	/**
	 * Method for creating two batches with grades
	 * @return
	 */
	private static List<Batch> batchComparisonInit(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1, 1);
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 3, 1);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, 4, 1);
		
		Batch b1 = new Batch("1808-Java",new Trainer(),start.getTime(), end.getTime(), REVATURE);
		Batch b2 = new Batch("1909-Java",new Trainer(), start.getTime(), end.getTime(), REVATURE);

		b1.setWeeks(4);
		b2.setWeeks(4);
		
		Set<Trainee> trainees1 = new HashSet<>();
		Set<Trainee> trainees2 = new HashSet<>();
		
		int[] score1 = {70, 80, 80, 90};
		int[] score2 = {100, 90, 90, 80};
		

		for(int i = 1; i < 2; i++){
            Trainee trainee1 = new Trainee("Trainee1_" + i, "java", EMAIL, b1);
            Trainee trainee2 = new Trainee("Trainee2_" + i,".net",EMAIL,b2);

            trainee1.setTraineeId(i+100);
            trainee2.setTraineeId(i+200);
            
            Set<Grade> grades1 = new HashSet<>();
            Set<Grade> grades2 = new HashSet<>();
            for(int j = 1; j < 5; j++){
                Assessment weekB1 = new Assessment(TITLE + j, b1, 100, AssessmentType.Exam, j, new Category());
                Assessment weekB2 = new Assessment(TITLE + j, b2, 100, AssessmentType.Exam,j, new Category());
                grades1.add(new Grade(weekB1, trainee1, new Date(), score1[j-1]));
                grades2.add(new Grade(weekB2, trainee2, new Date(), score2[j-1]));
            }
            trainee1.setGrades(grades1);
            trainee2.setGrades(grades2);
            trainees1.add(trainee1);
            trainees2.add(trainee2);
        }
		
		b1.setTrainees(trainees1);
		b2.setTrainees(trainees2);
		
		List<Batch> batches = new ArrayList<>();
		batches.add(b1);
		batches.add(b2);
		return batches;
	}
	
	
	/**
	 * Utility method for testing Creates and returns a batch with 3 trainees
	 * with QC Notes and statuses for 7 weeks
	 * 
	 * @return Batch
	 */
	private Batch createTestBatchWithQCNotes() {
		Set<Trainee> traineesWithQC = new HashSet<>();
		Batch batch = new Batch();
		batch.setWeeks(7);
		for (int i = 1; i < 4; i++) {
			Trainee trainee = new Trainee("Trainee" + i, "java", EMAIL, batch);
			trainee.setTraineeId(i);
			Set<Note> notes = new HashSet<>();
			for (int j = 1; j < 8; j++) {
				Note note = new Note();
				note.setWeek((short) j);
				switch (j % 4) {
				case 0: 
					note.setQcStatus(QCStatus.Poor);
					break;
				case 1: 
					note.setQcStatus(QCStatus.Average);
					break;
				case 2: 
					note.setQcStatus(QCStatus.Good);
					break;
				case 3: 
					note.setQcStatus(QCStatus.Superstar);
					break;
				default:
				}
				notes.add(note);
			}
			trainee.setNotes(notes);
			traineesWithQC.add(trainee);
		}
		batch.setTrainees(traineesWithQC);
		return batch;
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAllCurrentBatchesLineChartConcurrent()
	 */
	@Test
	public void testGetAllCurrentBatchesLineChartConcurrent() {
		log.trace("Testing getAllCurrentBatchesLineChartConcurrent");

		final String key = "1702 Feb13 Java (AP)"; // name of batch
		
		// call service
		Map<String, Map<Integer, Double>> batches = reportingService.getAllCurrentBatchesLineChartConcurrent();

		log.debug(batches);

		// check that expected batch is there
		assertTrue(batches.containsKey(key));
		
		// check that batch has all expected week averages
		assertEquals(7, batches.get(key).size());
		
		// check that each week grade averages are what is expected
		assertEquals(68.34, batches.get(key).get(1), FLOATING_NUMBER_VARIANCE);
		assertEquals(84.96, batches.get(key).get(2), FLOATING_NUMBER_VARIANCE);
		assertEquals(76.83, batches.get(key).get(3), FLOATING_NUMBER_VARIANCE);
		assertEquals(75.09, batches.get(key).get(4), FLOATING_NUMBER_VARIANCE);
		assertEquals(77.94, batches.get(key).get(5), FLOATING_NUMBER_VARIANCE);
		assertEquals(82.80, batches.get(key).get(6), FLOATING_NUMBER_VARIANCE);
		assertEquals(74.27, batches.get(key).get(7), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekRadarChart(Integer,
	 *      Integer)
	 */
	@Test
	public void testGetTraineeUpToWeekRadarChart() {
		
		final String spring = "Spring";
		final String hibernate = "Hibernate";
		log.trace("Testing getTraineeUpToWeekRadarChart");

		// call service
		Map<String, Double> traineeSkills = reportingService.getTraineeUpToWeekRadarChart(TEST_TRAINEE_ID, TEST_ASSESSMENT_WEEK);
		
		// check that trainee has all expected skills
		assertEquals(6, traineeSkills.size());
		
		// check that each expected skill is there and has expected average
		assertEquals(82.92, traineeSkills.get(hibernate), FLOATING_NUMBER_VARIANCE);
		assertEquals(80.40, traineeSkills.get("JSP"), FLOATING_NUMBER_VARIANCE);
		assertEquals(67.79, traineeSkills.get("Java"), FLOATING_NUMBER_VARIANCE);
		assertEquals(93.10, traineeSkills.get("JavaScript"), FLOATING_NUMBER_VARIANCE);
		assertEquals(91.55, traineeSkills.get("SQL"), FLOATING_NUMBER_VARIANCE);
		assertEquals(79.20, traineeSkills.get(spring), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallRadarChart(Integer)
	 */
	@Test
	public void testGetTraineeOverallRadarChart() {
		final String spring = "Spring";
		final String hibernate = "Hibernate";
		log.trace("Testing getTraineeOverallRadarChart");

		// call service
		Map<String, Double> traineeSkills = reportingService.getTraineeOverallRadarChart(TEST_TRAINEE_ID);
		
		// check that trainee has all expected skills
		assertEquals(7, traineeSkills.size());
		
		// check that each expected skill is there and has expected average
		assertEquals(82.92, traineeSkills.get(hibernate), FLOATING_NUMBER_VARIANCE);
		assertEquals(80.40, traineeSkills.get("JSP"), FLOATING_NUMBER_VARIANCE);
		assertEquals(67.79, traineeSkills.get("Java"), FLOATING_NUMBER_VARIANCE);
		assertEquals(93.10, traineeSkills.get("JavaScript"), FLOATING_NUMBER_VARIANCE);
		assertEquals(91.55, traineeSkills.get("SQL"), FLOATING_NUMBER_VARIANCE);
		assertEquals(79.20, traineeSkills.get(spring), FLOATING_NUMBER_VARIANCE);
		assertEquals(83.60, traineeSkills.get("REST"), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekLineChart(int,
	 *      int, int)
	 */
	@Test
	public void getTraineeUpToWeekLinechart() {
		log.trace("getTraineeUpToWeekLinechart");
		
		/*
		 * Method description: input: batch, week, and trainee output: map of
		 * week and scores
		 */

		// get chart data which should be a map of week and scores
		Map<Integer, Double[]> averageGrades = reportingService.getTraineeUpToWeekLineChart(TEST_BATCH_ID, TEST_ASSESSMENT_WEEK, TEST_TRAINEE_ID);

		// size should be 6 for week 6
		assertEquals(TEST_ASSESSMENT_WEEK, averageGrades.size());

		// equal to data from database
		assertEquals(72.74, averageGrades.get(1)[0], FLOATING_NUMBER_VARIANCE);
		assertEquals(75.04, averageGrades.get(6)[0], FLOATING_NUMBER_VARIANCE);

		// week 7 should not exist
		assertNull(averageGrades.get(7));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallLineChart(int,
	 *      int)
	 */
	@Test
	public void getTraineeOverallLineChart() {
		log.trace("getTraineeOverallLineChart");
		
		/*
		 * Method description: input: batch, and trainee output: map of week and
		 * double array for average with set size of 2 [0: trainee, 1: batch]
		 */

		Map<Integer, Double[]> overallGrades = reportingService.getTraineeOverallLineChart(TEST_BATCH_ID, TEST_TRAINEE_ID);

		// batch had 7 weeks total
		assertEquals(7, overallGrades.size());

		// scores should only have 2 values
		Double[] weekAverage = overallGrades.get(7);
		assertEquals(2, weekAverage.length);

		// week 8 should not exist
		assertNull(overallGrades.get(8));
	}

	/**
	 * Tests methods:getBatchOverallRadarChart testing batch 2150 and 2200 spot
	 * testing average of category for batch
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchOverallRadarChart(List<Grade>
	 *      grades)
	 */
	@Test
	public void getBatchOverallRadarChart() {
		log.trace("getBatchOverallRadarChart");
		
		Map<String, Double> skills = reportingService.getBatchOverallRadarChart(TEST_BATCH_ID);
		assertEquals(7, skills.size());
		assertEquals(76.70, skills.get("Java"), FLOATING_NUMBER_VARIANCE);
		assertEquals(89.74, skills.get("Hibernate"), FLOATING_NUMBER_VARIANCE);

		skills = reportingService.getBatchOverallRadarChart(TEST_BATCH_ID2);
		assertEquals(10, skills.size());
		assertEquals(77.88, skills.get("JDBC"), FLOATING_NUMBER_VARIANCE);
		assertEquals(89.52, skills.get("Spring"), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchOverallLineChart(int)
	 */
	@Test
	public void testGetBatchOverallLineChart() {
		log.trace("testGetBatchOverallLineChart");
		
		/*
		 * Method description: input: batchId output: map of week and scores
		 */

		Map<Integer, Double> map = reportingService.getBatchOverallLineChart(TEST_BATCH_ID);

		// batch had 7 weeks total
		assertEquals(7, map.size());

		// grades are equal
		assertEquals(map.get(1), 80.26, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(2), 92.69, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(3), 86.66, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(4), 84.79, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(5), 87.84, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(6), 84.93, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(7), 83.27, FLOATING_NUMBER_VARIANCE);

		// week 8 should not exist
		assertNull(map.get(8));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAllCurrentBatchesLineChart()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllCurrentBatchesLineChart() {
		log.trace("testGetAllCurrentBatchesLineChart");
		
		/*
		 * Method description: output: map of current batch and its respective
		 * data of (address, label of start date & trainer, and list of grades)
		 */
		List<Object> results = reportingService.getAllCurrentBatchesLineChart();

		for (Object num : results) {
			Map<String, List<String>> data = (Map<String, List<String>>) num;

			// current batch keys should have address, label and grades
			assertTrue(data.containsKey("address"));
			assertTrue(data.containsKey("label"));
			assertTrue(data.containsKey("grades"));

		}
		// current batch data should have 3
		assertTrue(results.size() == 3);
	}

	/**
	 * Tests getBatchAllTraineesOverallRadarChart testing batch 2150 and 2200
	 * spot testing average students' category
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchAllTraineesOverallRadarChart
	 */
	@Test
	public void getBatchAllTraineesOverallRadarChart() {
		log.trace("getBatchAllTraineesOverallRadarChart");
		
		Map<String, Map<String, Double>> skills = reportingService.getBatchAllTraineesOverallRadarChart(TEST_BATCH_ID);
		
		assertEquals(13, skills.size());
		assertEquals(91.55, skills.get("Erwin, Eric").get("SQL"), FLOATING_NUMBER_VARIANCE);
		assertEquals(84.16, skills.get("Michels, Alex").get("Hibernate"), FLOATING_NUMBER_VARIANCE);

		skills = reportingService.getBatchAllTraineesOverallRadarChart(TEST_BATCH_ID2);
		assertEquals(15, skills.size());
		assertEquals(84.95, skills.get("Lau, Samuel").get("SOAP"), FLOATING_NUMBER_VARIANCE);
		assertEquals(78.17, skills.get("Sibrian, David").get("REST"), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAvgBatchWeekValue(Integer,
	 *      Integer)
	 */
	@Test
	public void testGetAvgBatchWeekValue() {
		log.trace("testGetAvgBatchWeekValue");
		
		/*
		 * Method description: input: batchId, and week output: average batch
		 * week 6 value
		 */

		Double avgBatchWeek6Value = new Double(reportingService.getAvgBatchWeekValue(TEST_BATCH_ID, TEST_ASSESSMENT_WEEK));

		assertEquals(avgBatchWeek6Value, 84.93, FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTechnologiesForTheWeek(Integer,
	 *      Integer)
	 */
	@Test
	public void testGetTechnologiesForTheWeek() {
		log.trace("testGetTechnologiesForTheWeek");
		
		/*
		 * Method description: input: batchId, and week output: List of
		 * technologies
		 */

		Set<String> technologies = reportingService.getTechnologiesForTheWeek(TEST_BATCH_ID, TEST_ASSESSMENT_WEEK);

		// One technologies in the set
		assertTrue(technologies.size() == 1);

		// Set of technologies should contain Spring
		assertTrue(technologies.contains("Spring"));

		// Set of technologies should not contain Java
		assertFalse(technologies.contains("Java"));
	}
}
