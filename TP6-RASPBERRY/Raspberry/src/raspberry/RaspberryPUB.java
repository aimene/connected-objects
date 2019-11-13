/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raspberry;

  import org.eclipse.paho.client.mqttv3.*;
public class RaspberryPUB {



        public static void main(String[] arg)
        {
                System.out.println("Hello");
                try
                {
                        MqttClient client = new MqttClient("tcp://192.168.1.1:1883","raspberry1");
                        // Connexion du client au broker
                        client.connect();
                        // Creation d'un message
                        MqttMessage message = new MqttMessage();
                        message.setPayload("aa".getBytes());
                        // Publication du message avec le topic "pahodemo/test"
                        client.publish("stateA/", message);
                        // Le client se deconnecte du broker
                        client.disconnect();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}

