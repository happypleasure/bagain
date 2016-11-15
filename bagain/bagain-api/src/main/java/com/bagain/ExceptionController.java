package com.bagain;

import org.bagain.utils.JSONMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

	@GetMapping("/error")
	public JSONMessage error(){
		return JSONMessage.failure("服务繁忙");
	}
}
