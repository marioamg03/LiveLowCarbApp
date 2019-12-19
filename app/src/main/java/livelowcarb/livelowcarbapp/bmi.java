package livelowcarb.livelowcarbapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.text.DecimalFormat;

public class bmi extends AppCompatActivity {

    private EditText mass, heigth;
    private TextView result_bmi, body;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        // Created variables for the app whr

        mass=(EditText)findViewById(R.id.mass);
        heigth=(EditText)findViewById(R.id.heigth);

        result_bmi=(TextView)findViewById(R.id.result_bmi);
        body=(TextView)findViewById(R.id.body);


        image=(ImageView)findViewById(R.id.image);

        clean();
    }

    public void bmicalculate (View view) {

        image.setVisibility(View.INVISIBLE);

        String valor1 = mass.getText().toString();
        String valor2 = heigth.getText().toString();

        if (valor1.equals("") || valor2.equals("")) {

            Toast notificacion = Toast.makeText(this,"Please Fill The Fields", Toast.LENGTH_LONG);
            notificacion.show();

        }
        else {

            //asigno valor numerico a los campos
            double mass = Double.parseDouble(valor1);
            double heigth = Double.parseDouble(valor2);

            //declaro las variables BMI y la calculo

            double BMI = ((mass / ((heigth*heigth)/100)) * 100);

            DecimalFormat df = new DecimalFormat("#0.00");
            String resulF=String.valueOf(df.format(BMI))+ " Kg/m²";
            result_bmi.setText(resulF);

            if (BMI <= 15.99)
            {
                body.setText("Severe thinness");
                image.setImageResource(R.drawable.flaquito);
                image.setVisibility(view.VISIBLE);
            }
            if (BMI >= 16 && BMI <= 18.49)
            {
                body.setText("Thinness");
                image.setImageResource(R.drawable.flaco);
                image.setVisibility(view.VISIBLE);
            }
            if (BMI > 18.49 && BMI <= 24.99)
            {
                body.setText("Normal");
                image.setImageResource(R.drawable.pesoideal);
                image.setVisibility(view.VISIBLE);
            }
            if (BMI >= 25 && BMI <= 29.99)
            {
                body.setText("Overweight");
                image.setImageResource(R.drawable.mediano);
                image.setVisibility(view.VISIBLE);
            }
            if (BMI >= 30 && BMI <= 39.99)
            {
                body.setText("Obesity");
                image.setImageResource(R.drawable.mediogordo);
                image.setVisibility(view.VISIBLE);

            }
            if (BMI >= 40)
            {
                body.setText("Morbid obesity");
                image.setImageResource(R.drawable.gordo);
                image.setVisibility(view.VISIBLE);
            }
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
