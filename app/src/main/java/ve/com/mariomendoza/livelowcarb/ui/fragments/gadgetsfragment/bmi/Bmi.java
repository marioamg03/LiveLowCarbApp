package ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.bmi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import ve.com.mariomendoza.livelowcarb.R;

public class Bmi extends AppCompatActivity {

    private EditText mass, heigth;
    private TextView result_bmi, body;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gadgets_bmi);

        // Created variables for the app Whr

        mass = findViewById(R.id.mass);
        heigth = findViewById(R.id.heigth);

        result_bmi = findViewById(R.id.result_bmi);
        body = findViewById(R.id.body);

        image = findViewById(R.id.image);

        clean();
    }

    public void bmicalculate (View view) {

        image.setVisibility(View.INVISIBLE);

        String valor1 = mass.getText().toString();
        String valor2 = heigth.getText().toString();

        if (valor1.equals("") || valor2.equals("")) {

            Toast notificacion = Toast.makeText(this,getString(R.string.please_fill_text), Toast.LENGTH_LONG);
            notificacion.show();

        } else {

            //asigno valor numerico a los campos
            double mass = Double.parseDouble(valor1);
            double heigth = Double.parseDouble(valor2);

            //declaro las variables BMI y la calculo

            double BMI = ((mass / ((heigth*heigth)/100)) * 100);

            DecimalFormat df = new DecimalFormat("#0.00");
            String resulF= df.format(BMI)+ " Kg/m²";
            result_bmi.setText(resulF);

            if (BMI <= 15.99)
            {
                body.setText(getString(R.string.severe_thinness_text));
                image.setImageResource(R.drawable.flaquito);
            }
            if (BMI >= 16 && BMI <= 18.49)
            {
                body.setText(getString(R.string.thinness_text));
                image.setImageResource(R.drawable.flaco);
            }
            if (BMI > 18.49 && BMI <= 24.99)
            {
                body.setText(getString(R.string.normal_text));
                image.setImageResource(R.drawable.pesoideal);
            }
            if (BMI >= 25 && BMI <= 29.99)
            {
                body.setText(getString(R.string.overweight_text));
                image.setImageResource(R.drawable.mediano);
            }
            if (BMI >= 30 && BMI <= 39.99)
            {
                body.setText(getString(R.string.obesity_text));
                image.setImageResource(R.drawable.mediogordo);
            }
            if (BMI >= 40)
            {
                body.setText(getString(R.string.morbid_obesity_text));
                image.setImageResource(R.drawable.gordo);
            }
            image.setVisibility(View.VISIBLE);
        }



    }

    public void callclean (View view) {

        clean();

    }

    public void clean () {

        mass.setText("");
        heigth.setText("");
        result_bmi.setText("");
        body.setText("");
        image.setVisibility(View.INVISIBLE);

    }
}
