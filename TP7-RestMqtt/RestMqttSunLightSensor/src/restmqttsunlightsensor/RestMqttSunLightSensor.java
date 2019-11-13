/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restmqttsunlightsensor;

import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class RestMqttSunLightSensor {

   static String broker = "tcp://mqtt.dioty.co:1883";
static String clientId = "";
static MqttClient mqttClient;
static  MqttConnectOptions connOpts ;

    public static void main(String[] args) throws MqttException, InterruptedException {
        MqttClient mqttClient = new MqttClient(broker, clientId);
         connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName("aimeneamiour@gmail.com");
        connOpts.setPassword(new char[]{'7', 'f', 'd', '7', 'f', '4', '4', 'c'});
        
        String topic = "/aimeneamiour@gmail.com/sunlightsensor";
        int i ;         int qos=0;

                mqttClient.connect(connOpts);

        for (int j = 0; j < 20; j++) {
              i= (int) (0 + (Math.random()*(100-0)));
        String content = String.valueOf(i);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        System.out.println("Publish message: " + message);
        mqttClient.publish(topic, message);
            Thread.sleep(2000);
        }
      
}
}
