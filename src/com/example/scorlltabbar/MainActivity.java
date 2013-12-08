/*
 * Copyright (C) 2013 jh
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.scorlltabbar;

import org.jh.widget.IndicatorBar;
import org.jh.widget.IndictorSwitcher;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/**
 *it's simple because this activity is just for test, so you can see the effect of IndicatorBar 
 * 
 * 
 * @author jh
 *
 */
public class MainActivity extends Activity {

	private IndictorSwitcher s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		IndicatorBar bar = (IndicatorBar) findViewById(R.id.ind);

		s = bar;

		((TextView) findViewById(R.id.txt1))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						s.switchView(0);

					}
				});
		((TextView) findViewById(R.id.txt2))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						s.switchView(1);

					}
				});

		((TextView) findViewById(R.id.txt3))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						s.switchView(2);

					}
				});

		((TextView) findViewById(R.id.txt4))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						s.switchView(3);

					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
