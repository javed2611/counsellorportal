package com.counsellorportal.service;

import java.util.List;

import com.counsellorportal.dto.ViewEnqFilterReq;
import com.counsellorportal.entites.Enquiry;

public interface EnquiryService {
	public boolean saveEnq(Enquiry enq, Integer counsellorId) throws Exception;

	public List<Enquiry> getAllEnq(Integer counsellorId);

	public Enquiry getEnquiryById(Integer enqId);

	public List<Enquiry> getEnqsWithFil(ViewEnqFilterReq vr, Integer counsellorId);
}
