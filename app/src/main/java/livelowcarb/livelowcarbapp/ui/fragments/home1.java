package livelowcarb.livelowcarbapp.ui.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import livelowcarb.livelowcarbapp.R;
import livelowcarb.livelowcarbapp.data.FoodsModel;

public class home1 extends Fragment {

    // Text donde coloco los datos
    private TextView foodt,carbt,proteint,fatt,caloriet,basedt;
    // Private
    private Switch toggle;
    private SeekBar seekBar;

    // Declaro el auto complete
    private AutoCompleteTextView Comida_a_buscar;

    //Vector donde esta almacenada la comida
    private String food [] = new String[5000];
    double calories [] = new double[5000];
    double fat [] = new double[5000];
    double Carbs [] = new double[5000];
    double Protein [] = new double[5000];

    /// Formato decimal
    private DecimalFormat df = new DecimalFormat("#0.00");

    //Variables que uso para juntar los strings de los textos
    String resulCarbs;
    String resulProtein;
    String resulFat;
    String resulCalori;
    String resulBased;

    //Variables con las que juego para hacer las cuentas.
    double Calorias;
    double Grasas;
    double Carbohidratos;
    double Proteinas;

    //Variable que me indica si ya hice una busqueda.
    boolean encontrado = false;

    //Variable que me indica si hice una transformacion antes.
    boolean gr = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home1, container, false);
        View Buscar = rootView.findViewById(R.id.search);


        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Busqueda(v);
            }

        });

        seekBar = rootView.findViewById(R.id.seekBar);

        seekBar.setProgress(90);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                progress = progresValue;
                double Unidad;
                Unidad = (progresValue) + 10;

                if (!toggle.isChecked()) {

                    resulBased = getString(R.string.based_on_text_title) + Unidad +"Gr (" + df.format(Unidad * 0.035) + "oz)";
                    basedt.setText(resulBased);

                    resulCarbs = df.format(Carbohidratos * Unidad/100)+ " Gr";
                    carbt.setText(resulCarbs);


                    resulProtein = df.format(Proteinas * Unidad/100)+ " Gr";
                    proteint.setText(resulProtein);


                    resulFat = df.format(Grasas * Unidad/100)+ " Gr";
                    fatt.setText(resulFat);


                    resulCalori = df.format(Calorias * Unidad/100)+ " Kcal";
                    caloriet.setText(resulCalori);

                }
                else{

                    resulBased = getString(R.string.based_on_text_title) + df.format(Unidad * 0.035) + "oz (" + Unidad + "Gr)";
                    basedt.setText(resulBased);

                    resulCarbs = df.format(Carbohidratos * Unidad * 0.035 / 100) + " oz";
                    carbt.setText(resulCarbs);


                    resulProtein = df.format(Proteinas * Unidad * 0.035 / 100) + " oz";
                    proteint.setText(resulProtein);


                    resulFat = df.format(Grasas * Unidad * 0.035 / 100) + " oz";
                    fatt.setText(resulFat);


                    resulCalori = df.format(Calorias * Unidad * 0.035 / 100) + " Kcal";
                    caloriet.setText(resulCalori);

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        llenado();
        llenadoA();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_dropdown_item_1line, Alimentos);
        Comida_a_buscar = view.findViewById(R.id.autoCompleteTextView);
        Comida_a_buscar.setAdapter(adapter);

        foodt = view.findViewById(R.id.food);
        carbt = view.findViewById(R.id.carb);
        proteint = view.findViewById(R.id.protein);
        fatt = view.findViewById(R.id.fat);
        caloriet = view.findViewById(R.id.calorie);
        basedt = view.findViewById(R.id.Basado);

        toggle = view.findViewById(R.id.switch1);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    toggle.setText("Oz");

                    if (!encontrado)
                    {
                        foodt.setText( "");
                        carbt.setText("");
                        proteint.setText("");
                        fatt.setText("");
                        caloriet.setText("");
                    }
                    else
                    {
                        seekBar.setProgress(90);

                        if (!gr)
                            {
                                resulBased = getString(R.string.based_on_text_title) + " 3.5oz (100.0Gr)";
                                basedt.setText(resulBased);

                                resulCarbs = df.format(Carbohidratos) + " oz";
                                carbt.setText(resulCarbs);

                                resulProtein = df.format(Proteinas) + " oz";
                                proteint.setText(resulProtein);

                                resulFat = df.format(Grasas) + " oz";
                                fatt.setText(resulFat);

                                resulCalori = df.format(Calorias) + " Kcal";
                                caloriet.setText(resulCalori);
                            }
                            else
                            {
                                resulBased = getString(R.string.based_on_text_title) + " 3.5oz (100.0Gr)";
                                basedt.setText(resulBased);

                                resulCarbs = df.format(Carbohidratos / 28.35) + " oz";
                                carbt.setText(resulCarbs);

                                resulProtein = df.format(Proteinas / 28.35) + " oz";
                                proteint.setText(resulProtein);

                                resulFat = df.format(Grasas / 28.35) + " oz";
                                fatt.setText(resulFat);

                                resulCalori = df.format( Calorias) + " Kcal";
                                caloriet.setText(resulCalori);

                                gr = false;

                            }
                    }

                    seekBar.setProgress(90);
                }
                else{
                    toggle.setText("Gr");

                    if (!encontrado)
                    {
                        foodt.setText( "");
                        carbt.setText("");
                        proteint.setText("");
                        fatt.setText("");
                        caloriet.setText("");
                    }
                    else
                    {
                        if (gr)
                        {
                            resulBased = getString(R.string.based_on_text_title) + " 100.0Gr (3.5oz)";
                            basedt.setText(resulBased);

                            resulCarbs = df.format(Carbohidratos) + " Gr";
                            carbt.setText(resulCarbs);

                            resulProtein = df.format(Proteinas) + " Gr";
                            proteint.setText(resulProtein);

                            resulFat = df.format(Grasas) + " Gr";
                            fatt.setText(resulFat);

                            resulCalori = df.format(Calorias) + " Kcal";
                            caloriet.setText(resulCalori);
                        }
                        else
                        {
                            resulBased = getString(R.string.based_on_text_title) + " 100.0Gr (3.5oz)";
                            basedt.setText(resulBased);

                            resulCarbs = df.format(Carbohidratos) + " Gr";
                            carbt.setText(resulCarbs);

                            resulProtein = df.format(Proteinas) + " Gr";
                            proteint.setText(resulProtein);

                            resulFat = df.format(Grasas) + " Gr";
                            fatt.setText(resulFat);

                            resulCalori = df.format(Calorias) + " Kcal";
                            caloriet.setText(resulCalori);

                            gr = true;
                        }
                    }

                    seekBar.setProgress(90);
                }

            }
        });
    }

    public void Busqueda (View v){   // Your method

        String comida;
        comida = Comida_a_buscar.getText().toString();
        String Escaneo;
        Double Cal;
        Double Fa;
        Double car;
        Double Pro;

        if (comida.equals(""))
        {
            foodt.setText(getString(R.string.please_fill_text));
            carbt.setText("");
            proteint.setText("");
            fatt.setText("");
            caloriet.setText("");
            Calorias = 0;
            Grasas = 0;
            Carbohidratos = 0;
            Proteinas = 0;
            seekBar.setVisibility(View.INVISIBLE);
        }
        else
        {
            for (int i = 0; i < 100; i++)
            {
                Escaneo = food[i];
                if (Escaneo.toLowerCase().equals(comida.toLowerCase()))
                {
                    seekBar.setVisibility(View.VISIBLE);
                    if (!toggle.isChecked()) {
                        foodt.setText(food[i]);

                        resulCarbs = df.format(Carbs[i]) + " Gr";
                        carbt.setText(resulCarbs);
                        car = Carbs[i];
                        Carbohidratos = car;

                        resulProtein = df.format(Protein[i]) + " Gr";
                        proteint.setText(resulProtein);
                        Pro = Protein[i];
                        Proteinas = Pro;

                        resulFat = df.format(fat[i]) + " Gr";
                        fatt.setText(resulFat);
                        Fa = fat[i];
                        Grasas = Fa;

                        resulCalori = df.format(calories[i]) + " Kcal";
                        caloriet.setText(resulCalori);
                        Cal = calories[i];
                        Calorias = Cal;

                        i = 100;
                        Comida_a_buscar.setText("");
                        encontrado = true;
                        seekBar.setProgress(90);

                    //    if (car <= 15)
                    //    {
                    //        bueno();
                    //    }

                    //    if (car > 15 && car < 30)
                      //  {
                    //        regular();
                      //  }

                    //    if (car >= 30)
                      //  {
                    //        malo();
                      //  }

                    } else {
                        if (toggle.isChecked()) {
                            foodt.setText(food[i]);


                            resulCarbs = df.format(Carbs[i] / 28.35) + " oz";
                            carbt.setText(resulCarbs);
                            car = Carbs[i];
                            Carbohidratos = car;


                            resulProtein = df.format(Protein[i] / 28.35) + " oz";
                            proteint.setText(resulProtein);
                            Pro = Protein[i];
                            Proteinas = Pro;


                            resulFat = df.format(fat[i] / 28.35) + " oz";
                            fatt.setText(resulFat);
                            Fa = fat[i];
                            Grasas = Fa;

                            resulCalori = df.format(calories[i]) + " Kcal";
                            caloriet.setText(resulCalori);
                            Cal = calories[i];
                            Calorias = Cal;

                            i = 100;
                            Comida_a_buscar.setText("");
                            encontrado = true;
                            seekBar.setProgress(90);

                          //  if (car <= 15)
                          //  {
                          //      bueno();
                          //  }
                          //
                          //  if (car > 15 && car < 30)
                          //  {
                          //      regular();
                          //  }
                          //
                          //  if (car >= 30)
                          //  {
                          //      malo();
                          //  }

                        }
                    }
                } else {
                    foodt.setText(getString(R.string.food_not_found_text));
                    carbt.setText("");
                    proteint.setText("");
                    fatt.setText("");
                    caloriet.setText("");
                    Calorias = 0;
                    Grasas = 0;
                    Carbohidratos = 0;
                    Proteinas = 0;
                    encontrado = false;
                    seekBar.setVisibility(View.INVISIBLE);
                }
            }
        }

    }


    private final String[] Alimentos = new String[501];

    // Codigo de llenado de los vectores de comida
    public void llenado (){

        food[0] ="Abalone (Mixed Species)";
        calories[0] = 105;
        fat[0] = 0.76;
        Carbs[0] = 6.01;
        Protein[0] = 17.10;

        food[1] = "Abalone (Mixed Species, Cooked, Fried)";
        calories[1] = 189;
        fat[1] = 6.78;
        Carbs[1] = 11.05;
        Protein[1] = 19.63;

        food[2] = "Acerola (West Indian Cherry)";
        calories[2] = 32;
        fat[2] = 0.30;
        Carbs[2] = 7.69;
        Protein[2] = 0.40;

        food[3] = "Acerola Juice";
        calories[3] = 23;
        fat[3] = 0.30;
        Carbs[3] = 4.80;
        Protein[3] = 0.40;

        food[4] = "Abiyuch";
        calories[4] = 69;
        fat[4] = 0.10;
        Carbs[4] = 17.60;
        Protein[4] = 1.50 ;

        food[5] = "Acorn Flour (Full Fat)";
        calories[5] = 501;
        fat[5] = 30.17;
        Carbs[5] = 54.65;
        Protein[5] = 7.49;

        food[6] = "Adobo Fresco";
        calories[6] =226 ;
        fat[6] = 20.90;
        Carbs[6] = 18.60;
        Protein[6] = 2.00;

        food[7] = "Acorn Winter Squash";
        calories[7] = 40;
        fat[7] = 0.10;
        Carbs[7] = 10.24;
        Protein[7] = 0.80;

        food[8] = "Acorn Winter Squash (Without Salt, Cooked, Baked)";
        calories[8] = 56;
        fat[8] = 0.14;
        Carbs[8] = 14.58;
        Protein[8] = 1.12;

        food[9] = "Acorn Winter Squash (Without Salt, Mashed, Cooked, Boiled)";
        calories[9] = 34;
        fat[9] = 0.08;
        Carbs[9] = 8.79;
        Protein[9] = 0.67;

        food[10] = "Acorn Winter Squash (With Salt, Cooked, Baked)";
        calories[10] = 56;
        fat[10] = 0.14;
        Carbs[10] = 14.58;
        Protein[10] = 1.12;

        food[11] = "Acorn Winter Squash (With Salt, Mashed, Cooked, Boiled)";
        calories[11] = 34;
        fat[11] = 0.08;
        Carbs[11] = 8.79;
        Protein[11] = 0.67;

        food[12] = "Acorns Nuts";
        calories[12] = 387;
        fat[12] = 23.86;
        Carbs[12] = 40.75;
        Protein[12] = 6.15;

        food[13] = "Adzuki Beans (Mature Seeds)";
        calories[13] = 329;
        fat[13] = 0.53;
        Carbs[13] = 62.90;
        Protein[13] =19.87;

        food[14] = "Agar Seaweed";
        calories[14] = 26;
        fat[14] =  0.03;
        Carbs[14] = 6.75;
        Protein[14] = 0.54;

        food[15] = "Adzuki Beans (Mature Seeds, Without Salt, Cooked, Boiled)";
        calories[15] = 128;
        fat[15] =  0.10;
        Carbs[15] = 24.77;
        Protein[15] = 7.52;

        food[16] = "Adzuki Beans (Mature Seeds, Sweetened, Canned)";
        calories[16] = 237;
        fat[16] = 0.03;
        Carbs[16] = 55.01;
        Protein[16] = 3.80;

        food[17] = "Adzuki Yokan Beans (Mature Seeds)";
        calories[17] = 260;
        fat[17] = 0.12;
        Carbs[17] = 60.72;
        Protein[17] = 3.29;

        food[18] = "Adzuki Beans (Mature Seeds, With Salt, Cooked, Boiled)";
        calories[18] = 128;
        fat[18] =0.10;
        Carbs[18] = 24.77;
        Protein[18] = 7.52;

        food[19] = "After Eight Mints Nestle Candies";
        calories[19] = 358;
        fat[19] = 13.70;
        Carbs[19] = 76.80;
        Protein[19] = 2.20;

        food[20] = "Agar Seaweed (Dried)";
        calories[20] = 306;
        fat[20] = 0.30;
        Carbs[20] = 80.88;
        Protein[20] = 6.21;

        food[21] = "Air Popped Popcorn";
        calories[21] = 387;
        fat[21] = 4.54;
        Carbs[21] = 77.78;
        Protein[21] = 12.94;

        food[22] = "Alcoholic Beverage (100 Proof, Gin Rum Vodka Whiskey)";
        calories[22] = 295;
        fat[22] = 0.00;
        Carbs[22] = 0.00;
        Protein[22] = 0.00;

        food[23] = "Alaska King Crab";
        calories[23] = 84;
        fat[23] = 0.60;
        Carbs[23] = 0.00;
        Protein[23] = 18.29;

        food[24] = "Alaska King Crab (Cooked, Moist Heat)";
        calories[24] = 97;
        fat[24] = 1.54;
        Carbs[24] = 0.00;
        Protein[24] = 19.35;

        food[25] = "Air Popped White Popcorn";
        calories[25] = 382;
        fat[25] = 4.20;
        Carbs[25] =77.90 ;
        Protein[25] = 12.00;

        food[26] = "Alfredo Mix Sauce";
        calories[26] = 535;
        fat[26] =36.35;
        Carbs[26] = 36.52;
        Protein[26] = 15.3;

        food[27] = "All Flavors General Mills Berry Burst Cheerios General Mills Cereals Ready-To-Eat";
        calories[27] = 405;
        fat[27] = 5.00;
        Carbs[27] = 80.00;
        Protein[27] = 10.00;

        food[28] = "Alfalfa Seeds (Sprouted)";
        calories[28] = 29;
        fat[28] = 0.69;
        Carbs[28] = 3.78;
        Protein[28] = 3.99;

        food[29] = "All Flavors Formulated Nuts (Without Salt, Wheat Based)";
        calories[29] = 647;
        fat[29] = 62.30;
        Carbs[29] = 20.79;
        Protein[29] = 13.11;

        food[30] = "Alcoholic Beverage (80 Proof, Gin Rum Vodka Whiskey)";
        calories[30] = 231;
        fat[30] = 0.00;
        Carbs[30] = 0.00;
        Protein[30] = 0.00;

        food[31] = "Alcoholic Beverage (94 Proof, Gin Rum Vodka Whiskey)";
        calories[31] = 275;
        fat[31] = 0.00;
        Carbs[31] = 0.00;
        Protein[31] = 0.00;

        food[32] = "Alcoholic Beverage (86 Proof, Gin Rum Vodka Whiskey)";
        calories[32] = 250;
        fat[32] = 0.00;
        Carbs[32] = 0.10;
        Protein[32] = 0.00;

        food[33] = "Alcoholic Beverage (90 Proof, Gin Rum Vodka Whiskey)";
        calories[33] = 263;
        fat[33] = 0.00;
        Carbs[33] = 0.00;
        Protein[33] = 0.00;

        food[34] = "Allspice Ground";
        calories[34] = 263;
        fat[34] = 8.69;
        Carbs[34] = 72.12;
        Protein[34] = 6.09;

        food[35] = "All Natural Light Vanilla Breyers Ice Creams";
        calories[35] = 162;
        fat[35] = 4.59;
        Carbs[35] = 25.30;
        Protein[35] = 4.84;

        food[36] = "All Natural Light French Vanilla Breyers Ice Creams";
        calories[36] = 173;
        fat[36] = 5.56;
        Carbs[36] = 26.03;
        Protein[36] = 4.82;

        food[37] = "All Natural Light Vanilla Chocolate Strawberry Breyers Ice Creams";
        calories[37] = 161;
        fat[37] =4.35;
        Carbs[37] =26.06;
        Protein[37] =4.69;

        food[38] = "All Natural Light Mint Chocolate Chip Breyers Ice Creams";
        calories[38] = 196;
        fat[38] = 7.09;
        Carbs[38] = 28.39;
        Protein[38] = 4.69;

        food[39] = "All Natural Light French Chocolate Breyers Ice Creams";
        calories[39] = 201;
        fat[39] = 7.28;
        Carbs[39] = 29.68;
        Protein[39] = 5.30;

        food[40] = "Almond Butter Nuts (With Salt Added)";
        calories[40] = 633;
        fat[40] = 59.10;
        Carbs[40] = 21.22;
        Protein[40] = 15.08;

        food[41] = "Almond Butter Nuts (Without Salt Added)";
        calories[41] = 633;
        fat[41] = 59.10;
        Carbs[41] = 21.22;
        Protein[41] = 15.08;

        food[42] = "Almond Vegetable Oil";
        calories[42] = 884;
        fat[42] = 100.00;
        Carbs[42] = 0.00;
        Protein[42] = 0.00;

        food[43] = "Almonds";
        calories[43] = 578;
        fat[43] = 50.64;
        Carbs[43] = 19.74;
        Protein[43] = 21.26;

        food[44] = "Almond Nut Paste";
        calories[44] = 458;
        fat[44] = 27.74;
        Carbs[44] = 47.81;
        Protein[44] = 9.00;

        food[45] = "Almond Joy Candy Bar Candies";
        calories[45] = 479;
        fat[45] = 26.93;
        Carbs[45] = 59.51;
        Protein[45] = 4.13;

        food[46] = "American Cheese (Cold Pack)";
        calories[46] = 331;
        fat[46] = 24.46;
        Carbs[46] = 8.32;
        Protein[46] = 19.66;

        food[47] = "Amaranth Leaves";
        calories[47] = 23;
        fat[47] = 0.33;
        Carbs[47] = 4.02;
        Protein[47] = 2.46;

        food[48] = "Amaranth Leaves (Without Salt, Drained, Cooked, Boiled)";
        calories[48] = 21;
        fat[48] = 0.18;
        Carbs[48] = 4.11;
        Protein[48] = 2.11;

        food[49] = "Amaranth Leaves (With Salt, Drained, Boiled, Cooked)";
        calories[49] = 21;
        fat[49] = 0.18;
        Carbs[49] = 4.11;
        Protein[49] = 2.11;

        food[50] = "Amaranth";
        calories[50] = 374;
        fat[50] = 6.51;
        Carbs[50] = 66.17;
        Protein[50] = 14.45;

        food[51] = "Alpen Ready-To-Eat Cereals";
        calories[51] = 352;
        fat[51] = 3.30;
        Carbs[51] = 75.70;
        Protein[51] = 11.20;

        food[52] = "American Cheese (Low Fat, Pasteurized)";
        calories[52] = 180;
        fat[52] = 7.00;
        Carbs[52] = 3.50;
        Protein[52] = 24.60;

        food[53] = "Amaranth Flakes";
        calories[53] = 353;
        fat[53] = 7.00;
        Carbs[53] = 75.15;
        Protein[53] = 15.54;

        food[54] = "American Cheese (With Di Sodium Phosphate, Pasteurized)";
        calories[54] = 375;
        fat[54] = 31.25;
        Carbs[54] = 1.60;
        Protein[54] = 22.15;

        food[55] = "American Cheese (Processed,Without Di Sodium Phosphate, Pasteurized)";
        calories[55] = 330;
        fat[55] = 25.18;
        Carbs[55] = 7.83;
        Protein[55] = 18.40;

        food[56] = "American Cheese Spread (Without Di Sodium Phosphate, Pasteurized)";
        calories[56] = 290;
        fat[56] = 21.23;
        Carbs[56] =8.73;
        Protein[56] = 16.41;

        food[57] = "American Cheese (Without Di Sodium Phosphate, Pasteurized)";
        calories[57] = 375;
        fat[57] = 31.25;
        Carbs[57] = 1.60;
        Protein[57] = 22.15;

        food[58] = "American Cheese (Processed,With Di Sodium Phosphate, Pasteurized)";
        calories[58] = 328;
        fat[58] = 24.60;
        Carbs[58] = 7.29;
        Protein[58] = 19.61;

        food[59] = "American Cheese Spread (With Di Sodium Phosphate, Pasteurized)";
        calories[59] = 290;
        fat[59] = 21.23;
        Carbs[59] = 8.73;
        Protein[59] = 16.41;

        food[60] = "American Shad (Fish)";
        calories[60] = 197;
        fat[60] =  13.77;
        Carbs[60] = 0.00;
        Protein[60] = 16.93;

        food[61] = "American Shad (Fish) (Cooked, Dry Heat)";
        calories[61] =252;
        fat[61] =17.65 ;
        Carbs[61] = 0.00;
        Protein[61] = 21.71;

        food[62] = "Anchovy";
        calories[62] =131;
        fat[62] = 4.84;
        Carbs[62] = 0.00;
        Protein[62] = 20.35;

        food[63] = "Anchovy (Drained Solids In Oil, Canned)";
        calories[63] = 210;
        fat[63] = 9.71;
        Carbs[63] = 0.00;
        Protein[63] = 28.89;

        food[64] = "And E C With Vitamins A Farley Fruit Snacks Farley Candy Snacks";
        calories[64] =341;
        fat[64] = 0.00;
        Carbs[64] = 80.90;
        Protein[64] = 4.40;

        food[65] = "And E C With Vitamins A Strawberry Sunkist Fruit Roll Sunkist Snacks";
        calories[65] = 342;
        fat[65] = 1.00;
        Carbs[65] = 82.70;
        Protein[65] = 0.60;

        food[66] = "Anise Seed";
        calories[66] = 337;
        fat[66] = 15.90;
        Carbs[66] = 50.02;
        Protein[66] = 17.60;

        food[67] = "And Raisins Honey Quaker 100% Natural Cereal With Oats Quaker Cereals Ready-To-Eat";
        calories[67] = 465;
        fat[67] = 17.90;
        Carbs[67] = 65.74;
        Protein[67] = 10.32;

        food[68] = "Antelope Meat";
        calories[68] = 114;
        fat[68] = 2.03;
        Carbs[68] = 0.00;
        Protein[68] = 22.38;

        Carbs[77] = 9.90;
        Protein[77] = 0.10;

        food[78] = "Antelope Meat (Cooked, Roasted)";
        calories[78] = 150;
        fat[78] = 2.67;
        Carbs[78] = 0.00;
        Protein[78] = 29.45;

        food[79] = "Apple - Cherry Juice Babyfood";
        calories[79] = 47;
        fat[79] = 0.10;
        Carbs[79] = 11.20;
        Protein[79] = 0.20;

        food[80] = "Apple And Prune Juice Babyfood";
        calories[80] = 73;
        fat[80] = 0.10;
        Carbs[80] = 18.00;
        Protein[80] = 0.20;

        food[81] = "Apple Cider-Flavored Drink (Powder, Added Vitamin C And Sugar)";
        calories[81] = 395;
        fat[81] = 0.00;
        Carbs[81] = 98.80;
        Protein[81] = 0.00;

        food[82] = "Apple Cinnamon Cheerios General Mills Cereals Ready-To-Eat";
        calories[82] = 392;
        fat[82] = 5.10;
        Carbs[82] = 84.00;
        Protein[82] = 6.00;

        food[83] = "Apple Cinnamon Kellogg's Pop Tarts Kellogg Toaster Pastries";
        calories[83] = 395;
        fat[83] = 10.20;
        Carbs[83] = 72.02;
        Protein[83] = 4.40;

        food[84] = "Apple Cinnamon Danish Kellogg's Pop-Tarts Pastry Swirls";
        calories[84] = 413;
        fat[84] = 17.70;
        Carbs[84] = 59.70;
        Protein[84] = 4.80;

        food[85] = "Apple Cider-Flavored Drink (Powder, Low Calorie, With Vitamin C)";
        calories[85] = 1;
        fat[85] = 0.00;
        Carbs[85] = 0.30;
        Protein[85] = 0.00;

        food[86] = "Apple Cinnamon Toasty O's Malt-O-Meal Cereals Ready-To-Eat";
        calories[86] = 410;
        fat[86] = 5.69;
        Carbs[86] = 82.50;
        Protein[86] = 7.10;

        food[87] = "Apple Juice (Canned Or Bottled)";
        calories[87] = 47;
        fat[87] = 0.11;
        Carbs[87] = 11.68;
        Protein[87] = 0.06;

        food[69] = "Angelfood Cake";
        calories[69] = 258;
        fat[69] = 0.80;
        Carbs[69] = 57.80;
        Protein[69] = 5.90;

        food[70] = "Angelfood Cake (Dry Mix)";
        calories[70] = 373;
        fat[70] = 0.40;
        Carbs[70] = 85.10;
        Protein[70] = 8.90;

        food[71] = "Angelfood Cake (Prepared, Dry Mix)";
        calories[71] = 257;
        fat[71] = 0.30;
        Carbs[71] = 58.70;
        Protein[71] = 6.10;

        food[72] = "Animal Crackers (Includes Arrowroot Cookies And Tea Biscuits)";
        calories[72] = 446;
        fat[72] = 13.80;
        Carbs[72] = 74.10;
        Protein[72] = 6.90;

        food[73] = "Animal Crackers Cookies";
        calories[73] = 446;
        fat[73] = 13.43;
        Carbs[73] = 75.33;
        Protein[73] = 6.18;

        food[74] = "Apple And Peach Juice Babyfood";
        calories[74] = 42;
        fat[74] = 0.10;
        Carbs[74] = 10.50;
        Protein[74] = 0.20;

        food[75] = "Apple And Plum Juice Babyfood";
        calories[75] = 49;
        fat[75] = 0.00;
        Carbs[75] = 12.30;
        Protein[75] = 0.10;

        food[76] = "Apple And Grape Juice Babyfood";
        calories[76] = 46;
        fat[76] = 0.20;
        Carbs[76] = 11.34;
        Protein[76] = 0.10;

        food[77] = "Apple And Cherry Juice Babyfood";
        calories[77] = 41;
        fat[77] = 0.20;
        food[88] = "Apple Juice (Frozen Concentrate)";
        calories[88] = 166;
        fat[88] = 0.37;
        Carbs[88] = 41.00;
        Protein[88] = 0.51;

        food[89] = "Apple Croissants";
        calories[89] = 254;
        fat[89] = 8.70;
        Carbs[89] = 37.10;
        Protein[89] = 7.40;

        food[90] = "Apple Filled Oatmeal Archway Home Style Cookies";
        calories[90] = 394;
        fat[90] = 12.76;
        Carbs[90] = 65.63;
        Protein[90] = 4.80;

        food[91] = "Apple Fruit Butters";
        calories[91] = 173;
        fat[91] = 0.00;
        Carbs[91] = 42.77;
        Protein[91] = 0.39;

        food[92] = "Apple Dippers Mcdonald's";
        calories[92] = 48;
        fat[92] = 0.00;
        Carbs[92] = 12.10;
        Protein[92] = 0.00;

        food[93] = "Apple Dippers With Low Fat Caramel Sauce Mcdonald's";
        calories[93] = 111;
        fat[93] = 0.77;
        Carbs[93] = 26.12;
        Protein[93] = 0.45;

        food[94] = "Apple Juice Babyfood";
        calories[94] = 47;
        fat[94] = 0.10;
        Carbs[94] = 11.70;
        Protein[94] = 0.00;

        food[95] = "Apple Multi-Grain Cereal Malt-O-Meal Cereals Ready-To-Eat";
        calories[95] = 395;
        fat[95] = 2.66;
        Carbs[95] = 87.30;
        Protein[95] = 5.40;

        food[96] = "Apple Juice (Frozen Concentrate, Diluted With Water)";
        calories[96] = 47;
        fat[96] = 0.10;
        Carbs[96] = 1.54;
        Protein[96] = 0.14;

        food[97] = "Apple Juice (With Added Ascorbic Acid, Canned Or Bottled)";
        calories[97] = 47;
        fat[97] = 0.11;
        Carbs[97] = 11.68;
        Protein[97] = 0.06;

        food[98] = "Apple Juice (With Added Ascorbic Acid, Frozen Concentrate)";
        calories[98] = 166;
        fat[98] = 0.37;
        Carbs[98] = 41.00;
        Protein[98] = 0.51;

        food[99] = "Apple Juice (Frozen Concentrate, Diluted With Water, With Added Ascorbic Acid)";
        calories[99] = 47;
        fat[99] = 0.10;
        Carbs[99] = 11.54;
        Protein[99] = 0.14;

        food[100] = "Apple Pie";
        calories[100] = 265;
        fat[100] = 12.50;
        Carbs[100] = 37.10;
        Protein[100] = 2.40;

        food[101] = "Apple Pie (Commercial, Enriched Flour)";
        calories[101] = 237;
        fat[101] = 11.00;
        Carbs[101] = 34.00;
        Protein[101] = 1.90;

        food[102] = "Apple-Banana Juice Babyfood";
        calories[102] = 51;
        fat[102] = 0.10;
        Carbs[102] = 12.30;
        Protein[102] = 0.20;

        food[103] = "Apple Strudel";
        calories[103] = 274;
        fat[103] = 11.20;
        Carbs[103] = 41.10;
        Protein[103] = 3.30;

        food[104] = "Apple Pie (Unenriched Flour)";
        calories[104] = 237;
        fat[104] = 11.00;
        Carbs[104] = 34.00;
        Protein[104] = 1.90;

        food[105] = "Apple Pie Fillings (Canned)";
        calories[105] = 101;
        fat[105] = 0.10;
        Carbs[105] = 26.20;
        Protein[105] = 0.10;

        food[106] = "Apple-Cranberry Juice Babyfood";
        calories[106] = 46;
        fat[106] = 0.02;
        Carbs[106] = 11.49;
        Protein[106] = 0.00;

        food[107] = "Apples";
        calories[107] = 52;
        fat[107] = 0.17;
        Carbs[107] = 13.81;
        Protein[107] = 0.26;

        food[108] = "Apples (Drained, Sliced, Sweetened, Canned)";
        calories[108] = 67;
        fat[108] = 0.49;
        Carbs[108] = 16.70;
        Protein[108] = 0.18;

        food[109] = "Apples (Low Moisture, Uncooked)";
        calories[109] = 346;
        fat[109] = 0.58;
        Carbs[109] = 93.53;
        Protein[109] = 1.32;

        food[110] = "Apples (Low Moisture, Stewed)";
        calories[110] = 74;
        fat[110] = 0.12;
        Carbs[110] = 19.91;
        Protein[110] = 0.28;

        food[111] = "Apples (With Added Sugar, Sulfured, Stewed)";
        calories[111] = 83;
        fat[111] = 0.07;
        Carbs[111] = 20.73;
        Protein[111] = 0.20;

        food[112] = "Apples (Unsweetened, Frozen)";
        calories[112] = 48;
        fat[112] = 0.32;
        Carbs[112] = 12.31;
        Protein[112] = 0.28;

        food[113] = "Apples (Unsweetened, Frozen, Heated)";
        calories[113] = 47;
        fat[113] = 0.33;
        Carbs[113] = 12.00;
        Protein[113] = 0.29;

        food[114] = "Apples (Without Skin)";
        calories[114] = 48;
        fat[114] = 0.13;
        Carbs[114] = 12.76;
        Protein[114] = 0.27;

        food[115] = "Apples Without Skin (Cooked, Boiled)";
        calories[115] = 53;
        fat[115] = 0.36;
        Carbs[115] = 13.64;
        Protein[115] = 0.26;

        food[116] = "Apples Without Skin (Cooked, Microwave)";
        calories[116] = 56;
        fat[116] = 0.42;
        Carbs[116] = 14.41;
        Protein[116] = 0.28;

        food[117] = "Apples (Without Added Sugar, Sulfured, Stewed)";
        calories[117] = 57;
        fat[117] = 0.07;
        Carbs[117] = 15.32;
        Protein[117] = 0.22;

        food[118] = "Applesauce (With Added Ascorbic Acid, Unsweetened, Canned)";
        calories[118] = 43;
        fat[118] = 0.05;
        Carbs[118] = 11.29;
        Protein[118] = 0.17;

        food[119] = "Applesauce (With Salt, Sweetened, Canned)";
        calories[119] = 76;
        fat[119] = 0.18;
        Carbs[119] = 19.91;
        Protein[119] = 0.18;

        food[120] = "Applesauce (Without Added Ascorbic Acid, Unsweetened, Canned)";
        calories[120] = 43;
        fat[120] = 0.05;
        Carbs[120] = 11.29;
        Protein[120] = 0.17;

        food[121] = "Applesauce (Without Salt, Sweetened, Canned)";
        calories[121] = 76;
        fat[121] = 0.18;
        Carbs[121] = 19.91;
        Protein[121] = 0.18;

        food[122] = "Apricot Kernel Vegetable Oil";
        calories[122] = 884;
        fat[122] = 100.00;
        Carbs[122] = 0.00;
        Protein[122] = 0.00;

        food[123] = "Apricot Filled Archway Home Style Cookies";
        calories[123] = 401;
        fat[123] = 13.90;
        Carbs[123] = 64.79;
        Protein[123] = 5.06;

        food[124] = "Apricot Jams And Preserves";
        calories[124] = 242;
        fat[124] = 0.20;
        Carbs[124] = 64.40;
        Protein[124] = 0.70;

        food[125] = "Apple-Sweet Potato Juice Babyfood";
        calories[125] = 48;
        fat[125] = 0.10;
        Carbs[125] = 11.40;
        Protein[125] = 0.30;

        food[126] = "Apricots";
        calories[126] = 48;
        fat[126] = 0.39;
        Carbs[126] = 11.12;
        Protein[126] = 1.40;

        food[127] = "Apricots (Solids And Liquids With Skin, Extra Light Syrup Pack, Canned)";
        calories[127] = 49;
        fat[127] = 0.10;
        Carbs[127] = 12.50;
        Protein[127] = 0.60;

        food[128] = "Apricots (Solids And Liquids With Skin, Heavy Syrup Pack, Canned)";
        calories[128] = 83;
        fat[128] = 0.08;
        Carbs[128] = 21.47;
        Protein[128] = 0.53;

        food[129] = "Apricots (Low Moisture, Uncooked)";
        calories[129] = 320;
        fat[129] = 0.62;
        Carbs[129] = 82.89;
        Protein[129] = 4.90;

        food[130] = "Apricots (Low Moisture, Stewed)";
        calories[130] = 126;
        fat[130] = 0.24;
        Carbs[130] = 32.62;
        Protein[130] = 1.93;

        food[131] = "Apricot Nectar (Without Added Ascorbic Acid, Canned)";
        calories[131] = 56;
        fat[131] = 0.09;
        Carbs[131] = 14.39;
        Protein[131] = 0.37;

        food[132] = "Apricots (Drained, Heavy Syrup, Canned) ";
        calories[132] = 83;
        fat[132] = 0.11;
        Carbs[132] = 21.31;
        Protein[132] = 0.64;

        food[133] = "Apricot Nectar (With Added Ascorbic Acid, Canned)";
        calories[133] = 56;
        fat[133] = 0.09;
        Carbs[133] = 14.39;
        Protein[133] = 0.37;

        food[134] = "Apricots (Solids And Liquids With Skin, Water Pack, Canned)";
        calories[134] = 27;
        fat[134] = 0.16;
        Carbs[134] = 6.39;
        Protein[134] = 0.71;

        food[135] = "Apricots (Solids And Liquids Without Skin, Water Pack, Canned)";
        calories[135] = 22;
        fat[135] = 0.03;
        Carbs[135] = 5.48;
        Protein[135] = 0.69;

        food[136] = "Apricots (Solids And Liquids With Skin, Juice Pack, Canned)";
        calories[136] = 48;
        fat[136] = 0.04;
        Carbs[136] = 12.34;
        Protein[136] = 0.63;

        food[137] = "Apricots (Solids And Liquids With Skin, Light Syrup Pack, Canned)";
        calories[137] = 63;
        fat[137] = 0.05;
        Carbs[137] = 16.49;
        Protein[137] = 0.53;

        food[138] = "Apricots (Solids And Liquids Without Skin, Heavy Syrup Pack, Canned)";
        calories[138] = 83;
        fat[138] = 0.09;
        Carbs[138] = 21.45;
        Protein[138] = 0.51;

        food[139] = "Apricots (Solids And Liquids Without Skin, Extra Heavy Syrup Pack, Canned)";
        calories[139] = 96;
        fat[139] = 0.04;
        Carbs[139] = 24.85;
        Protein[139] = 0.55;

        food[140] = "Apricots (Sweetened, Frozen)";
        calories[140] = 98;
        fat[140] = 0.10;
        Carbs[140] = 25.10;
        Protein[140] = 0.70;

        food[141] = "Aquafina Pepsi Non-Carbonated Bottled Water";
        calories[141] = 0;
        fat[141] = 0.00;
        Carbs[141] = 0.00;
        Protein[141] = 0.00;

        food[142] = "Arrowhead";
        calories[142] = 99;
        fat[142] = 0.29;
        Carbs[142] = 20.23;
        Protein[142] = 5.33;

        food[143] = "Arrowhead (Without Salt, Drained, Cooked, Boiled)";
        calories[143] = 78;
        fat[143] = 0.10;
        Carbs[143] = 16.14;
        Protein[143] = 4.49;

        food[144] = "Arrowhead (With Salt, Drained, Cooked, Boiled)";
        calories[144] = 78;
        fat[144] = 0.10;
        Carbs[144] = 16.14;
        Protein[144] = 4.49;

        food[145] = "Archway Home Style Cookies,Gourmet Chocolate Chip N' Toffee";
        calories[145] = 468;
        fat[145] = 21.89;
        Carbs[145] = 64.00;
        Protein[145] = 4.40;

        food[146] = "Arrowroot Cookies Babyfood";
        calories[146] = 442;
        fat[146] = 14.30;
        Carbs[146] = 71.20;
        Protein[146] = 7.60;

        food[147] = "Artichokes (Globe Or French)";
        calories[147] = 47;
        fat[147] = 0.15;
        Carbs[147] = 10.51;
        Protein[147] = 3.27;

        food[148] = "Artichokes (Globe Or French) (Without Salt, Drained, Cooked, Boiled)";
        calories[148] = 50;
        fat[148] = 0.16;
        Carbs[148] = 11.18;
        Protein[148] = 3.48;

        food[149] = "Artichokes (Globe Or French) (Without Salt, Frozen, Drained, Cooked, Boiled)";
        calories[149] = 45;
        fat[149] = 0.50;
        Carbs[149] = 9.18;
        Protein[149] = 3.11;

        food[150] = "Arrowroot";
        calories[150] = 65;
        fat[150] = 0.20;
        Carbs[150] = 13.39;
        Protein[150] = 4.24;

        food[151] = "Artichokes (Globe Or French) (With Salt, Drained, Cooked, Boiled)";
        calories[151] = 50;
        fat[151] = 0.16;
        Carbs[151] = 11.18;
        Protein[151] = 3.48;

        food[152] = "Artichokes (Globe Or French) (With Salt, Frozen, Drained, Cooked, Boiled)";
        calories[152] = 45;
        fat[152] = 0.50;
        Carbs[152] = 9.18;
        Protein[152] = 3.11;

        food[153] = "Arrowroot Flour";
        calories[153] = 357;
        fat[153] = 0.10;
        Carbs[153] = 88.15;
        Protein[153] = 0.30;

        food[154] = "Ascorbic Acid Added Canned Minced Pork And Chicken Lite Luncheon Meat Hormel Spam";
        calories[154] = 191;
        fat[154] = 13.90;
        Carbs[154] = 1.35;
        Protein[154] = 15.23;

        food[155] = "Artichokes (Globe Or French, Unprepared, Frozen)";
        calories[155] = 38;
        fat[155] = 0.43;
        Carbs[155] = 7.76;
        Protein[155] = 2.63;

        food[156] = "Arugula (Rocket)";
        calories[156] = 25;
        fat[156] = 0.66;
        Carbs[156] = 3.65;
        Protein[156] = 2.58;

        food[157] = "Artificial Blueberry Muffin Mix, Dry";
        calories[157] = 407;
        fat[157] = 8.70;
        Carbs[157] = 77.45;
        Protein[157] = 4.70;

        food[158] = "Artificially Flavored Keebler Golden Vanilla Wafers Keebler";
        calories[158] = 475;
        fat[158] = 19.50;
        Carbs[158] = 69.70;
        Protein[158] = 5.20;

        food[159] = "Ascidians (Tunughnak) (Alaska Native)";
        calories[159] = 29;
        fat[159] = 0.50;
        Carbs[159] = 2.20;
        Protein[159] = 3.80;

        food[160] = "Asian Pears";
        calories[160] = 42;
        fat[160] = 0.23;
        Carbs[160] = 10.65;
        Protein[160] = 0.50;

        food[161] = "Asparagus";
        calories[161] = 20;
        fat[161] = 0.12;
        Carbs[161] = 3.88;
        Protein[161] = 2.20;

        food[162] = "Asparagus (Drained, Cooked, Boiled)";
        calories[162] = 22;
        fat[162] = 0.22;
        Carbs[162] = 4.11;
        Protein[162] = 2.40;

        food[163] = "Asparagus (Solids And Liquids, Canned)";
        calories[163] = 15;
        fat[163] = 0.18;
        Carbs[163] = 2.47;
        Protein[163] = 1.80;

        food[164] = "Asparagus (Drained Solids, Canned)";
        calories[164] = 19;
        fat[164] = 0.65;
        Carbs[164] = 2.46;
        Protein[164] = 2.14;

        food[165] = "Asparagus (No Salt Added, Solids And Liquids, Canned)";
        calories[165] = 15;
        fat[165] = 0.18;
        Carbs[165] = 2.48;
        Protein[165] = 1.80;

        food[166] = "Assorted Flavors Kashi Pillows By Kellogg Cereals Ready-To-Eat";
        calories[166] = 370;
        fat[166] = 2.40;
        Carbs[166] = 84.50;
        Protein[166] = 5.90;

        food[167] = "Asparagus (Unprepared, Frozen)";
        calories[167] = 24;
        fat[167] = 0.23;
        Carbs[167] = 4.10;
        Protein[167] = 3.23;

        food[168] = "Asparagus (Without Salt, Frozen, Drained, Cooked, Boiled)";
        calories[168] = 18;
        fat[168] = 0.42;
        Carbs[168] = 1.92;
        Protein[168] = 2.95;

        food[169] = "Asparagus (With Salt, Drained, Cooked, Boiled)";
        calories[169] = 22;
        fat[169] = 0.22;
        Carbs[169] = 4.11;
        Protein[169] = 2.40;

        food[170] = "Asparagus (With Salt, Frozen, Drained, Cooked, Boiled)";
        calories[170] = 18;
        fat[170] = 0.42;
        Carbs[170] = 1.92;
        Protein[170] = 2.95;

        food[171] = "Atlantic Cod";
        calories[171] = 82;
        fat[171] = 0.67;
        Carbs[171] = 0.00;
        Protein[171] = 17.81;

        food[172] = "Atlantic And Pacific Halibut";
        calories[172] = 110;
        fat[172] = 2.29;
        Carbs[172] = 0.00;
        Protein[172] = 20.81;

        food[173] = "Atlantic And Pacific Halibut (Fish) (Cooked, Dry Heat)";
        calories[173] = 140;
        fat[173] = 2.94;
        Carbs[173] = 0.00;
        Protein[173] = 26.69;

        food[174] = "Atlantic Cod (Cooked, Dry Heat) ";
        calories[174] = 105;
        fat[174] = 0.86;
        Carbs[174] = 0.00;
        Protein[174] = 22.83;

        food[175] = "Atlantic Cod (Solids And Liquids, Canned)";
        calories[175] = 105;
        fat[175] = 0.86;
        Carbs[175] = 0.00;
        Protein[175] = 22.76;

        food[176] = "Atlantic Croaker";
        calories[176] = 104;
        fat[176] = 3.17;
        Carbs[176] = 0.00;
        Protein[176] = 17.78;

        food[177] = "Atlantic Croaker (Cooked, Bread And Fried)";
        calories[177] = 221;
        fat[177] = 12.67;
        Carbs[177] = 7.54;
        Protein[177] = 18.20;

        food[178] = "Atlantic Herring";
        calories[178] = 158;
        fat[178] = 9.40;
        Carbs[178] = 0.00;
        Protein[178] = 17.96;

        food[179] = "Atlantic Herring (Cooked, Dry Heat)";
        calories[179] = 203;
        fat[179] = 11.59;
        Carbs[179] = 0.00;
        Protein[179] = 23.03;

        food[180] = "Atlantic Mackerel";
        calories[180] = 205;
        fat[180] = 13.89;
        Carbs[180] = 0.00;
        Protein[180] = 18.60;

        food[181] = "Atlantic Mackerel (Cooked, Dry Heat)";
        calories[181] = 262;
        fat[181] = 17.81;
        Carbs[181] = 0.00;
        Protein[181] = 23.85;

        food[182] = "Atlantic Ocean Perch";
        calories[182] = 94;
        fat[182] = 1.63;
        Carbs[182] = 0.00;
        Protein[182] = 18.62;

        food[183] = "Atlantic Ocean Perch (Cooked, Dry Heat)";
        calories[183] = 121;
        fat[183] = 2.09;
        Carbs[183] = 0.00;
        Protein[183] = 23.88;

        food[184] = "Atlantic Pollock (Fish)";
        calories[184] = 92;
        fat[184] = 0.98;
        Carbs[184] = 0.00;
        Protein[184] = 19.44;

        food[185] = "Atlantic Pollock (Fish) (Cooked, Dry Heat)";
        calories[185] =118;
        fat[185] = 1.26;
        Carbs[185] = 0.00;
        Protein[185] = 24.92;

        food[186] = "Au Gratin Potatoes (Using Butter)";
        calories[186] = 132;
        fat[186] = 7.59;
        Carbs[186] = 11.27;
        Protein[186] = 5.06;

        food[187] = "Au Gratin Potatoes (Dry Mix)";
        calories[187] = 314;
        fat[187] = 3.70;
        Carbs[187] = 74.31;
        Protein[187] = 8.90;

        food[188] = "Au Gratin Potatoes (Dry Mix, Prepared With Water, Whole Milk And Butter)";
        calories[188] = 93;
        fat[188] = 4.12;
        Carbs[188] = 12.48;
        Protein[188] = 2.30;

        food[189] = "Au Gatin Potatoes (Using Margarine)";
        calories[189] = 132;
        fat[189] = 7.59;
        Carbs[189] = 11.27;
        Protein[189] = 5.06;

        food[190] = "Atlantic Sardine (Drained Solids With Bone In Oil, Canned)";
        calories[190] = 208;
        fat[190] = 11.45;
        Carbs[190] = 0.00;
        Protein[190] = 24.62;

        food[191] = "Atlantic Wolfish";
        calories[191] = 96;
        fat[191] = 2.39;
        Carbs[191] = 0.00;
        Protein[191] = 17.50;

        food[192] = "Atlantic Wolfish (Cooked, Dry Heat)";
        calories[192] = 123;
        fat[192] = 3.06;
        Carbs[192] = 0.00;
        Protein[192] = 22.44;

        food[193] = "Atlantic Salmon (Farmed)";
        calories[193] = 183;
        fat[193] = 10.85;
        Carbs[193] = 0.00;
        Protein[193] = 19.90;


        food[194] = "Au Jus Gravy (Canned)";
        calories[194] = 16;
        fat[194] = 0.20;
        Carbs[194] = 2.50;
        Protein[194] = 1.20;

        food[195] = "Au Jus Gravy (Dry)";
        calories[195] = 313;
        fat[195] = 9.63;
        Carbs[195] = 47.49;
        Protein[195] = 9.20;

        food[196] = "Australian Lamb (Trimmed To 1/8 Fat)";
        calories[196] = 229;
        fat[196] = 16.97;
        Carbs[196] = 0.00;
        Protein[196] = 17.84;

        food[197] = "Australian Lamb (Lean Only, Trimmed To 1/8 Fat)";
        calories[197] = 142;
        fat[197] = 6.18;
        Carbs[197] = 0.00;
        Protein[197] = 20.25;

        food[198] = "Australian Lamb (Lean Only, Trimmed To 1/8 Fat, Cooked)";
        calories[198] = 201;
        fat[198] = 9.63;
        Carbs[198] = 0.00;
        Protein[198] = 26.71;


        food[199] = "Aunt Bea's Pound Cake Cookie Archway Home Style Cookies";
        calories[199] = 402;
        fat[199] = 15.64;
        Carbs[199] = 60.66;
        Protein[199] = 5.17;

        food[200] = "Australian Lamb (Trimmed To 1/8 Fat, Cooked)";
        calories[200] = 256;
        fat[200] = 16.82;
        Carbs[200] = 0.00;
        Protein[200] = 24.52;

        food[201] = "Australian Lamb Fat";
        calories[201] = 648;
        fat[201] = 68.87;
        Carbs[201] = 0.00;
        Protein[201] = 6.27;

        food[202] = "Australian Lamb Fat (Cooked)";
        calories[202] = 639;
        fat[202] = 66.40;
        Carbs[202] = 0.00;
        Protein[202] = 9.42;

        food[203] = "Australian Lamb Foreshank (Trimmed To 1/8 Fat)";
        calories[203] =195 ;
        fat[203] = 12.68;
        Carbs[203] = 0.00;
        Protein[203] = 18.85;

        food[204] = "Australian Lamb Foreshank (Lean Only, Trimmed To 1/8 Fat)";
        calories[204] = 123;
        fat[204] = 3.81;
        Carbs[204] = 0.00;
        Protein[204] = 20.83;

        food[205] = "Australian Lamb Foreshank (Lean Only, Trimmed To 1/8 Fat, Cooked, Braised) ";
        calories[205] = 165;
        fat[205] = 5.22;
        Carbs[205] = 0.00;
        Protein[205] = 27.50;

        food[206] = "Australian Lamb Foreshank (Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[206] = 236;
        fat[206] = 14.44;
        Carbs[206] = 0.00;
        Protein[206] = 24.78;

        food[207] = "Australian Lamb Leg (Shank Half, Trimmed To 1/8 Fat)";
        calories[207] = 201;
        fat[207] = 13.48;
        Carbs[207] = 0.00;
        Protein[207] = 18.59;

        food[208] = "Australian Lamb Leg (Shank Half, Lean Only, Trimmed To 1/8 Fat)";
        calories[208] = 133;
        fat[208] = 5.10;
        Carbs[208] = 0.00;
        Protein[208] = 20.45;


        food[209] = "Australian Lamb Leg (Shank Half, Lean Only, Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[209] = 182;
        fat[209] = 7.27;
        Carbs[209] = 0.00;
        Protein[209] = 27.18;

        food[210] = "Australian Lamb Leg (Center Slice, Bone-In, Trimmed To 1/8 Fat)";
        calories[210] = 195;
        fat[210] = 12.56;
        Carbs[210] = 0.00;
        Protein[210] = 19.17;

        food[211] = "Australian Lamb Leg (Center Slice, Bone-In, Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[211] = 215;
        fat[211] = 11.78;
        Carbs[211] = 0.00;
        Protein[211] = 25.54;

        food[212] = "Australian Lamb Leg (Center Slice, Bone-In, Lean Only, Trimmed To 1/8 Fat)";
        calories[212] = 143;
        fat[212] = 6.08;
        Carbs[212] = 0.00;
        Protein[212] = 20.65;

        food[213] = "Australian Lamb Leg (Center Slice, Bone-In, Lean Only, Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[213] = 183;
        fat[213] = 7.68;
        Carbs[213] = 0.00;
        Protein[213] = 26.75;
        food[214] = "Australian Lamb Leg (Shank Half, Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[214] = 231;
        fat[214] = 13.69;
        Carbs[214] = 0.00;
        Protein[214] = 25.25;

        food[215] = "Australian Lamb Leg (Sirloin Half, Boneless, Lean Only, Trimmed To 1/8 Fat)";
        calories[215] = 138;
        fat[215] = 5.64;
        Carbs[215] = 0.00;
        Protein[215] = 20.48;

        food[216] = "Australian Lamb Leg (Sirloin Chops, Boneless, Trimmed To 1/8 Fat)";
        calories[216] = 208;
        fat[216] = 14.38;
        Carbs[216] = 0.00;
        Protein[216] = 18.33;

        food[217] = "Australian Lamb Leg (Sirloin Chops, Boneless, Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[217] = 235;
        fat[217] = 13.83;
        Carbs[217] = 0.00;
        Protein[217] = 25.75;

        food[218] = "Australian Lamb Leg (Sirloin Chops, Boneless, Lean Only, Trimmed To 1/8 Fat)";
        calories[28] = 132;
        fat[218] = 4.91;
        Carbs[218] = 0.00;
        Protein[218] = 20.43;

        food[219] = "Australian Lamb Leg (Sirloin Chops, Boneless, Lean Only, Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[219] = 188;
        fat[219] = 7.80;
        Carbs[219] = 0.00;
        Protein[219] = 27.63;

        food[220] = "Australian Lamb Leg (Sirloin Half, Boneless, Lean Only, Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[220] = 215;
        fat[220] = 10.65;
        Carbs[220] = 0.00;
        Protein[220] = 27.75;

        food[221] = "Australian Lamb Leg (Sirloin Half, Boneless, Trimmed To 1/8 Fat)";
        calories[221] = 254;
        fat[221] = 20.00;
        Carbs[221] = 0.00;
        Protein[221] = 17.25;

        food[222] = "Australian Lamb Leg (Whole (Shank And Sirloin), Trimmed To 1/8 Fat)";
        calories[222] =215;
        fat[222] = 15.19;
        Carbs[222] = 0.00;
        Protein[222] = 18.24;

        food[223] = "Australian Lamb Leg (Whole (Shank And Sirloin), Lean Only, Trimmed To 1/8 Fat)";
        calories[223] = 135;
        fat[223] = 5.23;
        Carbs[223] = 0.00;
        Protein[223] = 20.46;

        food[224] = "Australian Lamb Leg (Whole (Shank And Sirloin), Lean Only, Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[224] = 190;
        fat[224] = 8.10;
        Carbs[224] = 0.00;
        Protein[224] = 27.31;

        food[225] = "Australian Lamb Leg (Sirloin Half, Boneless, Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[225] = 281;
        fat[225] = 19.38;
        Carbs[225] = 0.00;
        Protein[225] = 24.88;

        food[226] = "Australian Lamb Leg (Whole (Shank And Sirloin), Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[226] = 244;
        fat[226] = 15.13;
        Carbs[226] = 0.00;
        Protein[226] = 15.16;

        food[227] = "Australian Lamb Loin (Trimmed To 1/8 Fat)";
        calories[227] = 203;
        fat[227] = 13.38;
        Carbs[227] = 0.00;
        Protein[227] = 19.32;

        food[228] = "Australian Lamb Loin (Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[228] = 219;
        fat[228] = 12.25;
        Carbs[228] = 0.00;
        Protein[228] = 25.49;

        food[229] = "Australian Lamb Loin (Lean Only, Trimmed To 1/8 Fat)";
        calories[229] = 146;
        fat[229] = 6.24;
        Carbs[229] = 0.00;
        Protein[229] = 21.00;

        food[230] = "Australian Lamb Loin (Lean Only, Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[230] = 192;
        fat[230] = 8.75;
        Carbs[230] = 0.00;
        Protein[230] = 26.53;

        food[231] = "Australian Lamb Ribs (Trimmed To 1/8 Fat) ";
        calories[231] = 289;
        fat[231] = 24.20;
        Carbs[231] = 0.00;
        Protein[231] = 16.46 ;

        food[232] = "Australian Lamb Ribs (Lean Only, Trimmed To 1/8 Fat)";
        calories[232] = 160;
        fat[232] = 8.20;
        Carbs[232] = 0.00;
        Protein[232] = 20.12;

        food[233] = "Australian Lamb Ribs (Lean Only, Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[233] = 210;
        fat[233] = 11.60;
        Carbs[233] = 0.00;
        Protein[233] = 24.63;

        food[234] = "Australian Lamb Ribs (Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[234] = 277;
        fat[234] = 20.21;
        Carbs[234] = 0.00;
        Protein[234] = 22.24;

        food[235] = "Australian Lamb Shoulder Arm And Blade (Lean Only, Trimmed To 1/8 Fat)";
        calories[235] = 155;
        fat[235] = 8.01;
        Carbs[235] = 0.00;
        Protein[235] = 19.36;

        food[236] = "Australian Lamb Shoulder Arm (Trimmed To 1/8 Fat)";
        calories[236] = 243;
        fat[236] = 18.89;
        Carbs[236] = 0.00;
        Protein[236] = 17.06;

        food[237] = "Australian Lamb Shoulder Arm (Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[237] = 311;
        fat[237] = 20.38;
        Carbs[237] = 0.00;
        Protein[237] = 29.70;

        food[238] = "Australian Lamb Shoulder Arm (Lean Only, Trimmed To 1/8 Fat)";
        calories[238] = 137;
        fat[238] = 5.81;
        Carbs[238] = 0.00;
        Protein[238] = 19.88;


        food[239] = "Australian Lamb Shoulder Arm (Lean Only, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[239] = 238;
        fat[239] = 10.23;
        Carbs[239] = 0.00;
        Protein[239] = 34.17;

        food[240] = "Australian Lamb Shoulder Arm And Blade (Lean Only, Trimmed To 1/8 Fat, Cooked)";
        calories[240] = 233;
        fat[240] = 13.44;
        Carbs[240] = 0.00;
        Protein[240] = 26.18;

        food[241] = "Australian Lamb Shoulder Arm And Blade (Trimmed To 1/8 Fat)";
        calories[241] = 256;
        fat[241] = 20.47;
        Carbs[241] = 0.00;
        Protein[241] = 16.68;

        food[242] = "Australian Lamb Shoulder Arm And Blade (Trimmed To 1/8 Fat, Cooked)";
        calories[242] = 296;
        fat[242] =21.65;
        Carbs[242] = 0.00;
        Protein[242] = 23.58;

        food[243] = "Australian Lamb Shoulder Blade (Trimmed To 1/8 Fat)";
        calories[243] = 262;
        fat[243] = 21.28;
        Carbs[243] = 0.00;
        Protein[243] = 16.48;

        food[244] = "Australian Lamb Shoulder Blade (Lean Only, Trimmed To 1/8 Fat)";
        calories[244] = 164;
        fat[244] = 9.09;
        Carbs[244] = 0.00;
        Protein[244] = 19.10;

        food[245] = "Australian Lamb Shoulder Blade (Lean Only, Trimmed To 1/8 Fat, Broiled, Cooked)";
        calories[245] = 231;
        fat[245] = 14.38;
        Carbs[245] = 0.00;
        Protein[245] = 23.83;

        food[246] = "Avocado Vegetable Oil";
        calories[246] = 884;
        fat[246] = 100.00;
        Carbs[246] = 0.00;
        Protein[246] = 0.00;

        food[247] = "Avocados";
        calories[247] = 160;
        fat[247] = 14.66;
        Carbs[247] = 8.53;
        Protein[247] = 2.00;

        food[248] = "Australian Lamb Shoulder Blade (Trimmed To 1/8 Fat, Cooked, Broiled)";
        calories[248] = 291;
        fat[248] = 22.03;
        Carbs[248] = 0.00;
        Protein[248] = 21.71;

        food[249] = "Babassu Vegetable Oil";
        calories[249] = 884;
        fat[249] = 100.00;
        Carbs[249] = 0.00;
        Protein[249] = 0.00;

        food[250] = "Baby Carrots";
        calories[250] = 35;
        fat[250] = 0.13;
        Carbs[250] = 8.24;
        Protein[250] = 0.64;

        food[251] = "Baby Lima Beans (Immature Seeds, Frozen)";
        calories[25] = 132;
        fat[251] = 0.44;
        Carbs[251] = 25.13;
        Protein[251] = 7.59;

        food[252] = "Baby Lima Beans (Mature Seeds)";
        calories[252] = 335;
        fat[252] = 0.93;
        Carbs[252] = 62.83;
        Protein[252] = 20.62;

        food[253] = "Baby Lima Beans (Mature Seeds, Without Salt, Cooked, Boiled)";
        calories[253] = 126;
        fat[253] = 0.38;
        Carbs[253] = 23.31;
        Protein[253] = 8.04;

        food[254] = "Baby Lima Beans (Mature Seeds, With Salt, Cooked, Boiled)";
        calories[254] = 126;
        fat[254] = 0.38;
        Carbs[254] = 23.31;
        Protein[254] = 8.04;

        food[255] = "Bacon (Cured)";
        calories[255] = 458;
        fat[255] = 45.04;
        Carbs[255] = 0.66;
        Protein[255] = 11.60;

        food[256] = "Bacon (Cured, Broiled, Pan-Fried Or Roasted, Cooked) ";
        calories[256] = 541;
        fat[256] = 41.78;
        Carbs[256] = 1.43;
        Protein[256] = 37.04;

        food[257] = "Bacon (Cured, Baked, Cooked)";
        calories[257] = 548;
        fat[257] = 43.27;
        Carbs[257] = 1.35;
        Protein[257] = 35.73;

        food[258] = "Bacon (Cured, Microwaved, Cooked)";
        calories[258] = 505;
        fat[258] = 37.27;
        Carbs[258] = 1.05;
        Protein[258] = 38.62;

        food[259] = "Bacon (Cured, Pan-Fried, Cooked)";
        calories[259] = 533;
        fat[259] = 40.30;
        Carbs[259] = 1.50;
        Protein[259] = 38.34;

        food[260] = "Baby Zucchini";
        calories[260] = 21;
        fat[26] = 0.40;
        Carbs[260] = 3.11;
        Protein[260] = 2.71;

        food[261] = "Baby Ruth Bar Nestle Candies";
        calories[261] = 464;
        fat[261] = 25.00;
        Carbs[261] = 61.80;
        Protein[261] = 7.10;

        food[262] = "Bacon (Cured, Reduced Sodium, Broiled, Pan-Fried Or Roasted, Cooked)";
        calories[262] = 541;
        fat[262] = 41.78;
        Carbs[262] =1.43;
        Protein[262] = 37.04;

        food[263] = "Bacon Grease";
        calories[263] = 897;
        fat[263] = 99.50;
        Carbs[263] = 0.00;
        Protein[263] = 0.00;

        food[264] = "Bacon And Beef Sticks";
        calories[264] = 517;
        fat[264] = 44.20;
        Carbs[264] = 0.80;
        Protein[264] = 29.10;

        food[265] = "Bacon Fat (Cooked)";
        calories[265] = 898;
        fat[265] = 99.50;
        Carbs[265] = 0.00;
        Protein[265] = 0.07;

        food[266] = "Bacon Ranch Salad With Grilled Chicken Mcdonald's";
        calories[266] = 76;
        fat[266] = 3.29;
        Carbs[266] = 3.74;
        Protein[266] = 9.97;

        food[267] = "Bacon Ranch Salad With Crispy Chicken Mcdonald's";
        calories[267] = 106;
        fat[267] = 5.84;
        Carbs[267] = 7.20;
        Protein[267] = 8.61;

        food[268] = "Bacon And Tomato Salad Dressing";
        calories[268] = 326;
        fat[268] = 35.00;
        Carbs[268] = 2.00;
        Protein[268] = 1.80;

        food[269] = "Bacon Ranch Salad Without Chicken Mcdonald's ";
        calories[269] = 61;
        fat[269] = 3.64;
        Carbs[269] = 4.20;
        Protein[269] = 4.14;

        food[270] = "Bagels (Includes Onion, Poppy, Sesame) (Enriched With Calcium Propionate)";
        calories[270] =257 ;
        fat[270] = 1.62;
        Carbs[270] = 50.50;
        Protein[270] = 10.02;

        food[271] = "Bagged Cereal Fruity Brontosaurus Blasts Quaker Cereals Ready-To-Eat";
        calories[271] = 376;
        fat[271] = 2.45;
        Carbs[271] = 89.27;
        Protein[271] = 3.75;

        food[272] = "Bagels (Includes Onion, Poppy, Sesame) (Without Calcium Propionate, Enriched)";
        calories[272] = 275;
        fat[272] = 1.60;
        Carbs[272] = 53.40;
        Protein[272] = 10.50;

        food[273] = "Bagels (Includes Onion, Poppy, Sesame) (With Calcium Propionate)";
        calories[273] = 275;
        fat[273] = 1.60;
        Carbs[273] = 53.40;
        Protein[273] = 10.50;


        food[274] = "Bagels (Includes Onion, Poppy, Sesame) (Without Calcium Propionate)";
        calories[274] = 275;
        fat[274] = 1.60;
        Carbs[274] = 53.40;
        Protein[274] = 10.50;

        food[275] = "Baked Beans";
        calories[275] = 151;
        fat[275] = 5.15;
        Carbs[275] = 21.39;
        Protein[275] = 5.54;

        food[276] = "Baked Beans With Beef (Canned)";
        calories[276] = 121;
        fat[276] = 3.45;
        Carbs[276] = 16.91;
        Protein[276] = 6.38;

        food[277] = "Baked Beans With Franks (Canned)";
        calories[277] = 142;
        fat[277] = 6.57;
        Carbs[277] = 15.39;
        Protein[277] = 6.75;

        food[278] = "Baked Beans With Pork (Canned)";
        calories[278] = 106;
        fat[278] = 1.55;
        Carbs[278] = 19.98;
        Protein[278] = 5.19;


        food[279] = "Baked Beans With Pork And Sweet Sauce (Canned)";
        calories[279] = 112;
        fat[279] = 1.44;
        Carbs[279] = 21.12;
        Protein[279] = 5.29;

        food[280] = "Baked Beans With Pork And Tomato Sauce (Canned)";
        calories[280] = 94;
        fat[280] = 0.93;
        Carbs[280] = 18.69;
        Protein[280] = 5.15;

        food[281] = "Baked Apple Pie Mcdonald's";
        calories[281] = 323;
        fat[281] = 15.66;
        Carbs[281] = 43.62;
        Protein[281] = 3.07;

        food[282] = "Baked Beans (No Salt Added, Canned)";
        calories[282] = 105;
        fat[282] = 0.40;
        Carbs[282] = 20.61;
        Protein[282] = 4.80;

        food[283] = "Baked Cooked 96% Fat Free) Ham (Water Added Oscar Mayer";
        calories[283] = 104;
        fat[283] = 3.52;
        Carbs[283] = 1.83;
        Protein[283] = 16.30;


        food[284] = "Baked Nabisco Wheat Thins Crackers Nabisco";
        calories[284] = 470;
        fat[284] = 20.00;
        Carbs[284] = 69.10;
        Protein[284] = 8.30;

        food[285] = "Baked Potato Topped With Cheese Sauce";
        calories[285] = 160;
        fat[285] = 9.71;
        Carbs[285] = 15.71;
        Protein[285] = 4.94;

        food[286] = "Baked Potato Topped With Cheese Sauce And Bacon";
        calories[286] = 151;
        fat[286] = 8.66;
        Carbs[286] = 14.86;
        Protein[286] = 6.16;

        food[287] = "Baked Potato Topped With Cheese Sauce And Broccoli";
        calories[287] = 119;
        fat[287] = 6.32;
        Carbs[287] = 13.74;
        Protein[287] = 4.03;

        food[288] = "Baked Potato Topped With Cheese Sauce And Chili";
        calories[288] = 122;
        fat[288] = 5.53;
        Carbs[288] = 14.14;
        Protein[288] = 5.88;


        food[289] = "Baked Potato Topped With Sour Cream And Chives";
        calories[289] = 130;
        fat[289] = 7.39;
        Carbs[289] = 16.56;
        Protein[289] = 2.21;

        food[290] = "Baked Refrigerated Dough Sugar Cookies";
        calories[290] = 484;
        fat[290] = 23.10;
        Carbs[290] = 65.60;
        Protein[290] = 4.70;

        food[291] = "Bakers Yeast (Compressed)";
        calories[291] = 105;
        fat[291] = 1.90;
        Carbs[291] = 18.10;
        Protein[291] = 8.40;

        food[292] = "Bakers Yeast (Active Dry)";
        calories[292] = 295;
        fat[292] = 4.60;
        Carbs[292] = 38.20;
        Protein[292] = 38.30;

        food[293] = "Baking Chocolate Liquid";
        calories[293] = 472;
        fat[293] = 47.70;
        Carbs[293] = 33.90;
        Protein[293] = 12.10;


        food[294] = "Baking Chocolate Mexican Squares";
        calories[294] = 426;
        fat[294] = 15.59;
        Carbs[294] = 77.41;
        Protein[294] = 3.64;

        food[295] = "Balsam-Pear (Bitter Gourd) (Leafy Tips, Without Salt, Drained, Cooked, Boiled)";
        calories[295] = 35;
        fat[295] = 0.20;
        Carbs[295] = 6.78;
        Protein[295] = 3.60;

        food[296] = "Balsam-Pear (Bitter Gourd) (Leafy Tips, With Salt, Drained, Cooked, Boiled)";
        calories[296] = 35;
        fat[296] = 0.20;
        Carbs[296] = 6.78;
        Protein[296] = 3.60;

        food[297] = "Balsam-Pear (Bitter Gourd) (Pods, With Salt, Drained, Cooked, Boiled)";
        calories[297] = 19;
        fat[297] = 0.18;
        Carbs[297] = 4.32;
        Protein[297] = 0.84;

        food[298] = "Baking Powder (Sodium Aluminum Sulfate, Double Acting)";
        calories[298] = 53;
        fat[298] = 0.00;
        Carbs[298] = 27.70;
        Protein[298] = 0.00;


        food[299] = "Baking Powder (Straight Phosphate, Double Acting)";
        calories[299] = 51;
        fat[299] = 0.00;
        Carbs[299] =14.10;
        Protein[299] = 0.10;

        food[300] = "Baking Powder (Low Sodium) ";
        calories[300] = 97;
        fat[300] = 0.40;
        Carbs[300] = 46.90;
        Protein[300] = 0.10;

        food[301] = "Baking Soda";
        calories[301] = 0;
        fat[301] = 0.00;
        Carbs[301] = 0.00;
        Protein[301] = 0.00;

        food[302] = "Baking Chocolate Squares";
        calories[302] = 501;
        fat[302] = 52.31;
        Carbs[302] = 29.84;
        Protein[302] = 12.90;

        food[303] = "Balsam-Pear (Bitter Gourd, Leafy Tips)";
        calories[303] = 30;
        fat[303] = 0.69;
        Carbs[303] = 3.29;
        Protein[303] = 5.30;

        food[304] = "Balsam-Pear (Bitter Gourd, Pods)";
        calories[304] = 17;
        fat[304] = 0.17;
        Carbs[304] = 3.70;
        Protein[304] = 1.00;

        food[305] = "Balsam-Pear (Bitter Gourd) (Pods, Without Salt, Drained, Cooked, Boiled)";
        calories[305] = 19;
        fat[305] = 0.18;
        Carbs[305] = 4.32;
        Protein[305] = 0.84;

        food[306] = "Bamboo Shoots";
        calories[306] = 27;
        fat[306] = 0.30;
        Carbs[306] = 5.20;
        Protein[306] = 2.60;

        food[307] = "Bamboo Shoots (Drained Solids, Canned)";
        calories[307] = 19;
        fat[307] = 0.40;
        Carbs[307] = 3.22;
        Protein[307] = 1.72;

        food[308] = "Bamboo Shoots (With Salt, Drained, Cooked, Boiled) ";
        calories[308] =11 ;
        fat[308] = 0.22;
        Carbs[308] = 1.52;
        Protein[308] = 1.53;

        food[309] = "Bamboo Shoots (Without Salt, Drained, Cooked, Boiled)";
        calories[309] = 12;
        fat[309] = 0.22;
        Carbs[309] = 1.92;
        Protein[309] = 1.53;

        food[310] = "Banana Bread (With Margarine)";
        calories[310] =326 ;
        fat[310] = 10.50;
        Carbs[310] = 54.60;
        Protein[310] = 4.30;

        food[311] = "Banana Cream Pie (Mix, No-Bake Type)";
        calories[311] = 251;
        fat[311] = 12.90;
        Carbs[311] = 31.60;
        Protein[311] = 3.40;

        food[312] = "Banana Cream Pie";
        calories[312] = 269;
        fat[312] = 13.60;
        Carbs[312] = 32.90;
        Protein[312] = 4.40;

        food[313] = "Banana Bread Waffles Kellogg's Eggo Kellogg";
        calories[313] = 272;
        fat[313] = 9.50;
        Carbs[313] = 41.60;
        Protein[313] = 6.80;


        food[314] = "Banana Chips";
        calories[314] = 519;
        fat[314] = 33.60;
        Carbs[314] = 58.40;
        Protein[314] = 2.30;

        food[315] = "Banana Powder Or Dehydrated Bananas";
        calories[315] = 346;
        fat[315] = 1.81;
        Carbs[315] = 88.28;
        Protein[315] = 3.89;

        food[316] = "Banana Pepper";
        calories[316] = 27;
        fat[316] = 0.45;
        Carbs[316] = 5.35;
        Protein[316] = 1.66;

        food[317] = "Banana Puddings (Dry Mix, Instant, With 2% Milk)";
        calories[317] = 105;
        fat[317] = 1.70;
        Carbs[317] = 19.74;
        Protein[317] = 2.76;

        food[318] = "Banana Puddings";
        calories[318] = 127;
        fat[318] = 3.60;
        Carbs[318] = 21.20;
        Protein[318] = 2.40;

        food[319] = "Banana Puddings (Dry Mix)";
        calories[319] = 376;
        fat[319] = 0.40;
        Carbs[319] = 92.90;
        Protein[319] = 0.00;

        food[320] = "Banana Puddings (Dry Mix, Instant, With Added Oil)";
        calories[320] = 386;
        fat[320] = 4.40;
        Carbs[320] = 89.00;
        Protein[320] = 0.00;

        food[321] = "Banana Melon (Navajo)";
        calories[321] = 21;
        fat[321] = 0.20;
        Carbs[321] = 4.06;
        Protein[321] = 0.84;

        food[322] = "Banana Juice With Low Fat Yogurt Babyfood";
        calories[322] = 89;
        fat[322] = 0.80;
        Carbs[322] = 17.54;
        Protein[322] = 2.50;

        food[323] = "Bananas";
        calories[323] = 89;
        fat[323] = 0.33;
        Carbs[323] = 22.84;
        Protein[323] = 1.09;

        food[324] = "Banana Puddings (Dry Mix, With 2% Milk)";
        calories[324] = 101;
        fat[324] = 1.73;
        Carbs[324] = 18.43;
        Protein[324] = 2.90;

        food[325] = "Banana Puddings (Instant, Dry Mix)";
        calories[325] = 367;
        fat[325] = 0.60;
        Carbs[325] = 92.70;
        Protein[325] = 0.00;

        food[326] = "Banana Puddings (Dry Mix, Instant, With Whole Milk)";
        calories[326] = 115;
        fat[326] = 2.87;
        Carbs[326] = 19.63;
        Protein[326] = 2.73;

        food[327] = "Banana Puddings (Dry Mix, With Whole Milk)";
        calories[327] = 111;
        fat[327] = 2.97;
        Carbs[327] = 18.31;
        Protein[327] = 2.86;

        food[328] = "Banana Puddings (Dry Mix, With Added Oil)";
        calories[328] = 387;
        fat[328] = 5.00;
        Carbs[328] = 88.40;
        Protein[328] = 0.00;

        food[329] = "Barbecue Flavor Corn Chips";
        calories[329] = 523;
        fat[329] = 32.70;
        Carbs[329] = 56.20;
        Protein[329] = 7.00;

        food[330] = "Barbecue Flavor Corn Puffs Or Twists (Made With Enriched Masa Flour)";
        calories[330] = 523;
        fat[33] = 32.70;
        Carbs[330] = 56.20;
        Protein[330] = 7.00;

        food[331] = "Barbecue Sauce";
        calories[331] = 75;
        fat[331] = 1.80;
        Carbs[331] = 12.80;
        Protein[331] = 1.80;

        food[332] = "Barbecue Flavor Potato Chips";
        calories[332] = 491;
        fat[332] = 32.40;
        Carbs[332] = 52.80;
        Protein[332] = 7.70;

        food[333] = "Barbecue Flavor Cornnuts";
        calories[333] = 436;
        fat[333] = 14.30;
        Carbs[333] = 71.70;
        Protein[333] = 9.00;

        food[334] = "Barbecue Flavor Pork Skins";
        calories[334] = 538;
        fat[334] = 31.80;
        Carbs[334] = 1.60;
        Protein[334] = 57.90;

        food[335] = "Barbeque Sauce (Low Sodium, Canned)";
        calories[335] = 75;
        fat[335] = 1.80;
        Carbs[335] = 12.80;
        Protein[335] = 1.80;

        food[336] = "Basic 4 General Mills Cereals Ready-To-Eat";
        calories[336] = 367;
        fat[336] = 5.10;
        Carbs[336] = 77.00;
        Protein[336] = 8.00;

        food[337] = "Barbera Wine";
        calories[337] = 86;
        fat[337] = 0.00;
        Carbs[337] = 2.79;
        Protein[337] = 0.07;

        food[338] = "Barley";
        calories[338] = 354;
        fat[338] = 2.30;
        Carbs[338] = 73.48;
        Protein[338] = 12.48;

        food[339] = "Barley Flour Or Meal";
        calories[339] = 345;
        fat[339] = 1.60;
        Carbs[339] = 74.52;
        Protein[339] = 10.50;

        food[340] = "Barley Malt Flour";
        calories[340] = 361;
        fat[340] = 1.84;
        Carbs[340] = 78.30;
        Protein[340] = 10.28;

        food[341] = "Barbeque Sauce Mcdonald's";
        calories[341] = 165;
        fat[341] = 1.22;
        Carbs[341] =  36.93;
        Protein[341] = 1.55;

        food[342] = "Barbeque Chili Beans (Ranch Style, Cooked)";
        calories[342] = 97;
        fat[342] = 1.00;
        Carbs[342] = 16.95;
        Protein[342] = 5.00;

        food[343] = "Basil (Dried)";
        calories[343] = 251;
        fat[343] = 3.98;
        Carbs[343] = 60.96;
        Protein[343] = 14.37;

        food[344] = "Bay Leaf";
        calories[344] = 313;
        fat[344] = 8.36;
        Carbs[344] = 74.97;
        Protein[344] = 7.61;

        food[345] = "Basil";
        calories[345] = 27;
        fat[345] = 0.61;
        Carbs[345] = 4.34;
        Protein[345] = 2.54;

        food[346] = "Bean And Ham Soup (Reduced Sodium, With Water, Canned)";
        calories[346] = 74;
        fat[346] = 1.03;
        Carbs[346] = 13.66;
        Protein[346] = 4.19;

        food[347] = "Bean Burrito Taco Bell";
        calories[347] = 204;
        fat[347] = 6.85;
        Carbs[347] = 27.77;
        Protein[347] = 7.90;

        food[348] = "Bean Beverage";
        calories[348] = 34;
        fat[348] = 0.00;
        Carbs[348] = 5.80;
        Protein[348] = 2.80;

        food[349] = "Bean With Bacon Soup (Condensed)";
        calories[349] = 117;
        fat[349] = 2.10;
        Carbs[349] = 18.00;
        Protein[349] = 6.50;

        food[350] = "Bean With Bacon Soup (Dry Mix, Dehydrated)";
        calories[350] = 370;
        fat[350] = 7.60;
        Carbs[350] = 52.72;
        Protein[350] = 19.38;

        food[351] = "Bean With Pork Soup (Canned, Condensed)";
        calories[351] =129 ;
        fat[351] = 4.42;
        Carbs[351] = 16.97;
        Protein[351] = 5.88;

        food[352] = "Bean With Frankfurters Soup (Canned, Condensed)";
        calories[352] = 142;
        fat[352] = 5.31;
        Carbs[352] = 16.75;
        Protein[352] = 7.60;

        food[353] = "Bean With Frankfurters Soup (With Equal Volume Water, Canned)";
        calories[353] = 75;
        fat[353] = 2.79;
        Carbs[353] = 8.80;
        Protein[353] = 3.99;

        food[354] = "Bean With Bacon Soup (Prepared With Water, Dehydrated)";
        calories[354] = 40;
        fat[354] = 0.81;
        Carbs[354] = 6.18;
        Protein[354] = 2.07;

        food[355] = "Bearnaise Sauce (Dry, Dehydrated)";
        calories[355] = 362;
        fat[355] = 9.00;
        Carbs[355] = 59.74;
        Protein[355] = 14.06;

        food[356] = "Beef And Pork Bologna";
        calories[356] = 308;
        fat[356] = 24.59;
        Carbs[356] = 5.49;
        Protein[356] = 15.20;

        food[357] = "Bear Meat";
        calories[357] = 161;
        fat[357] = 8.30;
        Carbs[357] = 0.00;
        Protein[357] = 20.10;

        food[358] = "Bear Meat (Cooked, Simmered)";
        calories[358] = 259;
        fat[368] = 13.39;
        Carbs[358] = 0.00;
        Protein[358] = 32.42;

        food[359] = "Beaver Meat";
        calories[359] = 146;
        fat[359] = 4.80;
        Carbs[359] = 0.00;
        Protein[359] = 24.05;

        food[360] = "Beaver Meat (Cooked, Roasted) ";
        calories[360] = 212;
        fat[360] = 6.96;
        Carbs[360] = 0.00;
        Protein[360] = 34.85;

        food[361] = "Bearded Seal Oil (Ogruk Oil) (Alaska Native)";
        calories[361] = 900;
        fat[361] = 100.00;
        Carbs[361] = 0.00;
        Protein[361] = 0.00;

        food[362] = "Beef And Pork Bologna (Low Fat)";
        calories[362] = 230;
        fat[362] = 19.30;
        Carbs[362] = 2.60;
        Protein[362] = 11.50;

        food[363] = "Beef Bologna";
        calories[363] = 314;
        fat[363] = 28.19;
        Carbs[363] = 3.98;
        Protein[363] = 10.27;

        food[364] = "Beef And Pork Frankfurter";
        calories[364] = 305;
        fat[364] = 27.64;
        Carbs[364] = 1.72;
        Protein[364] = 11.53;

        food[365] = "Beef And Pork Salami (Cooked)";
        calories[365] = 250;
        fat[365] = 20.11;
        Carbs[365] = 2.25;
        Protein[365] = 13.92;

        food[366] = "Beef And Pork Bratwurst (Smoked)";
        calories[366] = 297;
        fat[366] = 26.34;
        Carbs[366] = 2.00;
        Protein[366] = 12.20;

        food[367] = "Beef Bologna (Low Fat)";
        calories[367] = 204;
        fat[367] = 14.80;
        Carbs[367] = 5.20;
        Protein[367] = 11.80;

        food[368] = "Beef And Pork Frankfurter (Low Fat)";
        calories[368] = 154;
        fat[368] = 10.00;
        Carbs[368] = 4.40;
        Protein[368] = 11.00;

        food[369] = "Beef Bologna (Reduced Sodium)";
        calories[369] = 313;
        fat[369] = 28.40;
        Carbs[369] = 2.00;
        Protein[369] = 11.70;

        food[370] = "Beef Bottom Round (Lean Only, Trimmed To 0 Fat, Choice Grade, Cooked, Braised)";
        calories[370] = 223;
        fat[370] = 9.03;
        Carbs[370] = 0.00;
        Protein[370] = 33.08;

        food[371] = "Beef Bottom Round (Lean Only, Trimmed To 0 Fat, Cooked, Braised)";
        calories[371] = 214;
        fat[371] = 7.67;
        Carbs[371] = 0.00;
        Protein[371] = 34.00;

        food[372] = "Beef Bottom Round (Lean Only, Trimmed To 0 Fat, Cooked, Roasted)";
        calories[372] = 177;
        fat[372] = 6.48;
        Carbs[372] = 0.00;
        Protein[372] = 27.76;

        food[373] = "Beef Bottom Round (Lean Only, Trimmed To 0 Fat, Choice Grade, Cooked, Roasted)";
        calories[373] = 185;
        fat[373] =7.63;
        Carbs[373] = 0.00;
        Protein[373] = 27.23;

        food[374] = "Beef Bottom Round (Lean Only, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[374] = 206;
        fat[374] = 6.30;
        Carbs[374] = 0.00;
        Protein[374] = 34.93;

        food[375] = "Beef Bottom Round (Lean Only, Trimmed To 1/2 Fat, Prime Grade)";
        calories[375] = 159;
        fat[375] = 7.30;
        Carbs[375] = 0.00;
        Protein[375] = 21.87;

        food[376] = "Beef Bottom Round (Lean Only, Trimmed To 1/2 Fat, Prime Grade, Cooked, Braised)";
        calories[376] = 249;
        fat[376] = 12.65;
        Carbs[376] = 0.00;
        Protein[376] = 31.59;

        food[377] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat)";
        calories[377] = 128;
        fat[377] = 4.31;
        Carbs[377] = 0.00;
        Protein[377] = 22.19;

        food[378] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Cooked)";
        calories[378] = 163;
        fat[378] = 5.72;
        Carbs[378] = 0.00;
        Protein[378] = 28.00;

        food[379] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[379] = 216;
        fat[379] = 7.73;
        Carbs[379] = 0.00;
        Protein[379] = 34.34;

        food[380] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Choice Grade)";
        calories[380] = 140;
        fat[380] = 4.96;
        Carbs[380] = 0.00;
        Protein[380] = 22.22;

        food[381] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Choice Grade, Cooked, Roasted)";
        calories[381] = 179;
        fat[381] = 6.77;
        Carbs[381] = 0.00;
        Protein[381] = 27.56;

        food[382] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Choice Grade, Cooked, Braised)";
        calories[382] = 228;
        fat[382] = 9.02;
        Carbs[382] = 0.00;
        Protein[382] = 34.22;

        food[383] = "Beef Bottom Round (Trimmed To 0 Fat, Cooked, Braised)";
        calories[383] = 223;
        fat[383] =8.86;
        Carbs[383] = 0.00;
        Protein[383] = 33.56;

        food[384] = "Beef Bottom Round (Trimmed To 0 Fat, Choice Grade, Cooked, Braised)";
        calories[384] = 230;
        fat[384] = 10.00;
        Carbs[384] = 0.00;
        Protein[384] = 32.73;

        food[385] = "Beef Bottom Round (Trimmed To 0 Fat, Choice Grade, Cooked, Roasted)";
        calories[385] = 199;
        fat[385] = 9.37;
        Carbs[385] = 0.00;
        Protein[385] = 26.76;

        food[386] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Select Grade, Cooked, Roasted)";
        calories[386] = 164;
        fat[386] = 4.67;
        Carbs[386] = 0.00;
        Protein[386] = 28.45;

        food[387] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[387] = 205;
        fat[387] = 6.43;
        Carbs[387] = 0.00;
        Protein[387] = 34.46;

        food[388] = "Beef Bottom Round (Lean Only, Trimmed To 1/8 Fat, Select Grade)";
        calories[388] = 128;
        fat[388] = 3.66;
        Carbs[388] = 0.00;
        Protein[388] = 22.18;

        food[389] = "Beef Bottom Round (Trimmed To 0 Fat, Cooked, Roasted)";
        calories[389] = 187;
        fat[389] = 7.72;
        Carbs[389] = 0.00;
        Protein[389] = 27.42;

        food[390] = "Beef Bottom Round (Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[390] = 217;
        fat[390] = 7.72;
        Carbs[390] = 0.00;
        Protein[390] = 34.39;

        food[391] = "Beef Bottom Round (Trimmed To 0 Fat, Select Grade, Cooked, Roasted)";
        calories[391] = 175;
        fat[391] = 6.06;
        Carbs[391] = 0.00;
        Protein[391] = 28.08;

        food[392] = "Beef Bottom Round (Trimmed To 1/2 Fat, Prime Grade)";
        calories[392] = 225;
        fat[392] = 15.38;
        Carbs[392] = 0.00;
        Protein[392] = 20.13;

        food[393] = "Beef Bottom Round (Trimmed To 1/2 Fat, Prime Grade, Cooked, Braised)";
        calories[393] = 297;
        fat[393] = 19.11;
        Carbs[393] = 0.00;
        Protein[393] = 29.24;

        food[394] = "Beef Bottom Round (Trimmed To 1/8 Fat)";
        calories[394] = 192;
        fat[394] = 11.54;
        Carbs[394] = 0.00;
        Protein[394] = 20.70;

        food[395] = "Beef Bottom Round (Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[395] = 247;
        fat[395] = 11.87;
        Carbs[395] = 0.00;
        Protein[395] = 32.76;

        food[396] = "Beef Bottom Round (Trimmed To 1/8 Fat, Cooked, Roasted)";
        calories[396] = 218;
        fat[396] =11.64;
        Carbs[396] = 0.00;
        Protein[396] = 26.41;

        food[397] = "Beef Bottom Round (Trimmed To 1/8 Fat, Choice Grade)";
        calories[397] = 198;
        fat[397] = 12.15;
        Carbs[397] = 0.00;
        Protein[397] = 20.71;

        food[398] = "Beef Bottom Round (Trimmed To 1/8 Fat, Choice Grade, Cooked, Braised)";
        calories[398] = 254;
        fat[398] = 12.56;
        Carbs[398] = 0.00;
        Protein[398] = 32.85;

        food[399] = "Beef Bottom Round (Trimmed To 1/8 Fat, Choice Grade, Cooked, Roasted)";
        calories[399] = 223;
        fat[399] = 12.44;
        Carbs[399] = 0.00;
        Protein[399] = 26.05;

        food[400] = "Beef Bottom Round (Trimmed To 1/8 Fat, Select Grade)";
        calories[400] = 187;
        fat[400] = 10.93;
        Carbs[400] = 0.00;
        Protein[400] = 20.68;

        food[401] = "Beef Bottom Round (Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[401] = 240;
        fat[401] = 11.19;
        Carbs[401] = 0.00;
        Protein[401] = 32.67;

        food[402] = "Beef Bottom Round (Trimmed To 1/8 Fat, Select Grade, Cooked, Roasted)";
        calories[402] = 212;
        fat[402] = 18.85;
        Carbs[402] = 0.00;
        Protein[402] = 26.77;

        food[403] = "Beef Bottom Round Roast (Lean Only, Trimmed To 1/8 Fat, Select Grade, Cooked, Roasted)";
        calories[403] = 169;
        fat[403] = 5.33;
        Carbs[403] = 0.00;
        Protein[403] = 28.29;

        food[404] = "Beef Bottom Sirloin (Tri-Tip Roast, Lean Only, Trimmed To 0 Fat)";
        calories[404] = 142;
        fat[404] = 5.63;
        Carbs[404] = 0.00;
        Protein[404] = 21.26;

        food[405] = "Beef Bottom Sirloin (Tri-Tip Roast, Lean Only, Trimmed To 0 Fat, Choice Grade, Cooked, Roasted)";
        calories[405] = 193;
        fat[405] = 9.73;
        Carbs[405] = 0.00;
        Protein[405] = 26.34;

        food[406] = "Beef Bottom Sirloin (Tri-Tip Roast, Lean Only, Trimmed To 0 Fat, Choice Grade)";
        calories[406] = 154;
        fat[406] = 7.06;
        Carbs[406] = 0.00;
        Protein[406] = 21.17;

        food[407] = "Beef Bottom Sirloin (Tri-Tip Roast, Lean Only, Trimmed To 0 Fat, Select Grade, Cooked, Roasted)";
        calories[407] =179 ;
        fat[407] = 6.95;
        Carbs[407] = 0.00;
        Protein[407] = 21.17;

        food[408] = "Beef Bottom Sirloin (Tri-Tip Roast, Lean Only, Trimmed To 0 Fat, Select Grade)";
        calories[408] = 129;
        fat[408] = 4.21;
        Carbs[408] = 0.00;
        Protein[408] = 21.34;

        food[409] = "Beef Bottom Sirloin (Tri-Tip Roast, Trimmed To 0 Fat)";
        calories[409] = 164;
        fat[409] = 8.55;
        Carbs[409] = 0.00;
        Protein[409] = 20.64;

        food[410] = "Beef Bottom Sirloin (Tri-Tip Roast, Trimmed To 0 Fat, Choice Grade)";
        calories[410] = 172;
        fat[410] = 9.51;
        Carbs[410] = 0.00;
        Protein[410] = 20.64;

        food[411] = "Beef Bottom Sirloin (Tri-Tip Roast, Trimmed To 0 Fat, Cooked, Roasted)";
        calories[411] = 208;
        fat[411] = 11.07;
        Carbs[411] = 0.00;
        Protein[411] = 26.05;

        food[412] = "Beef Bottom Sirloin (Tri-Tip Roast, Trimmed To 0 Fat, Choice Grade, Cooked, Roasted)";
        calories[412] = 218;
        fat[412] = 12.36;
        Carbs[412] = 0.00;
        Protein[412] = 25.66;

        food[413] = "Beef Bottom Sirloin (Tri-Tip Roast, Trimmed To 0 Fat, Select Grade, Cooked, Roasted)";
        calories[413] = 198;
        fat[413] = 9.78;
        Carbs[413] = 0.00;
        Protein[413] = 26.44;

        food[414] = "Beef Bottom Sirloin (Tri-Tip Roast, Trimmed To 0 Fat, Select Grade)";
        calories[414] = 157;
        fat[414] = 7.68;
        Carbs[414] = 0.00;
        Protein[414] = 20.64;

        food[415] = "Beef Bottom Sirloin Butt (Tri-Tip, Lean Only, Trimmed To 1/4 Fat)";
        calories[415] = 154;
        fat[415] = 7.76;
        Carbs[415] = 0.00;
        Protein[415] = 21.16;

        food[416] = "Beef Brain";
        calories[416] = 143;
        fat[416] = 10.30;
        Carbs[416] = 1.05;
        Protein[416] = 10.86;

        food[417] = "Beef Brain (Cooked, Pan-Fried)";
        calories[417] = 196;
        fat[417] = 15.83;
        Carbs[417] = 0.00;
        Protein[417] = 12.57;

        food[418] = "Beef Brain (Cooked, Simmered)";
        calories[418] = 151;
        fat[418] = 10.53;
        Carbs[418] = 1.48;
        Protein[418] = 11.67;


        food[419] = "Beef Breakfast Strips (Cured)";
        calories[419] = 4.06;
        fat[419] = 38.80;
        Carbs[419] = 0.70;
        Protein[419] = 12.50;

        food[420] = "Beef Bottom Sirloin Butt (Tri-Tip Roast, Lean Only, Trimmed To 0 Fat, Cooked, Roasted)";
        calories[420] = 182;
        fat[420] = 8.34;
        Carbs[420] = 0.00;
        Protein[420] = 26.75;

        food[421] = "Beef Bottom Sirloin Butt (Tri-Tip Steak, Lean Only, Trimmed To 0 Fat, Cooked, Broiled)";
        calories[421] = 250;
        fat[421] = 13.16;
        Carbs[421] = 0.00;
        Protein[421] = 30.68;

        food[422] = "Beef Bottom Sirloin Butt (Tri-Tip Steak, Trimmed To 0 Fat, Cooked, Roasted)";
        calories[422] = 265;
        fat[422] = 15.18;
        Carbs[422] = 0.00;
        Protein[422] = 29.97;

        food[423] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 0 Fat, Choice Grade, Cooked, Braised)";
        calories[423] = 212;
        fat[423] = 8.07;
        Carbs[423] = 0.00;
        Protein[423] = 32.62;

        food[424] = "Beef Breakfast Strips (Cured, Cooked)";
        calories[424] = 449;
        fat[424] = 34.40;
        Carbs[424] = 1.40;
        Protein[424] = 31.30;

        food[425] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 0 Fat, Cooked, Braised)";
        calories[425] = 205;
        fat[425] = 6.99;
        Carbs[425] = 0.00;
        Protein[425] = 33.62;

        food[426] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[426] = 198;
        fat[426] = 5.92;
        Carbs[426] = 0.00;
        Protein[426] = 33.90;

        food[427] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 1/8 Fat, Choice Grade)";
        calories[427] = 129;
        fat[427] = 4.06;
        Carbs[427] = 0.00;
        Protein[427] = 21.69;

        food[428] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 1/8 Fat, Choice Grade, Cooked, Braised)";
        calories[428] = 203;
        fat[428] = 6.79;
        Carbs[428] = 0.00;
        Protein[428] = 33.13;

        food[429] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[429] = 196;
        fat[429] = 6.00;
        Carbs[429] = 0.00;
        Protein[429] = 33.15;

        food[430] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 1/8 Fat, Select Grade)";
        calories[430] = 124;
        fat[430] = 3.61;
        Carbs[430] = 0.00;
        Protein[430] = 21.45;

        food[431] = "Beef Brisket (Flat Half, Trimmed To 0 Fat, Choice Grade, Cooked, Braised)";
        calories[431] = 221;
        fat[431] = 9.24;
        Carbs[431] = 0.00;
        Protein[431] = 32.21;

        food[432] = "Beef Brisket (Flat Half, Trimmed To 0 Fat, Cooked, Braised)";
        calories[432] = 213;
        fat[432] = 8.01;
        Carbs[432] = 0.00;
        Protein[432] = 32.90;

        food[433] = "Beef Brisket (Flat Half, Lean Only, Trimmed To Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[433] = 127;
        fat[433] = 3.84;
        Carbs[433] = 0.00;
        Protein[433] = 21.57;

        food[434] = "Beef Brisket (Flat Half, Lean Only, Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[434] = 189;
        fat[434] = 5.21;
        Carbs[434] = 0.00;
        Protein[434] = 33.18;

        food[435] = "Beef Brisket (Flat Half, Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[435] = 280;
        fat[435] = 17.37;
        Carbs[435] = 0.00;
        Protein[435] = 28.97;

        food[436] = "Beef Brisket (Point Half, Lean Only, Trimmed To 0 Fat, Cooked, Braised)";
        calories[436] = 244;
        fat[436] = 13.80;
        Carbs[436] = 0.00;
        Protein[436] = 28.05;

        food[437] = "Beef Brisket (Flat Half, Trimmed To Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[437] = 277;
        fat[437] = 22.18;
        Carbs[437] = 0.00;
        Protein[437] = 17.94;

        food[438] = "Beef Brisket (Flat Half, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[438] = 289;
        fat[438] = 18.42;
        Carbs[438] = 0.00;
        Protein[438] = 28.82;

        food[439] = "Beef Brisket (Flat Half, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[439] = 205;
        fat[439] = 6.77;
        Carbs[439] = 0.00;
        Protein[439] = 33.59;

        food[440] = "Beef Brisket (Flat Half, Trimmed To 1/8 Fat, Choice Grade)";
        calories[440] = 277;
        fat[440] = 22.15;
        Carbs[440] = 0.12;
        Protein[440] = 18.12;

        food[441] = "Beef Brisket (Flat Half, Trimmed To 1/8 Fat, Select Grade)";
        calories[441] = 276;
        fat[441] = 22.21;
        Carbs[441] = 0.00;
        Protein[441] = 17.77;

        food[442] = "Beef Brisket (Flat Half, Trimmed To 1/8 Fat, Choice Grade, Cooked, Braised)";
        calories[442] = 298;
        fat[442] = 19.47;
        Carbs[442] = 0.00;
        Protein[442] = 28.66;

        food[443] = "Beef Brisket (Point Half, Trimmed To 1/4 Fat)";
        calories[443] = 331;
        fat[443] = 29.09;
        Carbs[443] = 0.00;
        Protein[443] = 16.12;


        food[444] = "Beef Brisket (Point Half, Trimmed To 1/4 Fat, Cooked, Braised)";
        calories[444] = 404;
        fat[444] = 34.27;
        Carbs[444] = 0.00;
        Protein[444] = 22.13;

        food[445] = "Beef Brisket (Point Half, Lean Only, Trimmed To 1/4 Fat)";
        calories[445] = 162;
        fat[445] = 8.50;
        Carbs[445] = 0.00;
        Protein[445] = 20.01;

        food[446] = "Beef Brisket (Point Half, Lean Only, Trimmed To 1/4 Fat, Cooked, Braised)";
        calories[446] = 261;
        fat[446] = 15.70;
        Carbs[446] = 0.00;
        Protein[446] = 18.05;

        food[447] = "Beef Brisket (Point Half, Trimmed To 0 Fat, Cooked, Braised)";
        calories[447] = 358;
        fat[447] = 28.50;
        Carbs[447] = 0.00;
        Protein[447] = 23.53;

        food[448] = "Beef Brisket (Point Half, Trimmed To 1/8 Fat)";
        calories[448] = 265;
        fat[448] = 20.98;
        Carbs[448] = 0.00;
        Protein[448] = 17.65;

        food[449] = "Beef Brisket (Point Half, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[449] = 349;
        fat[449] = 27.17;
        Carbs[449] = 0.00;
        Protein[449] = 24.40;

        food[450] = "Beef Brisket (Whole, Lean Only)";
        calories[450] = 155;
        fat[450] = 7.37;
        Carbs[450] = 0.00;
        Protein[450] = 20.72;

        food[451] = "Beef Brisket (Whole, Trimmed To 1/4 Fat)";
        calories[451] = 312;
        fat[451] = 26.54;
        Carbs[451] = 0.00;
        Protein[451] = 16.94;

        food[452] = "Beef Brisket (Whole, Lean Only, Trimmed To 1/4 Fat, Cooked, Braised)";
        calories[452] = 242;
        fat[452] = 12.76;
        Carbs[452] = 0.00;
        Protein[452] = 29.75;

        food[453] = "Beef Brisket (Whole, Trimmed To 0 Fat, Cooked, Braised)";
        calories[453] = 291;
        fat[453] = 19.52;
        Carbs[453] = 0.00;
        Protein[453] = 26.79;


        food[454] = "Beef Brisket (Whole, Lean Only, Trimmed To 0 Fat, Cooked, Braised)";
        calories[454] = 218;
        fat[454] = 10.08;
        Carbs[454] = 0.00;
        Protein[454] = 29.75;

        food[455] = "Beef Broth Or Bouillon (Canned)";
        calories[455] = 7;
        fat[455] = 0.22;
        Carbs[455] = 0.04;
        Protein[455] = 1.14;

        food[456] = "Beef Broth Bouillon And Consomme (Canned, Condensed)";
        calories[456] = 24;
        fat[456] = 0.00;
        Carbs[456] = 1.44;
        Protein[456] = 4.37;

        food[457] = "Beef Broth Or Bouillon Soup (Dry Powder)";
        calories[457] = 238;
        fat[457] = 8.89;
        Carbs[457] = 23.65;
        Protein[457] = 15.97;

        food[458] = "Beef Broth Or Bouillon Soup (Made With Powder)";
        calories[458] = 8;
        fat[458] = 0.29;
        Carbs[458] = 0.77;
        Protein[458] = 0.52;

        food[459] = "Beef Brisket (Whole, Trimmed To 1/4 Fat, Cooked, Braised)";
        calories[459] = 385;
        fat[459] = 31.56;
        Carbs[459] = 0.00;
        Protein[459] = 23.50;

        food[460] = "Beef Brisket (Whole, Trimmed To 1/8 Fat)";
        calories[460] = 251;
        fat[460] = 19.06;
        Carbs[460] = 0.00;
        Protein[460] = 18.42;

        food[461] = "Beef Brisket (Whole, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[461] = 331;
        fat[461] = 24.50;
        Carbs[461] = 0.00;
        Protein[461] = 25.85;

        food[462] = "Beef Broth And Tomato Juice (Canned)";
        calories[462] = 37;
        fat[462] = 0.10;
        Carbs[462] = 8.50;
        Protein[462] = 0.60;

        food[463] = "Beef Broth Soup (Condensed, Canned)";
        calories[463] = 18;
        fat[463] = 1.00;
        Carbs[463] = 0.00;
        Protein[463] = 2.60;


        food[464] = "Beef Carcass (Choice Grade)";
        calories[464] = 291;
        fat[464] = 24.05;
        Carbs[464] = 0.00;
        Protein[464] = 17.32;

        food[465] = "Beef Carcass (Select Grade)";
        calories[465] = 287;
        fat[465] = 22.55;
        Carbs[465] = 0.00;
        Protein[465] = 17.48;

        food[466] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 0 Fat, Cooked, Braised)";
        calories[466] = 212;
        fat[466] = 7.67;
        Carbs[466] = 0.00;
        Protein[466] = 33.36;

        food[467] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[467] = 195;
        fat[467] = 5.80;
        Carbs[467] = 0.00;
        Protein[467] = 33.37;

        food[468] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/2 Fat, Prime Grade)";
        calories[468] = 154;
        fat[468] = 6.96;
        Carbs[468] = 0.00;
        Protein[468] = 21.26;

        food[469] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/2 Fat, Prime Grade, Cooked, Braised)";
        calories[469] = 261;
        fat[469] = 13.36;
        Carbs[469] = 0.00;
        Protein[469] = 33.02;

        food[469] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/8 Fat)";
        calories[469] = 132;
        fat[469] = 4.19;
        Carbs[469] = 0.00;
        Protein[469] = 22.11;

        food[470] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[470] = 214;
        fat[470] = 7.36;
        Carbs[470] = 0.00;
        Protein[470] = 34.66;

        food[471] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/8 Fat, Choice Grade)";
        calories[471] =139 ;
        fat[471] = 5.05;
        Carbs[471] = 0.00;
        Protein[471] = 21.96;

        food[272] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/8 Fat, Choice Grade, Cooked, Braised)";
        calories[272] = 224;
        fat[272] = 8.37;
        Carbs[272] = 0.00;
        Protein[272] = 34.72;

        food[473] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/8 Fat, Select Grade)";
        calories[473] = 125;
        fat[473] = 3.32;
        Carbs[473] = 0.00;
        Protein[473] = 22.26;

        food[474] = "Beef Chuck (Arm Pot Roast, Trimmed To 0 Fat, Cooked, Braised)";
        calories[474] = 297;
        fat[474] = 19.17;
        Carbs[474] = 0.00;
        Protein[474] = 28.94;

        food[475] = "Beef Chuck (Arm Pot Roast, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[475] = 283;
        fat[475] = 17.56;
        Carbs[475] = 0.00;
        Protein[475] = 29.23;

        food[476] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/2 Fat, Prime Grade)";
        calories[476] = 294;
        fat[476] = 24.16;
        Carbs[476] = 0.00;
        Protein[476] = 17.75;

        food[477] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/2 Fat, Prime Grade, Cooked, Braised)";
        calories[477] = 391;
        fat[477] = 30.96;
        Carbs[477] = 0.00;
        Protein[477] = 26.11;

        food[478] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat)";
        calories[478] = 244;
        fat[478] = 17.98;
        Carbs[478] = 0.00;
        Protein[478] = 19.23;

        food[479] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat, Choice Grade)";
        calories[479] = 249;
        fat[479] = 18.57;
        Carbs[479] = 0.00;
        Protein[479] = 19.14;

        food[480] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat, Choice Grade, Cooked, Braised)";
        calories[480] = 309;
        fat[480] = 19.93;
        Carbs[48] = 0.00;
        Protein[480] = 30.20;

        food[481] = "Beef Chuck (Arm Pot Roast, Lean Only, Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[481] = 205;
        fat[481] = 6.35;
        Carbs[481] = 0.00;
        Protein[481] = 34.60;

        food[482] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 0 Fat, Cooked, Braised)";
        calories[482] = 253;
        fat[482] = 13.30;
        Carbs[482] = 0.00;
        Protein[482] = 31.06;

        food[483] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 0 Fat, Choice Grade, Cooked, Braised)";
        calories[483] = 265;
        fat[483] = 14.70;
        Carbs[483] = 0.00;
        Protein[483] = 31.06;

        food[484] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[484] = 238;
        fat[484] = 11.70;
        Carbs[484] = 0.00;
        Protein[484] = 31.06;

        food[485] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat, Cooked, Braised)";
        calories[485] = 302;
        fat[485] = 19.22;
        Carbs[485] = 0.00;
        Protein[485] = 30.12;

        food[486] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat, Select Grade)";
        calories[486] = 239;
        fat[486] = 17.39;
        Carbs[486] = 0.00;
        Protein[486] = 19.33;

        food[487] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[487] = 295;
        fat[487] = 18.50;
        Carbs[487] = 0.00;
        Protein[487] = 30.05;

        food[488] = "Beef Chuck (Arm Pot Roast, Trimmed To 1/8 Fat, Select Grade, Cooked, Braised)";
        calories[488] = 295;
        fat[488] = 18.50;
        Carbs[488] = 0.00;
        Protein[488] = 30.05;

        food[489] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/2 Fat, Prime Grade)";
        calories[489] = 203;
        fat[489] = 13.41;
        Carbs[489] = 0.00;
        Protein[489] = 19.25;

        food[490] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/2 Fat, Prime Grade, Cooked, Braised)";
        calories[490] = 318;
        fat[490] = 20.53;
        Carbs[490] = 0.00;
        Protein[490] = 31.06;

        food[491] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/4 Fat)";
        calories[491] = 149;
        fat[491] = 7.40;
        Carbs[491] = 0.00;
        Protein[491] = 19.25;

        food[492] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/4 Fat, Cooked, Braised)";
        calories[492] = 251;
        fat[492] =13.10;
        Carbs[492] = 0.00;
        Protein[492] = 31.06;

        food[493] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/4 Fat, Choice Grade)";
        calories[493] = 159;
        fat[493] = 8.50;
        Carbs[493] = 0.00;
        Protein[493] = 19.25;

        food[494] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/4 Fat, Choice Grade, Cooked, Braised)";
        calories[494] = 263;
        fat[494] = 14.40;
        Carbs[494] = 0.00;
        Protein[494] = 31.06;

        food[495] = "Beef Chuck (Blade Roast, Trimmed To 1/4 Fat)";
        calories[495] = 254;
        fat[495] = 20.10;
        Carbs[495] = 0.00;
        Protein[495] = 17.04;

        food[496] = "Beef Chuck (Blade Roast, Trimmed To 1/4 Fat, Choice Grade)";
        calories[496] = 272;
        fat[496] = 22.23;
        Carbs[496] = 0.00;
        Protein[496] = 16.82;

        food[497] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/4 Fat, Select Grade)";
        calories[497] = 139;
        fat[497] = 6.30;
        Carbs[497] = 0.00;
        Protein[497] = 19.25;

        food[497] = "Beef Chuck (Blade Roast, Lean Only, Trimmed To 1/4 Fat, Select Grade, Cooked, Braised)";
        calories[497] = 237;
        fat[497] = 11.60;
        Carbs[497] = 0.00;
        Protein[497] = 31.06;

        food[498] = "Beef Chuck (Blade Roast, Trimmed To 0 Fat, Choice Grade, Cooked, Braised)";
        calories[498] = 348;
        fat[498] = 25.83;
        Carbs[498] = 0.00;
        Protein[498] = 26.98;

        food[499] = "Beef Chuck (Blade Roast, Trimmed To 0 Fat, Select Grade, Cooked, Braised)";
        calories[499] = 313;
        fat[499] = 21.67;
        Carbs[499] = 0.00;
        Protein[499] = 27.59;

        food[500] = "Beef Chuck (Blade Roast, Trimmed To 1/2 Fat, Prime Grade)";
        calories[500] = 328;
        fat[500] = 28.58;
        Carbs[500] = 0.00;
        Protein[500] = 16.33;


    }

    public void llenadoA (){

        List<FoodsModel> foodsModelList = new ArrayList<>();


        for (int i = 0; i < 501; i++){
            Alimentos[i] = food[i];

            FoodsModel foodsModel = new FoodsModel();

            foodsModel.setId(i);
            foodsModel.setName(food[i]);
            foodsModel.setCalories(calories[i]);
            foodsModel.setFat(fat[i]);
            foodsModel.setCarbs(Carbs[i]);
            foodsModel.setProtein(Protein[i]);
            foodsModelList.add(i,foodsModel);
        }

        String json = new Gson().toJson(foodsModelList);

        Log.e("COMIDA", json);

    }


}
