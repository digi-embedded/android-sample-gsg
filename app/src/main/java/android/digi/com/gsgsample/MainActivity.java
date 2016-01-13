/**
 * Copyright (c) 2016 Digi International Inc.,
 * All rights not expressly granted are reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
 * =======================================================================
 */

package android.digi.com.gsgsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digi.android.gpio.GPIO;
import com.digi.android.gpio.GPIOException;
import com.digi.android.gpio.GPIOManager;
import com.digi.android.gpio.GPIOMode;
import com.digi.android.gpio.GPIOValue;

/**
 * GSG Sample application.
 *
 * <p>This example demonstrates the usage of the GPIO
 * API by blinking the User 0 LED of the SBC device.</p>
 *
 * <p>For a complete description on the example, refer to the 'README.md' file
 * included in the example directory.</p>
 */
public class MainActivity extends Activity {

	// Constants.
	private final static int GPIO_LED = 34;

	// Variables.
	private GPIO ledGPIO;

	private EditText blinkText;

	private Button blinkButton;

	private boolean blinking = false;

	private int blinkingPeriod;

	private Thread blinkingThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize application control and GPIO.
		initializeControls();
		initializeGPIOs();
	}

	@Override
	protected void onDestroy() {
		// Stop the blinking process.
		stopBlinking();

		ledGPIO = null;
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// Stop the blinking process.
		stopBlinking();

		super.onPause();
	}

	/**
	 * Initializes the LED GPIO.
	 */
	private void initializeGPIOs() {
		// Get the GPIO manager.
		GPIOManager gpioManager = new GPIOManager(this);
		try {
			ledGPIO = gpioManager.createGPIO(GPIO_LED, GPIOMode.OUTPUT_HIGH);
		} catch (GPIOException e) {
			displayError("Error initializing GPIO: " + e.getMessage());
		}
		// Initialize LED to a known status (off).
		resetLEDStatus();
	}

	/**
	 * Initializes application controls.
	 */
	private void initializeControls() {
		// Declare views by retrieving them with the ID.
		blinkText = (EditText)findViewById(R.id.blink_text);
		blinkButton = (Button)findViewById(R.id.blink_button);

		// Set focus to the button.
		blinkButton.setFocusable(true);
		blinkButton.setFocusableInTouchMode(true);///add this line
		blinkButton.requestFocus();

		blinkingPeriod = Integer.parseInt(getResources().getString(R.string.default_blink_value));

		// Add listeners.
		blinkText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

			@Override
			public void afterTextChanged(Editable editable) {
				handleBlinkingTextChanged();
			}
		});

		blinkButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				handleBlinkButtonPressed();
			}
		});
	}

	/**
	 * Sets the LED to a known status (off).
	 */
	private void resetLEDStatus() {
		try {
			ledGPIO.setValue(GPIOValue.LOW);
		} catch (GPIOException e) {
			displayError("Error setting GPIO value: " + e.getMessage());
		}
	}

	/**
	 * Blinking period text was changed, configure controls accordingly.
	 */
	private void handleBlinkingTextChanged() {
		boolean validNumber = true;
		int period = 0;

		if (blinkText.getText().length() == 0)
			validNumber = false;
		else {
			try {
				period = Integer.parseInt(blinkText.getText().toString());
				if (period <= 0)
					validNumber = false;
			} catch (NumberFormatException e) {
				validNumber = false;
			}
		}

		if (validNumber) {
			blinkingPeriod = period;
			blinkButton.setBackgroundColor(getResources().getColor(R.color.colorCoorpGreen));
		} else
			blinkButton.setBackgroundColor(getResources().getColor(R.color.colorCoorpDarkGray));
		blinkButton.setEnabled(validNumber);
	}

	/**
	 * Blinking button was pressed, start or stop the blinking cycle.
	 */
	private void handleBlinkButtonPressed() {
		if (blinking)
			stopBlinking();
		else
			startBlinking();

		// When the blinking process is running, the edit text control should be disabled.
		blinkText.setEnabled(!blinking);
	}

	/**
	 * Starts the blinking process. Creates a thread where the status of the GPIO LED is toggled
	 * based on the period value.
	 */
	private void startBlinking() {
		if (blinking)
			return;

		blinking = true;

		blinkingThread = new Thread() {
			GPIOValue ledStatus = GPIOValue.HIGH;
			@Override
			public void run() {
				while (blinking) {
					if (!blinking)
						break;
					try {
						// Change the status of the LED and toggle the value.
						ledGPIO.setValue(ledStatus);
						if (ledStatus == GPIOValue.HIGH)
							ledStatus = GPIOValue.LOW;
						else
							ledStatus = GPIOValue.HIGH;
						// Sleep period/2
						try {
							Thread.sleep(blinkingPeriod/2);
						} catch (InterruptedException e) { }
					} catch (final GPIOException e) {
						blinking = false;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								displayError("Error setting GPIO value: " + e.getMessage());
							}
						});
					}
				}
			}
		};
		blinkingThread.start();
		// Update the button status.
		blinkButton.setText(getResources().getString(R.string.stop_blink));
		blinkButton.setBackgroundColor(getResources().getColor(R.color.colorCoorpOrange));
	}

	/**
	 * Stops the blinking process. Sets the blinking var to false and interrupts the blinking
	 * thread.
	 */
	private void stopBlinking() {
		if (!blinking)
			return;

		blinking = false;

		if (blinkingThread != null)
			blinkingThread.interrupt();

		// Turn off the LED.
		resetLEDStatus();

		// Update the button status.
		blinkButton.setText(getResources().getString(R.string.start_blink));
		blinkButton.setBackgroundColor(getResources().getColor(R.color.colorCoorpGreen));
	}

	/**
	 * Displays a pop-up with the given error message and exits the application.
	 *
	 * @param errorMessage The error message to display.
	 */
	private void displayError(String errorMessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(errorMessage);
		builder.setCancelable(true);

		builder.setPositiveButton(
				"OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}

