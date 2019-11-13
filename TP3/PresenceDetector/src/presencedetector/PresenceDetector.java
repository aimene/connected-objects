
package presencedetector;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author Aimene AMIOUR
 */
public class PresenceDetector {

     static int RandomPresenceValue(){
        return new Random().nextInt(2);
    }
     
     static void publish ( int presence) throws MqttException{
        
            MqttClient PresenceDetector = new MqttClient("tcp://localhost:1883", "PresenceDetector");
            PresenceDetector.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(Integer.toString(presence).getBytes());
            PresenceDetector.publish("house/presencedetector", message);
            PresenceDetector.disconnect();
     }
    public static void main(String[] args) throws InterruptedException, MqttException {
      
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            publish(RandomPresenceValue());
        }
    }
    
}
