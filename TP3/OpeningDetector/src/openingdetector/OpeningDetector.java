
package openingdetector;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Aimene AMIOUR
 */
public class OpeningDetector {

  
     static int RandomOpeningValue(){
        return new Random().nextInt(2);
    }
     
     static void publish ( int open) throws MqttException{
        
            MqttClient OpeningDetector = new MqttClient("tcp://localhost:1883", "OpeningDetector");
            OpeningDetector.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(Integer.toString(open).getBytes());
            OpeningDetector.publish("house/openingdetector", message);
            OpeningDetector.disconnect();
     }
    public static void main(String[] args) throws InterruptedException, MqttException {
      
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            publish(RandomOpeningValue());
        }
    }
    
    
}
