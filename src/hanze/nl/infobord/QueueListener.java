package hanze.nl.infobord;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import javax.jms.*;

public class QueueListener implements MessageListener {
    private static InfoBord infoBord;
  public QueueListener() {
    infoBord = InfoBord.getInfoBord();

  }

  @Override
  public void onMessage(Message message) {
      try{
          if (message instanceof TextMessage) {
              System.out.println(message.getStringProperty("RICHTING"));
              TextMessage textMessage = (TextMessage) message;
              String text = textMessage.getText();
              infoBord.verwerkBericht(text);
              infoBord.setRegels();

          }
      }
      catch (JMSException e) {
          e.printStackTrace();
      }
  }
}