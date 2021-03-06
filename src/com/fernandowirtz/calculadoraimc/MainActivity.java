package com.fernandowirtz.calculadoraimc;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	// Atributos:
	private static final String LOGTAG = "MainActivity";
	private IndiceMasaCorporal imc;
	private EditText editPeso;
	private EditText editAltura;
	private TextView textViewResultado;
	private TextView textViewIMC;
	private String peso;
	private String altura;
	private String resultado;
	private String valorIMC;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Establecer identificadores
		editPeso = (EditText) findViewById(R.id.editPeso);
		editAltura = (EditText) findViewById(R.id.editAltura);
		textViewResultado = (TextView) findViewById(R.id.textViewResultado);
		textViewIMC = (TextView) findViewById(R.id.textViewIMC);

	}

	/**
	 * onButtonClickCalcularIMC
	 */
	public void onButtonClickCalcularIMC(View v) {

		try {

			peso = editPeso.getText().toString();
			altura = editAltura.getText().toString();

			imc = new IndiceMasaCorporal(peso, altura);

			valorIMC = imc.valorIndiceMasaCorporal().toString();
			textViewIMC.setText(valorIMC);
			resultado = imc.clasificacionOMS();
			textViewResultado.setText(resultado);
			Log.e(LOGTAG, imc.toString());

		} catch (IndiceMasaCorporalException e) {
			if (e.isErrorPeso()) {
				editPeso.setBackgroundColor(Color.LTGRAY);
			}
			if (e.isErrorAltura()) {
				editAltura.setBackgroundColor(Color.LTGRAY);
			}
			String texto = "Error entrada de datos";
			Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT)
					.show();
		} catch (Exception e) {
			Log.e(LOGTAG, e.getMessage());
		}
	}

	public void onButtonClickAcercaDe(View view) {
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Para que al girar la pantalla no se pierdan los datos...
	@Override
	protected void onSaveInstanceState(Bundle guardarEstado) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(guardarEstado);
		guardarEstado.putString("resultado", resultado);
		guardarEstado.putString("valorIMC", valorIMC);

	}

	@Override
	protected void onRestoreInstanceState(Bundle recEstado) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(recEstado);
		resultado = recEstado.getString("resultado");
		valorIMC = recEstado.getString("valorIMC");

		textViewResultado.setText(resultado);
		textViewIMC.setText(valorIMC);

	}
}
