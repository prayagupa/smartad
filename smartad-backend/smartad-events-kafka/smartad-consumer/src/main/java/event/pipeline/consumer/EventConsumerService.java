package event.pipeline.consumer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

/**
 * Created by prayagupd
 * on 9/17/15.
 */

public class EventConsumerService extends  Thread {
        final static String clientId = "EventConsumerService";
        final static String TOPIC = "smartad_test";
        ConsumerConnector consumerConnector;

        public EventConsumerService(){
            Properties properties = new Properties();
            properties.put("zookeeper.connect","localhost:2181");
            properties.put("group.id","smartad-test-group");
            ConsumerConfig consumerConfig = new ConsumerConfig(properties);
            consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
        }

        @Override
        public void run() {
            consumeMessageStreams();
        }

        private void consumeMessageStreams() {
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>(){{
                put(TOPIC, new Integer(1));
            }};
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
            KafkaStream<byte[], byte[]> stream =  consumerMap.get(TOPIC).get(0);
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while(it.hasNext())
                System.out.println(new String(it.next().message()));
        }

    private static void printMessages(ByteBufferMessageSet messageSet) throws UnsupportedEncodingException {
            for(MessageAndOffset messageAndOffset: messageSet) {
                ByteBuffer payload = messageAndOffset.message().payload();
                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);
                System.out.println(new String(bytes, "UTF-8"));
            }
        }
    }
