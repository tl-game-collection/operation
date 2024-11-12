package com.softeem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.softeem.mappers.room.ServiceFeeMapper;
import com.softeem.model.room.ServiceFee;
import com.softeem.services.ServiceFeeService;

@Service
public class ServiceFeeServiceImpl implements ServiceFeeService  {
	@Autowired
	private ServiceFeeMapper  serviceFeeMapper; 


	@Override
	public List<ServiceFee> ListServiceFee(ServiceFee fee) {
		return serviceFeeMapper.ListServiceFee(fee);
	}

	@Override
	public List<ServiceFee> ListServiceFeeDetil(ServiceFee fee) {
		return serviceFeeMapper.ListServiceFeeDetil(fee);
	}

}
