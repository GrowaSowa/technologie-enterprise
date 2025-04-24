package lab.ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@JMSDestinationDefinition(name = "java:app/jms/NewsQueue",
        interfaceName = "jakarta.jms.Queue", resourceAdapter = "jmsra",
        destinationName = "NewsQueue")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements jakarta.jms.MessageListener {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        //ObjectMessage msg = null;
        TextMessage msg = null;
        try {
//            if (message instanceof ObjectMessage) {
//                msg = (ObjectMessage) message;
//                NewsItem e = (NewsItem) msg.getObject();
//                em.persist(e);
//            }
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                String[] s = msg.getText().split("\\|");
                NewsItem e = new NewsItem();
                e.setHeading(s[0]);
                e.setBody(s[1]);
                em.persist(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
