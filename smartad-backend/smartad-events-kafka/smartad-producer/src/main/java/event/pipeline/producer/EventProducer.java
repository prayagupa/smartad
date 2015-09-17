package event.pipeline.producer;

/**
 * Created by prayagupd
 * on 9/17/15.
 */

//import kafka.javaapi.producer.ProducerData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class EventProducer extends Thread {
    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();
    final static String TOPIC = "smartad_test";

    public EventProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("zk.connect", "localhost:2181");
        props.put("metadata.broker.list", "localhost:9092");
        // Use random partitioner. Don't need the key type. Just set it to Integer.
        // The message is of type String.
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        System.out.println("Starting events stream production . . .  .    ................");
        produceEventMessageStream();
    }

    private void produceEventMessageStream() {
        int messageNo = 1;
        boolean condition = true; //messageNo > 2;
        while(condition) {
            String messageStr = new String("Smartad Message_" + messageNo);
            producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
            messageNo++;
        }
    }

    public void sendMessage(String topic) {
        Properties producerProperties = new Properties();
        producerProperties.put("serializer.class", "kafka.serializer.StringEncoder");
        producerProperties.put("metadata.broker.list", "localhost:9092");
        ProducerConfig producerConfig = new ProducerConfig(producerProperties);

        kafka.javaapi.producer.Producer<String,String> producer = new kafka.javaapi.producer.Producer<String, String>(producerConfig);
        SimpleDateFormat sdf = new SimpleDateFormat();
        KeyedMessage<String, String> message =new KeyedMessage<String, String>(topic,"Test message from smartad program " + sdf.format(new Date()));
        producer.send(message);
        producer.close();
    }

}
