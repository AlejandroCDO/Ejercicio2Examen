package rubengarcia.ejercicio2examen;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;


import rubengarcia.ejercicio2examen.adapters.DesconocidoAdapter;
import rubengarcia.ejercicio2examen.databinding.ActivityMainBinding;
import rubengarcia.ejercicio2examen.models.DesconocidoModel;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private ArrayList<DesconocidoModel> desconocidoList;

    private DesconocidoAdapter adapter;

    private RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        desconocidoList = new ArrayList<>();

        adapter = new DesconocidoAdapter(
                desconocidoList, R.layout.desconocido_view_model, this
        );

        lm = new LinearLayoutManager(this);

        binding.contentMain.contenedorMain.setAdapter(adapter);
        binding.contentMain.contenedorMain.setLayoutManager(lm);
        


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crear().show();

            }
        });
    }

    //CREAR ALGO CON EL MODELO DE DATOS EN EN ALERT VIEW CAMBIAR DATOS CREAS OBJETO
    private AlertDialog crear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Titulo");
        builder.setCancelable(false);

        View desconocidoViewAlert = LayoutInflater.from(this).inflate(R.layout.desconocido_view_alert, null);
        EditText txtdesconocido = desconocidoViewAlert.findViewById(R.id.editTextText);
        EditText txtdesconocido2 = desconocidoViewAlert.findViewById(R.id.editTextNumber);
        EditText txtdesconocido3 = desconocidoViewAlert.findViewById(R.id.editTextNumberDecimal);
        TextView lbldesconocido4 = desconocidoViewAlert.findViewById(R.id.textView);
        builder.setView(desconocidoViewAlert);

        //ACTUALIZAR EL LABEL EN TIEMPO REAL
        TextWatcher tw = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                try {

                    int desconocido2 = Integer.parseInt(txtdesconocido2.getText().toString());
                    float desconocido3 = Float.parseFloat(txtdesconocido3.getText().toString());
                    float total = desconocido2 * desconocido3;


                    //CON SIMBOLO MONEDA
                    //NumberFormat nf = NumberFormat.getCurrencyInstance();
                    //lbldesconocido4.setText(nf.format(total));

                    //SIN SIMBOLO MONEDA
                    NumberFormat nf = NumberFormat.getNumberInstance();
                    lbldesconocido4.setText(nf.format(total));


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        };
        //PARA QUE LO ACTUALIZAR ESTE PENDIENTE A LO QUE NECISTA ACTUALIZAR
        txtdesconocido2.addTextChangedListener(tw);
        txtdesconocido3.addTextChangedListener(tw);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //String
                String desconocido1 = txtdesconocido.getText().toString();
                //INT
                String desconocido2S = txtdesconocido2.getText().toString();
                //Decimal
                String desconocido3S = txtdesconocido3.getText().toString();

                if (!desconocido1.isEmpty() && !desconocido2S.isEmpty() && !desconocido3S.isEmpty()){

                    int desconocido2 = Integer.parseInt(desconocido2S);
                    float desconocido3 = Float.parseFloat(desconocido3S);

                    DesconocidoModel d = new DesconocidoModel(desconocido1,desconocido2,desconocido3);
                    desconocidoList.add(0,d);
                    adapter.notifyItemInserted(0);

                }
            }
        });

        return builder.create();
    }

    //MOVIL HORIZONTAL


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("LIST", desconocidoList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<DesconocidoModel> listaRecuperada = (ArrayList<DesconocidoModel>) savedInstanceState.getSerializable("LIST");


        desconocidoList.addAll(listaRecuperada);
        adapter.notifyItemRangeInserted(0,desconocidoList.size());
    }
}