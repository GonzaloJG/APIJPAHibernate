package centripio.ecommerce.controllers;

import java.util.List;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.Order;

@Path("orders")
public class OrderController {

	@PersistenceContext(unitName = "ecommerce-jpa")
	private EntityManager manager;
	
	
	@GET
	public Response findAll() {
		List<Order> orders = manager.createQuery("select e fromo Order e", Order.class)
				.getResultList();
		
		orders.forEach(x -> {
			x.getLines().forEach(l -> {
				l.setOrder(null);
			});
		});
		
		return Response.ok(orders).build();
	}
	
	@GET
	@Path("{orderId}")
	public Response findById(PathParam("orderId") Long orderId) {
		Order order = manager.find(Order.class, orderId);
		
		order.getLines().forEach(l-> {
			l.setOrder(null);
		});
		
		return Response.ok(order).build();
	}
	
	@POST
	public Response create(Order order) {
		
		Customer customer = manager.find(Customer.class, order.getCustomer().getId());
		order.setCustomer(customer);
		
		manager.persist(order);
		manager.flash();
		
		return Response.ok(order).build();
	}
	
	@POST
	public Response update(Order order) {
		Order order = manager.merge(order);
		return Response.ok(order).build();
	}
	
	@DELETE
	@Path("{orderId}")
	public Response delete(PathParam("orderId") Long orderId) {
		manager.createQuery("delete from Order o where o.id = :orderId")
			.setParameter("orderId", orderId)
			.executeUpdate();
		
		return Response.ok().build();
	}
}
