package receiver;

import javax.jms.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class receivertopic {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Topic topic = (Topic) applicationContext.getBean("topic");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			TopicConnection topicConnection = factory.createTopicConnection();
			// Open a session	
			TopicSession session = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE); 
			// start the connection	
			topicConnection.start();
			// Create a receive	
			TopicSubscriber subscriber = session.createSubscriber(topic); 
			// Receive the message
			Message m = subscriber.receive();
			
			// TopicConnection connection = factory.createTopicConnection();
			//TopicSubscriber subscriber=session.createSubscriber();
			//connection.start();
			//Message m =subscriber.receive();
			System.out.println(m + "rrrrrrrrrr");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}