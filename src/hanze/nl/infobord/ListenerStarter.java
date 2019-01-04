package hanze.nl.infobord;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public  class ListenerStarter implements Runnable, ExceptionListener {
  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  private String selector;

  public ListenerStarter(String selector) {
    this.selector = selector;
  }

  @Override
  public void run() {
      try {

          // Create a ConnectionFactory
          ActiveMQConnectionFactory connectionFactory =
                  new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

          // Create a Connection
          Connection connection = connectionFactory.createConnection();
          connection.start();

          connection.setExceptionListener(this);

          // Create a Session
          Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

          // Create the destination (Topic or Queue)
          Destination destination = session.createTopic("BusTopic");

          // Create a MessageConsumer from the Session to the Topic or Queue
          MessageConsumer consumer = session.createConsumer(destination, selector);
          consumer.setMessageListener(new QueueListener());
//          consumer.close();
//          session.close();
//          connection.close();
      } catch (Exception e) {
          System.out.println("Caught: " + e);
          e.printStackTrace();
      }
  }

  @Override
  public void onException(JMSException e) {

  }
}