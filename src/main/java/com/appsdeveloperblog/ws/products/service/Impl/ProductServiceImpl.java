package com.appsdeveloperblog.ws.products.service.Impl;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.Uuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.ws.products.model.Product;
import com.appsdeveloperblog.ws.products.service.ProductService;
import com.appsdeveloperblog.ws.shared.event.ProductCreationEvent;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private KafkaTemplate<String, ProductCreationEvent> kafkaTemplate;

	public ProductServiceImpl(KafkaTemplate<String, ProductCreationEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String createProduct(Product product) {
		String productId = Uuid.randomUuid().toString();

		// TODO: Persist the product
		ProductCreationEvent event = new ProductCreationEvent(productId, product.getName(), product.getPrice(),
				product.getQuantity());
//		CompletableFuture<SendResult<String, ProductCreationEvent>> future = kafkaTemplate.send("product-creation-event-topic", productId, event);
		
//		future.whenComplete((result,error)-> {
//			if(result != null) {
//				logger.info("***** Message persisted in kafka");
//			} else {
//				logger.error("***** Message dint reach kafka");
//			}
//		}); //Async call without join
		
//		future.join(); //sychronously call
		
		
		/*Synchronous call*/
		
		try {
			SendResult<String, ProductCreationEvent> result = kafkaTemplate.send("product-creation-event-topic", productId, event).get();
			logger.info("***** Message persisted in kafka");
		} catch (InterruptedException e) {
			logger.error("***** Message dint reach kafka: {}" , e.getMessage());
			e.printStackTrace();
		} catch (ExecutionException e) {
			logger.error("***** Message dint reach kafka: {}" , e.getMessage());
		}
		
		
		logger.info("***** ProductId is returned");
		return productId;
	}

}
