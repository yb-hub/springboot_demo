package yb.demo.springbootrabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private AmqpAdmin admin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void createExchange(){
        admin.declareExchange(new DirectExchange("DirectExchange"));
        admin.declareExchange(new FanoutExchange("FanoutExchange"));
        admin.declareExchange(new TopicExchange("TopicExchange"));
    }
    @Test
    public void creatQueue(){
        admin.declareQueue(new Queue("queue1",true));
        admin.declareQueue(new Queue("queue2",true));
        admin.declareQueue(new Queue("queue3",true));
        admin.declareQueue(new Queue("queue4",true));
        admin.declareQueue(new Queue("queue5",true));
        admin.declareQueue(new Queue("queue6",true));
    }
    @Test
    public void creatBinding(){
        admin.declareBinding(new Binding("queue1", Binding.DestinationType.QUEUE, "DirectExchange","directQueue1",null));
        admin.declareBinding(new Binding("queue2", Binding.DestinationType.QUEUE, "FanoutExchange","fanoutQueue2",null));
        admin.declareBinding(new Binding("queue3", Binding.DestinationType.QUEUE, "FanoutExchange","fanoutQueue3",null));
        admin.declareBinding(new Binding("queue4", Binding.DestinationType.QUEUE, "FanoutExchange","fanoutQueue4",null));
        admin.declareBinding(new Binding("queue5", Binding.DestinationType.QUEUE, "TopicExchange","topic.*",null));
        admin.declareBinding(new Binding("queue6", Binding.DestinationType.QUEUE, "TopicExchange",".Queue6",null));
    }

    @Test
    public void sendMessageToDiret(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg","send a message");
        rabbitTemplate.convertAndSend("DirectExchange","directQueue1",map);
    }

    @Test
    public void sendMessageToFanout(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg","send a message");
        rabbitTemplate.convertAndSend("FanoutExchange","",map);
    }

    @Test
    public void sendMessageToTopic(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg","send a message");
        rabbitTemplate.convertAndSend("TopicExchange","topic.queue",map);
    }
    @Test
    public void receiveMessage(){
        Object queue1 = rabbitTemplate.receiveAndConvert("queue1");
        System.out.println(queue1);
    }


}
