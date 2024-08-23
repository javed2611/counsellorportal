package com.counsellorportal.service;

import com.counsellorportal.dto.DashboardResponse;
import com.counsellorportal.entites.Counsellor;

public interface CounsellorService {

	public boolean register(Counsellor counsellor);

	public Counsellor login(String email, String password);

	public DashboardResponse getDashBoard(Integer counsellorId);

	public Counsellor findByEmail(String email);
}
