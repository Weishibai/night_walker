package com.sunshine.admin.service;

import java.util.List;

import com.sunshine.common.vo.OpLog;

public interface IOpService {

	public void writeLog(OpLog opLog);

	public List<OpLog> queryLog();
}
