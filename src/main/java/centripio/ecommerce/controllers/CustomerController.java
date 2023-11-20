package centripio.ecommerce.controllers;

import java.util.List;

import centripio.ecommerce.entity.Customer;

@Path("Customer")
@Stateless
public class CustomerController {

	@PersistentContext(unitName = "ecommerce-jpa")
	private EntityManager manager;

	@GET
	public Response findAll() {
		List<Customer> customers = manager.createQuery("select e from Customer e", Customer.class)
				.getResultList();
		
		return Response.ok(customers).build();
	}

	
	//customers/{cutomerId}
	//customers/1
	@GET
	@Path("{cutomerId}")
	public Response findById(@PathParam(cutomerId) Long id) {
		Customer customer = manager.find(Customer.class, id);
		return Response.ok(customer).build();
	}
	
	@POST
	public Response create(Customer customer) {
		manager.persist(customer);
		return Response.ok(customer).build();
	}
	
	@POST
	public Response update(Customer customer) {
		Customer mergeCustomer = manager.merge(customer);
		return Response.ok(mergeCustomer).build();
	}
	
	@DELETE
	@Path("{cutomerId}")
	public Response delete(@PathParam(cutomerId) Long id) {
		manager.createQuery("delete Customer c where c.id = :cutomerId")
			.setParameter("cutomerId", cutomerId)
			.executeUpdate();
		return Response.ok().build();
	}
}
