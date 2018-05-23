/**
 * 
 */
package ca.cihi.oi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author SSeetharaman
 *
 */
@RestController
//public class OrderRestfulController extends BaseController{
public class OrderRestfulController {
	private static final Log LOGGER = LogFactory.getLog(OrderRestfulController.class);




	@RequestMapping(value="/rest/orders", method=RequestMethod.GET)
	public String getAllOrders(@RequestParam(value="orderId",required=false)String orderId) throws JsonProcessingException
	{
		LOGGER.debug("orderId "+orderId);		
		List<Order> orders=getAllOrders();
		ObjectMapper objectMapper=new ObjectMapper();
		String output=objectMapper.writeValueAsString(orders);

		return output;

	}

	@RequestMapping(value="/rest/orders/{orderId}", method=RequestMethod.GET)
	private String getSpecificOrder(@PathVariable("orderId") String orderId) throws JsonProcessingException
	{
		ObjectMapper objectMapper=new ObjectMapper();
		Long oId=Long.parseLong(orderId);
		Order o= findOrder(oId);
		String output=objectMapper.writeValueAsString(o);
		return output;
	}


/*
 * Temp Utility methods. Change after creating Service and DAO layers.
 */
	private Order findOrder(Long inputOrderId)
	{
		List<Order> orders=getAllOrders();
		for(Order o:orders)
		{
			if(o.getId().equals(inputOrderId))
			{
				return o;
			}
		}
		return null;
	}

	private List<Order> getAllOrders()
	{
		Order order1=new Order();
		order1.setId(1001L);
		order1.setDescription("first order");
		order1.setCost(250);
		order1.setIsCompleted(true);

		Order order2=new Order();
		order2.setId(1002L);
		order2.setDescription("second order");
		order2.setCost(350);
		order2.setIsCompleted(false);

		Order order3=new Order();
		order3.setId(1003L);
		order3.setDescription("third order");
		order3.setCost(450);
		order3.setIsCompleted(true);

		Order order4=new Order();
		order4.setId(1004L);
		order4.setDescription("last order");
		order4.setCost(1250);
		order4.setIsCompleted(false);
		
		Order order5=new Order();
		order4.setId(1005L);
		order4.setDescription("new order");
		order4.setCost(1050);
		order4.setIsCompleted(false);


		List<Order> orderList=new ArrayList<Order>();
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		orderList.add(order4);

		return orderList;
	}


}