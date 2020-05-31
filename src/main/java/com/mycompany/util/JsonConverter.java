package com.mycompany.util;

import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConverter {

	@Autowired
	private ObjectMapper objectMapper;

	public String convertToJson(List<Object> objects) throws Exception {
		StringWriter stringEmp = new StringWriter();
		this.objectMapper.writeValue(stringEmp, objects);
		return stringEmp.toString();
	}

	public String convertToJson(Object object) throws Exception {
		StringWriter stringEmp = new StringWriter();
		this.objectMapper.writeValue(stringEmp, object);
		return stringEmp.toString();
	}
}