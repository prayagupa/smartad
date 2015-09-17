package event.pipeline.consumer;

import java.io.UnsupportedEncodingException;

/**
 * https://github.com/kafka-dev/kafka/blob/master/examples/src/main/java/kafka/examples/SimpleConsumerDemo.java
 * Created by prayagupd
 * on 9/17/15.
 */

public class EventConsumerApp {

    public static void main(String[] args) throws UnsupportedEncodingException{
        EventConsumerService consumerService = new EventConsumerService();
        consumerService.start();
    }
}
