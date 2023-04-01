// любой GPIO пин с поддержкой АЦП
constexpr auto pinSensor = A0;
 
void setup() {
  // открываем Serial-порт
  Serial.begin(9600);
}
 
void loop() {
  // считываем данные с датчика влажности почвы
  int valueSensor = analogRead(pinSensor);
  // выводим данные в Serial-порт
  Serial.println(valueSensor);
  // ждём 100 мс
  delay(1000);
}