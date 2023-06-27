// библиотека для работы программного Serial
#include <SoftwareSerial.h>
// библиотека для работы с датчиком освещённости (Troyka-модуль)
#include <TroykaLight.h>
//Пин питания датчика уровня воды
#define sensorPowerWater 7
//Пин получения данных датчика уровня воды
#define sensorPinWater A5
//Пин питания датчика влажности почвы
#define sensorPower 2
//Пин получения данных датчика влажности почвы
#define sensorPin A4
// serial-порт к которому подключён Wi-Fi модуль
#define WIFI_SERIAL mySerial
//Пин реле
#define rele A3

// создаём объект для работы с датчиком освещённости
// и передаём ему номер пина выходного сигнала
TroykaLight sensorLight(A0);
// создаём объект для работы с программным Serial
// и передаём ему пины TX(передача данных на микроконтроллер) и RX(получение данных с микроконтроллера)
SoftwareSerial mySerial(A2, A3);
 
String data = "";
String IP = "192.168.57.120";
String port = "8080";
//название и пароль от сети Wi-Fi
String ssid = "Real";
String password = "realchik78";
int countTimeCommand;
//Переменная, хранящая значение уровня воды
int water_data;
//время полива
int waterCount = 10000;
//Переменная, хранящая значение влажности почвы
int humidity;
//пороговое значение влажности
int limit = 50;
int light;
bool success;
bool currentSuccess;
bool generalSuccess;
 


void setup()
{
  // открываем последовательный порт для мониторинга действий в программе
  // и передаём скорость 9600 бод
  Serial.begin(9600);
  while (!Serial) {
  // ждём, пока не откроется монитор последовательного порта
  // для того, чтобы отследить все события в программе
  }
  Serial.print("Serial init OK\r\n");
  // открываем Serial-соединение с Wi-Fi модулем на скорости 115200 бод
  WIFI_SERIAL.begin(115200);
  //сброс настроек Wi-Fi модуля
  //reset();
  success = false;
  //подсоединение к Wi-Fi сети
  connectWiFi();
  //подсоединение к серверу
  //настраиваем режим работы аналогового порта на выход
  pinMode(sensorPowerWater, OUTPUT);
  pinMode(sensorPower, OUTPUT);
  pinMode(rele, OUTPUT);
  //установка уровня, чтобы на датчики не подавалось питание
  digitalWrite(sensorPowerWater, LOW);
  digitalWrite(sensorPower, LOW);
  digitalWrite(rele, HIGH);
}
 
void reset() 
{
  sendCommand("AT+RESTORE", 5, "OK");    
  delay(1000);
}

void connectWiFi()
{
  sendCommand("AT", 5, "OK");
  if (success)
    {sendCommand("AT+CWMODE=3", 5, "OK");}
  if (success)
    {sendCommand("AT+CWJAP=\"" + ssid + "\",\"" + password + "\"", 20, "OK");}
}

void connectServer(){
  if (success)
    {sendCommand("AT+CIPMUX=1", 10, "OK");}
  if (success)
    {sendCommand("AT+CIPSTART=0,\"TCP\",\""+ IP + "\"," + port, 10, "OK");}
  if (success){
    generalSuccess = true;
  }
}

void sendCommand(String command, int maxTime, char readReplay[])
{
  while (countTimeCommand < (maxTime * 1))
  {
    WIFI_SERIAL.println(command);
    delay(1000);
    if (WIFI_SERIAL.find(readReplay))
    {
      Serial.println(readReplay);
      success = true;
      currentSuccess = true;
      break;
    }
    countTimeCommand++;
  }
  if (countTimeCommand == (maxTime * 1)){
        Serial.println("success");
        success = false;
        currentSuccess = false;
  }
  countTimeCommand = 0;
}

void loop()
{
  // считывание данных с датчика освещённости
  sensorLight.read();
  // вывод показателей сенсора освещённости в люксах
  Serial.print("Light is ");
  light = int(sensorLight.getLightLux());
  Serial.print(light);
  Serial.println(" Lx\t");
  delay(500);
  // считываем данные с датчика влажности почвы
  humidity = readHumiditySensor();
  // выводим данные в Serial-порт
  Serial.print("Humidity: ");
  Serial.println(humidity);
  //полив, если значение влажности меньше порогового
  if (humidity < limit){
    digitalWrite(rele, LOW);
    delay(waterCount);
    digitalWrite(rele, HIGH);
  }
  digitalWrite(rele, HIGH);
  // ждём 500 мс
  delay(500);
  // считываем данные с датчика уровня воды
  water_data = readWaterSensor();
  Serial.print("Water level: ");
  Serial.println(water_data);
  connectServer();
  delay(2000);
  sendData();
  close();
  delay(10000);
  // если приходят данные из Wi-Fi модуля - отправим их в порт компьютера
  if (WIFI_SERIAL.available()) {
    Serial.write(WIFI_SERIAL.read());
  }
  // если приходят данные из компьютера - отправим их в Wi-Fi модуль
  if (Serial.available()) {
    WIFI_SERIAL.write(Serial.read());
  }
}


void close(){
  sendCommand("AT+CIPCLOSE", 10, "OK");
}

int readHumiditySensor(){
  //устанавливаем высокий уровень, чтобы на датчик подавалось питание
  digitalWrite(sensorPower, HIGH);
  delay(10);
  int value = analogRead(sensorPin);
  //выключаем датчик
  digitalWrite(sensorPower, LOW);
  return value;
}

int readWaterSensor(){
  //устанавливаем высокий уровень, чтобы на датчик подавалось питание
  digitalWrite(sensorPowerWater, HIGH);
  delay(10);
  //считываем данные с датчика(находятся в диапазоне от 0 до 1023)
  int value = analogRead(sensorPinWater);
  //выключаем датчик
  digitalWrite(sensorPowerWater, LOW);
  return value;
}


String stringFormat(){
  char outString[200];
  sprintf(outString, "\{WaterLevel:%d, Humidity:%d, Light:%d\}", water_data, humidity, light);
  return outString;
}


void sendData(){
        data = stringFormat();
        char stringLength[256];
        sprintf(stringLength, "AT+CIPSEND=0,%d", data.length());
        sendCommand(stringLength, 10, ">");
        delay(2000);
        if (currentSuccess = true)
        {
          Serial.println(data);
          WIFI_SERIAL.println(data); 
        }
        delay(2000);
}