
package javaapplication9;

import org.eclipse.paho.client.mqttv3.*;

public class Controller {

    static String broker = "tcp://mqtt.dioty.co:1883";
    static String clientId = "";
    
    static final String topicSunLight = "/aimeneamiour@gmail.com/sunlightsensor";
    static final String topicPresence = "/aimeneamiour@gmail.com/presnece";
    static MqttClient SunLightAndPrecenseSensor;
    static MqttClient LampClient;

    static  MqttConnectOptions connOpts ;
    
    static int SunLightIntensityValue = 0;
    static boolean Presence = false;
    static boolean ForceSmartPhoneLight=false;
    
   static void publishLamp(boolean state) throws MqttException{
         LampClient = new MqttClient(broker, clientId);

        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName("aimeneamiour@gmail.com");
        connOpts.setPassword(new char[]{'7', 'f', 'd', '7', 'f', '4', '4', 'c'});
        
        String content = String.valueOf(state) ;
        String topic = "/aimeneamiour@gmail.com/lamp";
        int qos=0;
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        System.out.println("============| Lamp Pub |==============" );
        System.out.println("Publish to lamp state : " + message);
        System.out.println("==========================" );
        LampClient.connect(connOpts);
        LampClient.publish(topic, message);
    }
    
      static void  subscribeSunLightSensorAndPresenceSenor() throws MqttException{
           // Creation d’un client
            SunLightAndPrecenseSensor = new MqttClient(broker, clientId);
            // Definit son callback
            SunLightAndPrecenseSensor.setCallback(new MqttCallback() {
                public void messageArrived(String topic, MqttMessage msg)
                        throws Exception {
          
        
                    switch(topic) {
                        case topicSunLight :   {
                            
                            SunLightIntensityValue= Integer.parseInt(new String(msg.getPayload()));
                            System.out.println("============| SunLight Sub |==============" );
                            System.out.println("Received:" + topic);
                            System.out.println("Sun light intensity value:" + SunLightIntensityValue);
                            System.out.println("==========================" );

                                break;}
                        case topicPresence : {
                                int val = Integer.parseInt(new String(msg.getPayload()));
                                Presence = val == 1;
                                
                                System.out.println("============| Prensence Sub |==============" );
                                System.out.println("Received:" + topic);
                                System.out.println("Presnece in house : " + Presence);
                                System.out.println("==========================" );
                                break;
                        }
                   
                    }
                    if (SunLightIntensityValue>50) 
                    { publishLamp(false);}
                    if ( SunLightIntensityValue<= 50 && Presence) 
                    { publishLamp(true); }
                    if( SunLightIntensityValue<=50 && ForceSmartPhoneLight)
                    { publishLamp(true);}
                    
                    //publishLamp(false);
                      Thread.sleep(2000);
                    
                    
    
                        
                }
                public void deliveryComplete(IMqttDeliveryToken arg0) {
                   // System.out.println("Delivery complete");
                }
                public void connectionLost(Throwable arg0) {
                }});
            
            connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName("aimeneamiour@gmail.com");
            connOpts.setPassword(new char[]{'7', 'f', 'd', '7', 'f', '4', '4', 'c'});
           
            SunLightAndPrecenseSensor.connect(connOpts);
            int qos=0;
            SunLightAndPrecenseSensor.subscribe(topicPresence, qos);
            SunLightAndPrecenseSensor.subscribe(topicSunLight, qos);

    }
  
    
    public static void main(String[] args) throws MqttException {

        
        subscribeSunLightSensorAndPresenceSenor();
    }
    
}
