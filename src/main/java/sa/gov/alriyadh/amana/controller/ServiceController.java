package sa.gov.alriyadh.amana.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.reactive.function.client.WebClient;
import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.srinterface.IJobRequestService;

@RestController
@RequestMapping("/api/serviceController")
@Validated
public class ServiceController {

	@Autowired
	IJobRequestService jobRequestService;

	//WebClient.Builder builder = WebClient.builder();
	@Autowired
	private WebClient webClient;

	@GetMapping("/testGenericResponse")
	public GenericApiResponse<?> testGenericResponse(@NotBlank(message = "{NO_DATA_FOUND}") @RequestParam String text){
		return GenericApiResponse.returnJsonTemp("0",null,"testtttt");
	}


}
