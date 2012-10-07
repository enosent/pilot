package com.ctb.pilot.stat.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.ctb.pilot.stat.model.DailyVisitIpLog;
import com.ctb.pilot.stat.model.DailyVisitLog;
import com.ctb.pilot.stat.model.DailyVisitUriLog;
import com.ctb.pilot.stat.model.VisitLog;

public interface VisitLogMapper {

	void insertVisitLog(VisitLog visitLog);

	List<DailyVisitLog> getDailyVisitLogs();

	List<DailyVisitIpLog> getDailyVisitIpLogs(String day);

	List<DailyVisitUriLog> getDailyVisitUriLogs(String day);

	Map<String, Long> getVisitTodayStatByIP();

}