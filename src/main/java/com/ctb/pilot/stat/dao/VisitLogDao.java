package com.ctb.pilot.stat.dao;

import java.util.List;
import java.util.Map;

import com.ctb.pilot.stat.model.DailyVisitLog;
import com.ctb.pilot.stat.model.VisitLog;

public interface VisitLogDao {

	void insertVisitLog(VisitLog visitLog);

	List<DailyVisitLog> getDailyVisitLogs();

	Map<String, Long> getDailyVisitIpLogs();

}
