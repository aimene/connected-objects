
package statealarmsiren;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author e1801322
 */
public class StateAlarmSiren {
     static int alarmState = 1;
   static MqttClient AlarmSirenSUB;  	
   static MqttClient AlarmSirenPUB; 
   
   static void StateAlarmSirenSUB() throws MqttException{
     
         AlarmSirenSUB   = new MqttClient("tcp://localhost:1883",  "AlarmSirenSUB");
         
         AlarmSirenSUB.setCallback(new MqttCallback() {
                int  value;
                        @Override
                        public void connectionLost(Throwable cause) {}
                       // Callback pour la reception des messages
                        @Override
                        public void messageArrived(String topic, MqttMessage message)throws Exception {
                            System.out.println("AlarmSirenSUB.AlarmSirenSUB()");
                            System.out.println(new String(message.getPayload()));
                            // value can contain several value 
                            value = Integer.parseInt(new String(message.getPayload()));
                            if ( topic.equals("house/button")){
                                alarmState=value;
                            }
                            if ( topic.equals("house/intrusion")){
                                if (1==alarmState && value ==1) {
                                   AlarmSirenPUB("alarm ON");
                                } else {
                                    AlarmSirenPUB("alarm off");        
                                }                        
                            }
                        }
                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {}
                    });
        AlarmSirenSUB.connect();
        AlarmSirenSUB.subscribe("house/intrusion");
        AlarmSirenSUB.subscribe("house/button");
       // AlarmSirenSUB.disconnect();

    }
   
   static void AlarmSirenPUB(String  state) throws MqttException{
            AlarmSirenPUB = new MqttClient("tcp://localhost:1883",  "IntrusionDetectorPUB");
            AlarmSirenPUB.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(state.getBytes());
            System.out.println("IntrusionDetectorPUB.IntrusionDetectorPUB()");
            AlarmSirenPUB.publish("house/alarm", message);
            AlarmSirenPUB.disconnect();
     }
    public static void main(String[] args) throws MqttException {
     StateAlarmSirenSUB();
    }
    
}
