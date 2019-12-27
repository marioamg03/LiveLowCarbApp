package ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.bmr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import ve.com.mariomendoza.livelowcarb.R;

public class Bmr extends AppCompatActivity {

    private EditText weigth,heigth,age;
    private TextView day,dayact;
    private RadioButton female,men,low,average,high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gadgets_bmr);

        // Created variables for the app bmr1

        weigth = findViewById(R.id.weigth);
        heigth = findViewById(R.id.heigth);
        age = findViewById(R.id.age);

        day = findViewById(R.id.day);
        dayact = findViewById(R.id.dayact);

        female = findViewById(R.id.female);
        men = findViewById(R.id.men);
        low = findViewById(R.id.low);
        average = findViewById(R.id.average);
        high = findViewById(R.id.high);

        clean();
    }

    public void bmrcalculate (View view) {

        String valor1 = weigth.getText().toString();
        String valor2 = heigth.getText().toString();
        String valor3 = age.getText().toString();

        //Verifico la seleccion de genero.
        if (!female.isChecked() && !men.isChecked()) {

            Toast notificacion = Toast.makeText(this, getString(R.string.enter_your_gender), Toast.LENGTH_LONG);
            notificacion.show();

        } else { //Si se ingreso el genero verifico los campos

            //Chequeo los campos.
            if (valor1.equals("") || valor2.equals("") || valor3.equals("")) {
                Toast notificacion = Toast.makeText(this, getString(R.string.please_fill_text), Toast.LENGTH_LONG);
                notificacion.show();
            } else { //Si los campos tienen valores les asigno valores.
                //asigno valor numerico a los campos
                double weigth = Double.parseDouble(valor1);
                double heigth = Double.parseDouble(valor2);
                double age = Math.round(Double.parseDouble(valor3));

                //declaro las variables TMB y fisico
                double TMB = 0;
                double fisico = 0;



                // Y verifico el esfuerzo fisico
                if (!low.isChecked() && !average.isChecked() && !high.isChecked()) {

                    Toast notificacion = Toast.makeText(this, getString(R.string.enter_your_physical_text), Toast.LENGTH_LONG);
                    notificacion.show();
                } else {

                    //Calculo el consumo de anergia diario sin esfuerzo fisico y luego lo muestro.

                    if (female.isChecked()) {
                        // ES UNA MUJER
                        TMB = (10 * weigth) + (6.25 * heigth) - (5 * age) + 5;
                    }

                    if (men.isChecked()) {
                        // ES UN HOMBRE
                        TMB = (10 * weigth) + (6.25 * heigth) - (5 * age) - 161;
                    }

                    //MUESTRO
                    String resu = TMB + " Kcal/" + getString(R.string.day_text);
                    day.setText(resu);

                    // Luego de mostrar el resultado calculo el consumo con esfuerzo fisico

                    if (low.isChecked()) {
                        //bajo
                        fisico = TMB*(1.4);
                    }
                    if (average.isChecked()) {
                        //medio
                        fisico = TMB * (1.6);
                    }
                    if (high.isChecked()) {
                        //alto
                        fisico = TMB * (2.2);
                    }

                    DecimalFormat df = new DecimalFormat("#.00");
                    String resulF = df.format(fisico) + " kcal/" + getString(R.string.day_physical_text);
                    dayact.setText(resulF);
                }
            }
        }
}

    public void callclean (View view) {
        clean();
    }

    public void clean () {

        weigth.setText("");
        heigth.setText("");
        age.setText("");
        day.setText("");
        dayact.setText("");
        female.setChecked(false);
        men.setChecked(false);
        low.setChecked(false);
        average.setChecked(false);
        high.setChecked(false);

    }

}
