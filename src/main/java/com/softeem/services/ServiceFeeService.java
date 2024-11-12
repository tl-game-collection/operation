package com.softeem.services;

import java.util.List;

import com.softeem.model.room.ServiceFee;

public interface ServiceFeeService {
List<ServiceFee> ListServiceFee(ServiceFee fee);
	
	
	List<ServiceFee> ListServiceFeeDetil(ServiceFee fee);

}
