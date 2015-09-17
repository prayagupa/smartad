package event.pipeline.producer;

/**
 * Created by prayagupd
 * on 9/17/15.
 */

public class EventProducerApp {
    public static void main(String[] argv){
        EventProducer eventProducer = new EventProducer("smartad_events");
//        eventProducer.sendMessage("smartad_test_different");
        eventProducer.start();
    }
}
