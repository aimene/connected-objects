/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raspberrysub;

import java.time.Clock;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class RaspberrySUB {

  
     static MqttClient RaspberrySUB;  	
   
    static void IntrusionDetectorSUB() throws MqttException{
     
         RaspberrySUB   = new MqttClient("tcp://192.168.1.1:1883",  "RaspberrySUB");
         
         RaspberrySUB.setCallback(new MqttCallback() {
                String  value;
                        @Override
                        public void connectionLost(Throwable cause) {}
                       // Callback pour la reception des messages
                        @Override
                        public void messageArrived(String topic, MqttMessage message)throws Exception {
                         
                            // value can contain several value 
                            value = (new String(message.getPayload()));
                           
                                    System.out.println(value);
                        }
                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {}
                    });
        RaspberrySUB.connect();
        RaspberrySUB.subscribe("stateA/");
       // IntrusionDetectorSUB.disconnect();

    }
      public static void main(String[] arg) throws MqttException
        {
        IntrusionDetectorSUB();
        }
}
