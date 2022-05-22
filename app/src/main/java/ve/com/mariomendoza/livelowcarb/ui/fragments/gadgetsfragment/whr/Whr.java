package ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.whr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import ve.com.mariomendoza.livelowcarb.R;

public class Whr extends AppCompatActivity {

    private EditText waist, hip;
    private TextView result_whr, body;
    private RadioButton female, men;
    private ImageView image;
    private Button btnCalculate, btnClean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gadgets_whr);
        // Created variables for the app Whr
        configView();
    }

    private void configView() {
        waist = findViewById(R.id.waist);
        hip = findViewById(R.id.hip);

        result_whr = findViewById(R.id.result_whr);
        body = findViewById(R.id.body);

        female = findViewById(R.id.female);
        men = findViewById(R.id.men);

        btnCalculate = findViewById(R.id.calculate);
        btnClean = findViewById(R.id.clean);

        image = findViewById(R.id.image);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whrcalculate();
            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clean();
            }
        });

        clean();
    }

    void whrcalculate() {

        image.setVisibility(View.INVISIBLE);

        String valor1 = waist.getText().toString();
        String valor2 = hip.getText().toString();

        if (!female.isChecked() && !men.isChecked()) {
            Toast notificacion = Toast.makeText(getApplication(), getApplication().getString(R.string.enter_your_gender), Toast.LENGTH_LONG);
            notificacion.show();
        }

        //Si se ingreso el genero verifico los campos
        else {

            //Chequeo los campos.
            if (valor1.equals("") || valor2.equals("")) {

                Toast notificacion = Toast.makeText(getApplication(), getApplication().getString(R.string.please_fill_text), Toast.LENGTH_LONG);
                notificacion.show();

            }

            else {

                //declaro las variables WHR y la calculo

                double WHR = Double.parseDouble(valor1)/Double.parseDouble(valor2);

                DecimalFormat df = new DecimalFormat("#0.00");
                String resulF = df.format(WHR) + " " + R.string.whr_text_title;
                result_whr.setText(resulF);

                if (men.isChecked()) {
                    //// HOMBRE
                    if (WHR >= 0.71 && WHR <= 0.84) {
                        body.setText(R.string.whr_standard_text);
                        image.setVisibility(View.INVISIBLE);
                    }
                    if (WHR < 0.71) {
                        body.setText(R.string.whr_pear_text);
                        image.setImageResource(R.drawable.pera);
                        image.setVisibility(View.VISIBLE);
                    }
                    if (WHR > 0.84) {
                        body.setText(R.string.whr_apple_text);
                        image.setImageResource(R.drawable.manzana);
                        image.setVisibility(View.VISIBLE);
                    }
                }

                if (female.isChecked()) {
                    //// MUJER
                    if (WHR >= 0.78 && WHR <= 0.94) {
                        body.setText(R.string.whr_standard_text);
                        image.setVisibility(View.INVISIBLE);
                    }
                    if (WHR < 0.78) {
                        body.setText(R.string.whr_pear_text);
                        image.setImageResource(R.drawable.pera);
                        image.setVisibility(View.VISIBLE);
                    }
                    if (WHR > 0.94) {
                        body.setText(R.string.whr_apple_text);
                        image.setImageResource(R.drawable.manzana);
                        image.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    void clean() {
        waist.setText("");
        hip.setText("");
        result_whr.setText("");
        body.setText("");
        female.setChecked(false);
        men.setChecked(false);
        image.setVisibility(View.INVISIBLE);
    }

}
