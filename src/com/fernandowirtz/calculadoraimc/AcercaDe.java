package com.fernandowirtz.calculadoraimc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AcercaDe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acercade);
	}

	public void cerrar(View view) {
		finish();
	}

}
