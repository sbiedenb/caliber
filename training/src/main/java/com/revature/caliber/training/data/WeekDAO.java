package com.revature.caliber.training.data;

import java.util.List;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.beans.Week;

public interface WeekDAO {
	
	List<Week> getAllWeek();
	Week getWeekById(long id);
	List<Week> getWeekByWeekNumber(long id);
	List<Week> getWeekByTopic(Category topic); 
}
