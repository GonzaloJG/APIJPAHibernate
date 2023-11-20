package centripio.ecommerce.controllers;

@Path("hello")
public class HelloworldController {

	@GET
	public String hello() {
		return "Hello World";
	}
}
