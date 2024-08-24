package com.counsellorportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.counsellorportal.entites.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{

	// QBE - QueryByExample (Dynamic Query Preparation)
	// When we don't know how and which filter user is going to apply we will use QBE
	
	
	// Anotation for custom query to fetch all the enqs by the specific counsellor
	@Query(value = "select * from enquiry_tbl where counsellorId = :counsellorId", nativeQuery = true)
	public List<Enquiry> getEnquiriesByCounsellorId(Integer counsellorId);
}
