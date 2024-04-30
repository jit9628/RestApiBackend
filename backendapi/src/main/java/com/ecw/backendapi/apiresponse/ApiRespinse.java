package com.ecw.backendapi.apiresponse;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiRespinse {

	public ResponseEntity<?> response(Object body, String message, HttpStatus httpStatus) {

		return new ResponseEntity<>(Map.of("body", body, "message", message), httpStatus);
	}

}
