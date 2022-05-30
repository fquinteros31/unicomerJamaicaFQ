package com.unicomer.jamaica.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApiResponseMessagesList {
	
	private int status;
    private List<String> message;

    public ApiResponseMessagesList(int status, List<String> messages) {
        super();
        this.status = status;
        this.message = messages;
    }

    public ApiResponseMessagesList(int status, String message) {
        super();
        this.status = status;
        this.message = Arrays.asList(message);
    }

}
