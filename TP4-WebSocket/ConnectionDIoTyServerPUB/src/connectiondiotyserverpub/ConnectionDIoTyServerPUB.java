/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectiondiotyserverpub;

import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class ConnectionDIoTyServerPUB {
    static String broker = "tcp://mqtt.dioty.co:1883";
static String clientId = "";
static MqttClient mqttClient;
static  MqttConnectOptions connOpts ;


public static void main(String[] args) throws MqttException {
        mqttClient = new MqttClient(broker, clientId);

        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        // Votre login
        connOpts.setUserName("aimeneamiour@gmail.com");
        // Les lettres de votre mot de passe 7fd7f44c
        connOpts.setPassword(new char[]{'7', 'f', 'd', '7', 'f', '4', '4', 'c'});
        
        

        String content = "Hello CloudMQTT";
        String topic = "/aimeneamiour@gmail.com/hello";
        int qos=0;
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        System.out.println("Publish message: " + message);
        mqttClient.connect(connOpts);
        mqttClient.publish(topic, message);

    }
    
}
