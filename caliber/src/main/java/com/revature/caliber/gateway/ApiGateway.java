package com.revature.caliber.gateway;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.BatchNote;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.QCNote;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerNote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gathers data from appropriate services and
 * combines the model to deliver to the view.
 * <p>
 * Reduces complexity compared to calling each
 * service individually throughout the application.
 */
public interface ApiGateway {

    /**
     * Create a new batch
     *
     * @param batch - A new batch
     */
    void createBatch(Batch batch);

    /**
     * Get all batches
     *
     * @return All batches
     */
    List<Batch> allBatch();

    /**
     * Get all Batches for a given Trainer.
     *
     * @param id - The trainer
     * @return All batches for a given trainer
     */
    List<Batch> getBatches(Integer id);

    /**
     * Get all current Batches
     *
     * @return All batches
     */
    List<Batch> currentBatch();

    /**
     * Get all current Batches for a given Trainer
     *
     * @param trainer - The trainer for whom we are returning the current batch
     * @return A list of batches
     */
    List<Batch> currentBatch(Integer id);

    /**
     * Get a batch by ID
     *
     * @param id The id of the batch
     * @return A batch that corresponds with the id provided
     */
    Batch getBatch(Integer id);

    /**
     * Update a Batch
     *
     * @param batch The batch to be updated
     */
    void updateBatch(Batch batch);

    /**
     * Delete a Batch
     *
     * @param batch The batch to be deleted
     */
    void deleteBatch(Batch batch);

    //Trainee

    /**
     * Creates new trainee
     *
     * @param trainee trainee to create
     */
    void createTrainee(Trainee trainee);

    /**
     * Update trainee's info
     *
     * @param trainee trainee to update (with new info)
     */
    void updateTrainee(Trainee trainee);

    /**
     * Get trainee by id.
     *
     * @param id id of trainee to get
     * @return Trainee object, or null if trainee with id doesn't exist
     */
    Trainee getTrainee(Integer id);

    /**
     * Get trainee by full name
     *
     * @param email email of trainee to get (conversions needed (server side restrictions): '@' -> "%40", '.' -> "_dot_")
     * @return Trainee object or null if trainee with name doesn't exist
     */
    Trainee getTrainee(String email);

    /**
     * Get list of trainees for a certain batch
     *
     * @param batchId id of the batch
     * @return list of trainees or an empty list if there is no batch (null?)
     */
    List<Trainee> getTraineesInBatch(Integer batchId);

    /**
     * Delete a trainee
     *
     * @param trainee trainee to delete
     */
    void deleteTrainee(Trainee trainee);

    //end of Trainee Service

    //Trainer

    /**
     * Creates new trainer
     *
     * @param trainer trainer to create
     */
    void createTrainer(Trainer trainer);

    /**
     * Gets a trainer by id
     *
     * @param id : id of the trainer
     * @return Trainer object
     */
    Trainer getTrainer(Integer id);

    /**
     * Gets a trainer by email
     *
     * @param email : email of the trainer
     * @return Trainer object
     */
    Trainer getTrainer(String email);

    /**
     * Gets a list of all trainers
     *
     * @return a list of Trainer objects
     */
    List<Trainer> getAllTrainers();

    /**
     * Updates a trainer
     *
     * @param trainer : the trainer object to update
     */
    void updateTrainer(Trainer trainer);



    //End of Trainer Service

    /**
     * retreive tech grade for a trainee with AVG , MEDIAN , MAX , MIN
     * @param id
     * @return
     */
	HashMap<String, Double[]> getTechGradeDataForTrainee(int id);

    HashMap<String, Double[]> getWeekGradeDataForTrainee(int id);
    
    HashMap<String, Double[]> getTechGradeDataForBatch(int batchId);
    
    Map<String, Double[]> getTraineeGradeDataForTrainer(int trainerId);
    //Grade Service
    /**
     * Retrieves the Grade using the assessmentId
     * @param assessmentId
     * @return
     */
    List<Grade> getGradesByAssessment(Integer assessmentId);
    
    /**
     * Inserts a grade
     * @param grade
     */
    void insertGrade(Grade grade);
    
    /**
     * Updates a grade
     * @param grade
     */
    void updateGrade(Grade grade);
    
    /**
     * Create a Trainer Note
     * @param note
     */
    //End of grade Service
    
    //TrainerNoteService
    void createTrainerNote(TrainerNote note);
    
    /**
     * Update the Trainer Note
     * @param note
     */
    void updateTrainerNote(TrainerNote note);
    
    /**
     * Delete the Trainer Note
     * @param note
     */
    void deleteTrainerNote(TrainerNote note);
    //End of trainer note service
    
    //Batch note service
    /**
     * Create a Batch Note
<<<<<<< HEAD
     * @param batchNote
=======
>>>>>>> e460ca5859d751280cb56d2dce02fe703fcea9b0
     */
    void createBatchNote(BatchNote batchNote);
    
    /**
     * Update the Batch Note
<<<<<<< HEAD
     * @param batchNote
=======
>>>>>>> e460ca5859d751280cb56d2dce02fe703fcea9b0
     */
    void updateBatchNote(BatchNote batchNote);
    
    /**
     * Delete the Batch Note
<<<<<<< HEAD
     * @param batchNote
=======
>>>>>>> e460ca5859d751280cb56d2dce02fe703fcea9b0
     */
    void deleteBatchNote(BatchNote batchNote);
    //End of batch note service
    
    //Assessment service
    /**
     * Add an assessment
     * @param assessment
     */
    void insertAssessment(Assessment assessment);
    
    /**
     * update an assessment
     * @param assessment
     */
    void updateAssessment(Assessment assessment);
    
    /**
     * Delete an assessment
     * @param assessment
     */
    void deleteAssessment(Assessment assessment);
    
    void createQCNote(QCNote note);
    
    void updateQCNote(QCNote note);

    HashMap<String, Double[]> getGradesForBatchWeekly(int batchID);


}
