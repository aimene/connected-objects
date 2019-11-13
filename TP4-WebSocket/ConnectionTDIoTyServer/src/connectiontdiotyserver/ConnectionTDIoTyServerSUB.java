
package connectiontdiotyserver;

import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class ConnectionTDIoTyServerSUB {
    
static String broker = "tcp://mqtt.dioty.co:1883";
static String clientId = "";
static MqttClient mqttClient;
static  MqttConnectOptions connOpts ;

      public static void main(String[] args) throws MqttException {
                  // Creation d’un client
            mqttClient = new MqttClient(broker, clientId);
            // Definit son callback
            mqttClient.setCallback(new MqttCallback() {
                public void messageArrived(String topic, MqttMessage msg)
                        throws Exception {
                    System.out.println("Received:" + topic);
                    System.out.println("Received:" + new String(msg.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken arg0) {
                    System.out.println("Delivery complete");
                }
                public void connectionLost(Throwable arg0) {
                }});
           // Suite du code java d’un client qui souscrit au broker
            connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            // Votre login
            connOpts.setUserName("aimeneamiour@gmail.com");
            // Les lettres de votre mot de passe 7fd7f44c
            connOpts.setPassword(new char[]{'7', 'f', 'd', '7', 'f', '4', '4', 'c'});
            
            mqttClient.connect(connOpts);
            String topic = "/aimeneamiour@gmail.com/hello";
            int qos=0;
            mqttClient.subscribe(topic, qos);
                
            
        

          
    }
    
}
