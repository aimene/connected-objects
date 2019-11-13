
package activatedeactivatealarmbutton;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author e1801322
 */
public class ActivateDeactivateAlarmButton {
    
    
 static int RandomOnOffValue(){
        return new Random().nextInt(2);
    }
     
     static void publish ( int open) throws MqttException{
        
            MqttClient AlarmButtonPub = new MqttClient("tcp://localhost:1883", "ActivateDeactivateAlarmButton");
            AlarmButtonPub.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(Integer.toString(open).getBytes());
            AlarmButtonPub.publish("house/button", message);
            AlarmButtonPub.disconnect();
     }
    public static void main(String[] args) throws InterruptedException, MqttException {
      
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            publish(RandomOnOffValue());
        }
    }

   
    
}
