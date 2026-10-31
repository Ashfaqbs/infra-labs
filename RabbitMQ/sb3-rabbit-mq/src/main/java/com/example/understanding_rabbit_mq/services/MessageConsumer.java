package com.example.understanding_rabbit_mq.services;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import java.io.IOException;


@Service
public class MessageConsumer {
    public static final String QUEUE_NAME = "exampleQueue";

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    // as we are in same project we dont need to create a queue bean
    /*
     * @Bean
    public Queue exampleQueue() {
        return new Queue(QUEUE_NAME, false);
    }
     */


    // Ack Message from queue :


    
    @RabbitListener(queues = QUEUE_NAME, ackMode = "MANUAL")
    public void listenv2(Message message, Channel channel) throws IOException {
        try {
            String modifiedMessage = new String(message.getBody()) + " - processed";
            System.out.println("Received and modified message: " + modifiedMessage);

            // Manually acknowledge message after processing
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // Reject the message without requeuing
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            System.err.println("Failed to process message: " + e.getMessage());
        }
    }
/*

The method receives both Message and Channel objects.It extracts the message body, modifies it, and processes it.
If successful, channel.basicAck() acknowledges the message.
If an error occurs, channel.basicNack() rejects it without requeuing.

Why Use Manual Acknowledgment?
Ensures messages are processed before being removed from the queue.
Prevents data loss in case of application crashes.
Allows reprocessing of messages if needed by requeueing (basicNack() with true as the third argument).
 
*/


    
}

