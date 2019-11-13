
package intrusiondetector;

import javax.xml.ws.Endpoint;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class IntrusionDetector {
   
    static int door;
    static int presence;
   static MqttClient IntrusionDetectorSUB;  	
   static MqttClient IntrusionDetectorPUB; 
   
    static void IntrusionDetectorSUB() throws MqttException{
     
         IntrusionDetectorSUB   = new MqttClient("tcp://localhost:1883",  "IntrusionDetectorSUB");
         
         IntrusionDetectorSUB.setCallback(new MqttCallback() {
                int  value;
                        @Override
                        public void connectionLost(Throwable cause) {}
                       // Callback pour la reception des messages
                        @Override
                        public void messageArrived(String topic, MqttMessage message)throws Exception {
                            System.out.println("IntrusionDetectorSUB.IntrusionDetectorSUB()");
                            System.out.println(new String(message.getPayload()));
                            // value can contain several value 
                            value = Integer.parseInt(new String(message.getPayload()));
                           
                                   IntrusionDetectorPUB(value);  
                        }
                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {}
                    });
        IntrusionDetectorSUB.connect();
        IntrusionDetectorSUB.subscribe("house/openingdetector");
        IntrusionDetectorSUB.subscribe("house/presencedetector");
       // IntrusionDetectorSUB.disconnect();

    }
    
     static void IntrusionDetectorPUB(int intrusion) throws MqttException{
            IntrusionDetectorPUB = new MqttClient("tcp://localhost:1883",  "IntrusionDetectorPUB");
            IntrusionDetectorPUB.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(Integer.toString(intrusion).getBytes());
            System.out.println("IntrusionDetectorPUB.IntrusionDetectorPUB()");
            IntrusionDetectorPUB.publish("house/intrusion", message);
            IntrusionDetectorPUB.disconnect();
     }
  
    
    public static void main(String[] args) throws MqttException {
       IntrusionDetectorSUB();
    }
    
}
