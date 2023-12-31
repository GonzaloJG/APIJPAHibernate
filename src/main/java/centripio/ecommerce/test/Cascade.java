package centripio.ecommerce.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.Order;
import centripio.ecommerce.entity.OrderLine;
import centripio.ecommerce.entity.Payment;
import centripio.ecommerce.entity.Product;
import centripio.ecommerce.enums.PayMethod;

public class Cascade {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		/*
		em.getTransaction().begin();
		
		Payment payment = new Payment();
		payment.setAmount(100d);
		payment.setPayMethod(PayMethod.CASH);
		
		Customer customer1 = em.find(Customer.class, 1L);
		
		Order order = new Order();
		order.setCustomer(customer1);
		order.setPayment(payment);
		
		Product product1 = em.find(Product.class, 1L);
		
		for (int c=0; c<10; c++) {
			OrderLine line = new OrderLine();
			line.setProduct(product1);
			line.setQuantity(c+1d);
			line.setUnitPrice(product1.getPrice());
			order.addLines(line);
		}
		
		em.persist(order);
		
		em.getTransaction().commit();
		*/
		
		em.getTransaction().begin();
		
		Order order1 = em.find(Order.class, 1L);
		em.remove(order1);
		
		em.getTransaction().commit();
	}
}
