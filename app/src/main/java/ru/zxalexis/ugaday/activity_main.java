package ru.zxalexis.ugaday;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Random;
import android.view.WindowManager;


public class activity_main extends Activity {
	TextView tvInfo;
	EditText etInput;
	Button bControl;
	Integer iPopitka;
	Boolean bFinished;
	Integer iChislo, iVvod;
	Random Rn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		tvInfo = (TextView) findViewById(R.id.textView1);
		etInput = (EditText) findViewById(R.id.editText1);
		bControl = (Button) findViewById(R.id.button1);
		Rn = new Random();
		iChislo = Rn.nextInt(100) + 1;
		if (iChislo > 100) iChislo -= 100;
		iPopitka = 0;
		bFinished = false;
		
		 /* code to show keyboard on startup.this code is not working.*/
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}

	public void onClick(View v) {
		if (!bFinished)  {
			if (!etInput.getText().toString().isEmpty()) {
				iVvod = Integer.parseInt(etInput.getText().toString());
			} else {
				iVvod = 0;
			}

			if ((iVvod > 100) | (iVvod < 1)) {
				tvInfo.setText(getResources().getString(R.string.error));
				etInput.setText("");
			} else {

				if (iVvod > iChislo) {
					iPopitka = iPopitka + 1;
					tvInfo.setText(getResources().getString(R.string.ahead));
				} else if (iVvod < iChislo) {
					iPopitka = iPopitka + 1;
					tvInfo.setText(getResources().getString(R.string.behind));
				} else if (iVvod == iChislo) {
					iPopitka = 0;
					bFinished = true;
					tvInfo.setText(getResources().getString(R.string.hit));
					bControl.setText(getResources().getString(R.string.playmore));
				}

				etInput.setText("");

				if ((iPopitka == 8) & (!bFinished)) {
					bFinished = true;
					tvInfo.setText(getResources().getString(R.string.fool));
					bControl.setText(getResources().getString(R.string.playmore));
				}
			}
		} else {
			etInput.setText("");
			tvInfo.setText(getResources().getString(R.string.try_to_guess));
			bControl.setText(getResources().getString(R.string.input_value));
			iChislo = Rn.nextInt(100) + 1;
			if (iChislo > 100) iChislo -= 100;
			bFinished = false;
			iPopitka = 0;
		}

	} //onClick

}// activity_main
