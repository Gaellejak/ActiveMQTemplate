package receiver;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			QueueConnection queueConnection = factory.createQueueConnection();
			// Open a session	
			QueueSession session = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE); 
			// start the connection	
			queueConnection.start();
			// Create a receive	
			QueueReceiver receiver = session.createReceiver(queue); 
			// Receive the message
			Message m = receiver.receive();
			
			System.out.println(m + "rrrrrrrrrr\n\n\n\n\n\n\n\n\n");
			//session.close();
			//queueConnection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
