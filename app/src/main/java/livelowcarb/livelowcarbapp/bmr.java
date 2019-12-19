package livelowcarb.livelowcarbapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class bmr extends AppCompatActivity {

    private EditText weigth,heigth,age;
    private TextView day,dayact;
    private RadioButton female,men,low,average,high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmr);

        // Created variables for the app bmr1

        weigth=(EditText)findViewById(R.id.weigth);
        heigth=(EditText)findViewById(R.id.heigth);
        age=(EditText)findViewById(R.id.age);

        day=(TextView)findViewById(R.id.day);
        dayact=(TextView)findViewById(R.id.dayact);

        female=(RadioButton)findViewById(R.id.female);
        men=(RadioButton)findViewById(R.id.men);
        low=(RadioButton)findViewById(R.id.low);
        average=(RadioButton)findViewById(R.id.average);
        high=(RadioButton)findViewById(R.id.high);

        clean();
    }



    public void bmrcalculate (View view) {

        String valor1=weigth.getText().toString();
        String valor2=heigth.getText().toString();
        String valor3=age.getText().toString();



        //Verifico la seleccion de genero.
        if (female.isChecked()==false && men.isChecked()==false) {

            Toast notificacion = Toast.makeText(this,"Enter Your Gender", Toast.LENGTH_LONG);
            notificacion.show();

        }

        //Si se ingreso el genero verifico los campos
        else{

            //Chequeo los campos.
            if (valor1.equals("") || valor2.equals("") || valor3.equals(""))
            {
                Toast notificacion = Toast.makeText(this,"Please Fill The Fields", Toast.LENGTH_LONG);
                notificacion.show();
            }
            //Si los campos tienen valores les asigno valores.
            else
            {
                //asigno valor numerico a los campos
                double weigth=Double.parseDouble(valor1);
                double heigth=Double.parseDouble(valor2);
                double age = Math.round(Double.parseDouble(valor3));

                //declaro las variables TMB y fisico
                double TMB = 0;
                double fisico = 0;



                // Y verifico el esfuerzo fisico
                if (low.isChecked()==false && average.isChecked()==false && high.isChecked()==false) {

                    Toast notificacion = Toast.makeText(this,"Enter Your Physical Effort", Toast.LENGTH_LONG);
                    notificacion.show();
                }
                else{

                    //Calculo el consumo de anergia diario sin esfuerzo fisico y luego lo muestro.

                    if (female.isChecked()==true) {
                        // ES UNA MUJER
                        TMB = (10 * weigth) + (6.25 * heigth) - (5 * age) + 5;
                    }

                    if (men.isChecked()==true) {
                        // ES UN HOMBRE
                        TMB = (10 * weigth) + (6.25 * heigth) - (5 * age) - 161;
                    }

                    //MUESTRO
                    String resu=String.valueOf(TMB)+ " Kcal/day";
                    day.setText(resu);

                    // Luego de mostrar el resultado calculo el consumo con esfuerzo fisico

                    if (low.isChecked() == true) {
                        //bajo
                        fisico=TMB*(1.4);
                    }
                    if (average.isChecked() == true) {
                        //medio
                        fisico= TMB * (1.6);
                    }
                    if (high.isChecked() == true) {
                        //alto
                        fisico= TMB * (2.2);
                    }

                    DecimalFormat df = new DecimalFormat("#.00");
                    String resulF=String.valueOf(df.format(fisico))+ " kcal/day according to their physical activity";
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
