package sender;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			QueueConnection queueConnection = factory.createQueueConnection();
			// Open a session without transaction and acknowledge automatic
			QueueSession session = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE); 
			// Start the connection
			queueConnection.start();
			// Create a sender
			QueueSender sender = session.createSender(queue); 
			// Create a message
			TextMessage message =session.createTextMessage("Hello message broker\n\n\n\n\n\n\n\ndddddddddddddddddddddd");
			// Send the message
			sender.send(message);
			// Close the session
			session.close();
			
			// Close the connection
			queueConnection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
