Getting Started Sample Application
==================================

This application is part of the Get Started for Digi Embedded for Android.
It demonstrates the usage of the GPIO API by blinking an LED of the device.

Demo requirements
-----------------

To run this example you need:

* A compatible development board to host the application.
* A USB connection between the board and the host PC in order to transfer and
  launch the application.

Demo setup
----------

Make sure the hardware is set up correctly:

1. The development board is powered on.
2. The board is connected directly to the PC by the micro USB cable.

Demo run
--------

The example is already configured, so all you need to do is to build and
launch the project.

The application allows you to start and stop a process that will make an
LED of the board to blink with a specific period. It displays the location of
the LED within the board:

* ConnectCore 8X SBC Pro:
     * User LED 0 (PTD5)
* ConnectCore 8M Mini Development Kit:
     * User LED 3 (GPIO2_IO19)

Configure the blinking period (ms) and then click the "Start Blinking" button
in order to start the blinking process. You will see the LED blinking in the
physical board. Click the "Stop Blinking" button to stop the process.

Compatible with
---------------

* ConnectCore 6 SBC
* ConnectCore 6 SBC v3
* ConnectCore 8X SBC Pro
* ConnectCore 8M Mini Development Kit

License
-------

Copyright (c) 2016-2025, Digi International Inc. <support@digi.com>

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
