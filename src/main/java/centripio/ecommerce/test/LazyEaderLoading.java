package centripio.ecommerce.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Order;

public class LazyEaderLoading {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		Order order = em.find(Order.class, 1L);
		System.out.println(order.getCustomer().getFistname());
		
		order.getLines().get(0);
	}
}
