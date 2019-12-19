package livelowcarb.livelowcarbapp;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class whr extends AppCompatActivity {

    private EditText waist, hip;
    private TextView result_whr, body;
    private RadioButton female, men;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whr);
        // Created variables for the app whr

        waist=(EditText)findViewById(R.id.waist);
        hip=(EditText)findViewById(R.id.hip);

        result_whr=(TextView)findViewById(R.id.result_whr);
        body=(TextView)findViewById(R.id.body);

        female=(RadioButton)findViewById(R.id.female);
        men=(RadioButton)findViewById(R.id.men);

        image=(ImageView)findViewById(R.id.image);

        clean();
    }

    public void whrcalculate(View view) {

        image.setVisibility(View.INVISIBLE);

        String valor1 = waist.getText().toString();
        String valor2 = hip.getText().toString();

        if (female.isChecked()==false && men.isChecked()==false) {

            Toast notificacion = Toast.makeText(this,"Enter Your Gender", Toast.LENGTH_LONG);
            notificacion.show();

        }

        //Si se ingreso el genero verifico los campos
        else {

            //Chequeo los campos.
            if (valor1.equals("") || valor2.equals("")) {

                Toast notificacion = Toast.makeText(this,"Please Fill The Fields", Toast.LENGTH_LONG);
                notificacion.show();

            }

            else
            {
                //asigno valor numerico a los campos
                double waist=Double.parseDouble(valor1);
                double hip=Double.parseDouble(valor2);

                //declaro las variables WHR y la calculo

                double WHR = waist/hip;

                    DecimalFormat df = new DecimalFormat("#0.00");
                    String resulF=String.valueOf(df.format(WHR))+ " WHR";
                    result_whr.setText(resulF);

                if (men.isChecked() == true) {
                    //// HOMBRE
                    if (WHR >= 0.71 && WHR <= 0.84) {
                        body.setText("Within the standard body");
                        image.setVisibility(view.INVISIBLE);
                    }
                    if (WHR < 0.71) {
                        body.setText("Pear body");
                        image.setImageResource(R.drawable.pera);
                        image.setVisibility(view.VISIBLE);
                    }
                    if (WHR > 0.84) {
                        body.setText("Apple body");
                        image.setImageResource(R.drawable.manzana);
                        image.setVisibility(view.VISIBLE);
                    }
                }

                if (female.isChecked() == true) {
                    //// MUJER
                    if (WHR >= 0.78 && WHR <= 0.94) {
                        body.setText("Within the standard body");
                        image.setVisibility(view.INVISIBLE);
                    }
                    if (WHR < 0.78) {
                        body.setText("Pear body");
                        image.setImageResource(R.drawable.pera);
                        image.setVisibility(view.VISIBLE);
                    }
                    if (WHR > 0.94) {
                        body.setText("Apple body");
                        image.setImageResource(R.drawable.manzana);
                        image.setVisibility(view.VISIBLE);
                    }
                }

                }
            }
        }

    public void callclean (View view) {

        clean();

    }

    public void clean () {

        waist.setText("");
        hip.setText("");
        result_whr.setText("");
        body.setText("");
        female.setChecked(false);
        men.setChecked(false);
        image.setVisibility(View.INVISIBLE);
    }
    }
