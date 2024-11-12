package com.softeem.mappers.room;

import java.util.List;

import com.softeem.model.room.ServiceFee;

public interface ServiceFeeMapper {
	
	List<ServiceFee> ListServiceFee(ServiceFee fee);
	
	
	List<ServiceFee> ListServiceFeeDetil(ServiceFee fee);
	
	
}
