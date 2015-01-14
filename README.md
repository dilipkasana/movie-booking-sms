# movie-booking-sms

Goto Control Panel\All Control Panel Items\Action Center and change user account setting to never notify me.
Requirments

0.Install xampp.exe for windows 7 (recomonded for sql database only xampp is allowed/Others not tested and required change. )

2.Java and JRE installed windows System.

3.A usb modem connected to windows 7 to send and receive message.

4.If modem cannot send message while connected to internet please disconnect from internet and try again.

5.At home screen of application please select port name to which your modem is connected.and click on connected button two time.The app will either connected or exit.If connected you may process else select another port name or set other parameters according to your modem.

6.Generally modem is connected to serial port which has name like COM3,COM4,COM5.You can see port by

Control Panel\All Control Panel Items > Manage Devices > Modems > Your modem name > Properties > Modem > Port :


***********
to run the project in eclipse do the following

1. Goto Eclipse :File> import project > existing project into workspace>

2.Now goto eclipse_window->package explorer-> PROJECT_NAME ->src->right click on setup.java and run as->java application.

After running app :

********
You can see the database by paste the url in brouser http://localhost/phpmyadmin/ and click on nowsms
************
￼
************
For any further help e-mail me at dilipgurjarkasana@gmail.com with the subject of “help on project” or report issue.
***********
Thanks!!!!!!!!!!!!!!!!!!!!!!
IF ANY ERROR PLEASE DO FOLLOWING ELSE NOT NEEDED
install in windows7 32 bit from jmf-2_1_1e-windows-i586.exe 
*****for 64 bit search and download and install jmf
For each file which is in project

(i) win32com.dll

Paste it to correspond directory for me C:\Program Files\Java\jdk1.6.0_26\jre\bin And C:\Program Files\Java\jre6\bin

(ii) comm.jar,mysql-connector-java-5.1.18-bin.jar,jmf.jar

Paste these into for me C:\Program Files\Java\jdk1.6.0_26\jre\lib\ext
And C:\Program Files\Java\jre6\lib\ext. (iii) javax.comm.properties
Paste it for me into C:\Program Files\Java\jre6\lib C:\Program Files\Java\jdk1.6.0_26\jre\lib

4.set system environment by clicking on Start->Right click on Computer->properties->Advanced system setting->Environment variables->

Variable name CLASSPATH
Variable value .;.;C:\PROGRA~1\JMF21~1.1E\lib\sound.jar;C:\PROGRA~1\JMF21~1.1E\lib\jmf.jar;C:\PROGRA~1\JMF21 ~1.1E\lib;
Variable name JAVA_HOME
￼
Variable value C:\Program Files\Java\jdk1.6.0_26 (for me )
If another error do googling and change source.

