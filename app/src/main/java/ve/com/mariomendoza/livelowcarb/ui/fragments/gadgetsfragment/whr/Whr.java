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
import androidx.lifecycle.ViewModelProviders;

import ve.com.mariomendoza.livelowcarb.R;

public class Whr extends AppCompatActivity {

    private EditText waist, hip;
    private TextView result_whr, body;
    private RadioButton female, men;
    private ImageView image;
    private Button btnCalculate, btnClean;

    private WhrViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gadgets_whr);
        // Created variables for the app Whr
        configView();
    }

    private void configView() {

        viewModel = ViewModelProviders.of(this).get(WhrViewModel.class);

        waist = findViewById(R.id.waist);
        hip = findViewById(R.id.hip);

        result_whr = findViewById(R.id.result_whr);
        body = findViewById(R.id.body);

        female = findViewById(R.id.female);
        men = findViewById(R.id.men);

        image = findViewById(R.id.image);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.whrcalculate(waist, hip, result_whr, body, female, men, image);
            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clean(waist, hip, result_whr, body, female, men, image);
            }
        });

        viewModel.clean(waist, hip, result_whr, body, female, men, image);

    }


    public void showToast(String string) {
        Toast notificacion = Toast.makeText(this, string, Toast.LENGTH_LONG);
        notificacion.show();
    }

}
