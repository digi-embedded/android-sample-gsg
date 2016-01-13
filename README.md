GSG Sample Application
=======================

This application is part of the ConnectCore 6 Getting Started Guide for 
Android. It demonstrates the usage of the GPIO API by blinking the User 
LED 0 of the SBC device.

Demo requirements
-----------------

To run this example you need:

* One compatible device to host the application.
* A USB connection between the device and the host PC in order to transfer and
  launch the application.

Demo setup
----------

Make sure the hardware is set up correctly:

1. The device is powered on.
2. The device is connected directly to the PC by the micro USB cable.

Demo run
--------

The example is already configured, so all you need to do is to build and 
launch the project.

The application allows you to start and stop a process that will make the 
User LED 0 of the SBC board to blink with a specific period. It displays 
the location of the User LED 0 within the board.

Configure the blinking period (ms) and then click the "Start Blinking" button 
in order to start the blinking process. You will see the User LED 0 blinking 
in the physical board. Click the "Stop Blinking" button to stop the process.

Compatible with
---------------

* ConnectCore 6 SBC
* ConnectCore 6 SBC v3

License
-------

This software is open-source software. Copyright Digi International, 2016.

This Source Code Form is subject to the terms of the Mozilla Public License,
v. 2.0. If a copy of the MPL was not distributed with this file, you can obtain
one at http://mozilla.org/MPL/2.0/.