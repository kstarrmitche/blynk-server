# End to End Wireless Setup Using a Local Server, Raspberry Pi 3, and Mobile Device

Things you'll need:

 - A Raspberry Pi and flashed microSD card with an OS (I suggest Debian Jessie) For more information, see the [NOOBS installation instructions](https://www.raspberrypi.org/documentation/installation/noobs.md). Also, access to the file system via SSH or using a keyboard + mouse + monitor.
- A Laptop or Desktop
- A mobile device

## Setting up

### Laptop

1. Please follow the directions to build the jar file included in this repo. 
2. Begin running the server with the default configurations using the command `java -jar server-0.29.2-java8.jar -dataFolder insert/file/path/here `. This will run the server on default hardware port 8442 and default application port 8443.
3. Alternatively, you can run the server on your Raspberry Pi, but for testing purposes, running from a device primarily used for development is best. Also, make a note of your ip - this can be done various ways: either through the `ifconfig` command on mac/linux or `ipconfig` on windows.
4. Also note that the administator UI is located at this address: `https://your_ip:9443/admin` Here, you can add widgets, track users, devices, add energy to projects, and so on. This can be extremely useful when testing widgets, as it costs "energy" to use widgets.

### Mobile device

1. Download the blynk app for your device of choice.

   Android: [Google Play Store]([https://play.google.com/store/apps/details?id=cc.blynk)

   Apple: [Itunes App Store](https://itunes.apple.com/us/app/blynk-control-arduino-raspberry/id808760481?ls=1&mt=8)

2. Create an account - make sure to send yourself your Auth token - this will be used when connecting the mobile app to the raspberry pi!

3. When you log in, you have the option to select the blynk cloud server or select your own custom local server by hitting the icon below "Forgot Password?". Insert the IP of your computer running the server here, as well as the proper port. 

   ![25855818_10208630075187836_437680014_o](/Users/kathystarrmitchell/Desktop/blynkfromscratch/blynk-server/docs/25855818_10208630075187836_437680014_o.jpg)

Now comes the fun part - adding widgets! Familiarize yourself with the mobile UI, then follow these steps:

4. Add a **Value Display** widget and bind it to **V9**
5. Add a **Slider** widget and bind it to **V1**
6. Press **Run** (triangle in the upper right corner)

### Raspberry Pi

1. Download and install Node.js and the accompanying blynk-library on the device. Follow [this tutorial](http://help.blynk.cc/how-to-connect-different-hardware-with-blynk/raspberry-pi/how-to-install-nodejs-library-on-linux). I suggest using the automatic Node.js installation and following the "Creating a new Node.js project with Blynk" until the end. 

2. Find your ip address by using the `ifconfig` command. 

3. Once the blynk-library is installed, you can start writing code! create a new directory where all your code will be stored. The author of the node.js blynk-library provides a usage example [here](https://github.com/vshymanskyy/blynk-library-js:) and insert your auth token in the appropriate area:

   ```javascript
   var BlynkLib = require('blynk-library');

   var blynk = new BlynkLib.Blynk('INSERT AUTH TOKEN HERE');
   var v1 = new blynk.VirtualPin(1);
   var v9 = new blynk.VirtualPin(9);

   v1.on('write', function(param) {
     console.log('V1:', param);
   });

   v9.on('read', function() {
     v9.write(new Date().getSeconds());
   });
   ```

   What this code does is listen for events - when another client (our mobile devices) 'writes' to the virtual pin v1, a log output will be displayed. When a client makes a request to''read" virtual pin 9, the v9 pin will be written to by the raspberry pi.

   ​

### Connecting the mobile app to the Pi

If the setup is giving you trouble, I recommend following this fantastic guide by the creator of the node.js server [here](http://www.instructables.com/id/Blynk-JavaScript-in-20-minutes-Raspberry-Pi-Edison/#step2), starting from step two. From here, it should be self explanatory! If the above example isn't working, try the TCP example.

#### Resources

Thanks to [vshymanskyy](https://github.com/vshymanskyy) who wrote the node.js port of the blank-library. 