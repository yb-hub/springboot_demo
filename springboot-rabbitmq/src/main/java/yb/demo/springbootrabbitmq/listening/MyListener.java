package yb.demo.springbootrabbitmq.listening;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    @RabbitListener(queues = {"queue1","queue2"})
    public void receive(Object obj){
//        if(obj instanceof Message){
//            byte[] body = ((Message) obj).getBody();
//            MessageProperties messageProperties = ((Message) obj).getMessageProperties();
//            System.out.println(body);
//            System.out.println(messageProperties);
//        }
        System.out.println(obj);
    }
}
