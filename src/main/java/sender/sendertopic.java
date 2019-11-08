package sender;

import javax.jms.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class sendertopic {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Topic topic = (Topic) applicationContext.getBean("topic");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			TopicConnection topicConnection = factory.createTopicConnection();
			// Open a session without transaction and acknowledge automatic
			TopicSession session = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE); 
			// Start the connection
			topicConnection.start();
			// Create a sender
			TopicPublisher sender = session.createPublisher(topic); 
			// Create a message
			TextMessage message =session.createTextMessage("Hello message broker");
			// Send the message
			sender.send(message);
			// Close the session
			session.close();
			
			// Close the connection
			topicConnection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
