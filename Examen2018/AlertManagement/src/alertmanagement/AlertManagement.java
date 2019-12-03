/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alertmanagement;

import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author e1801322
 */
public class AlertManagement {
    static int valeurVent;
        static void AlertManagementPUB (String messageToSend , String topic) throws MqttException, InterruptedException{
     
            MqttClient Station = new MqttClient("tcp://localhost:1883", "Average");
            Station.connect();
            MqttMessage message = new MqttMessage();
           
            message.setPayload((messageToSend).getBytes());                 
            Station.publish(topic, message);
             
      }

   static void AlertManagementSUB() throws MqttException {

        MqttClient AlertManagement = new MqttClient("tcp://localhost:1883", "TemperatureCalc");

        AlertManagement.setCallback(new MqttCallback() {
           

            @Override
            public void connectionLost(Throwable cause) {
            }
            // Callback pour la reception des messages

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                
                 if(topic.equals("/vent")){
                     valeurVent = Integer.parseInt(new String(message.getPayload()));
                       System.out.println("vent  " + valeurVent);
                     if (valeurVent==3) {
                         AlertManagementPUB("début tempête", "/alerteTempete");
                         
                           TimeUnit.SECONDS.sleep(3);
                           
                         AlertManagementPUB("fin de tempête", "/alerteTempete");

                     }
                 }
            
                   

                 

                

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
        AlertManagement.connect();
        AlertManagement.subscribe("/vent");
                AlertManagement.disconnect();

       

    }
    public static void main(String[] args) throws MqttException {
        AlertManagementSUB();
    }
    
}
