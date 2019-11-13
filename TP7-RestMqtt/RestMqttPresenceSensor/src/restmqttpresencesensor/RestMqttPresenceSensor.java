/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restmqttpresencesensor;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class RestMqttPresenceSensor {
    static String broker = "tcp://mqtt.dioty.co:1883";
static String clientId = "";
static MqttClient mqttClient;
static  MqttConnectOptions connOpts ;
 static Random random = new Random();
    public static void main(String[] args) throws MqttException, InterruptedException {
        MqttClient mqttClient = new MqttClient(broker, clientId);
         connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName("aimeneamiour@gmail.com");
        connOpts.setPassword(new char[]{'7', 'f', 'd', '7', 'f', '4', '4', 'c'});
        
        String topic = "/aimeneamiour@gmail.com/presnece";
        int i ;         int qos=0;

                mqttClient.connect(connOpts);

        for (int j = 0; j < 20; j++) {
              i= random.nextInt(2);
        String content = String.valueOf(i);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        System.out.println("Publish message: " + message);
        mqttClient.publish(topic, message);
            Thread.sleep(2000);
        }
      
    }
    
}
