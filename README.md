Getting Started Sample Application
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

Copyright (c) 2016, Digi International Inc. <support@digi.com>

Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.