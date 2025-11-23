package alejandrocompany.ejercicio2examen.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alejandrocompany.ejercicio2examen.R;
import alejandrocompany.ejercicio2examen.models.DesconocidoModel;

//CAMBIAR NOMBRES
public class DesconocidoAdapter extends RecyclerView.Adapter<DesconocidoAdapter.DesconocidoVH> {
    private List<DesconocidoModel> objects;
    private int resource;
    private Context context;

    public DesconocidoAdapter(List<DesconocidoModel> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public DesconocidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View desconocidoView = LayoutInflater.from(context).inflate(resource,null);
        desconocidoView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new DesconocidoVH(desconocidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull DesconocidoVH holder, int position) {

        //PONEMOS LOS DATOS QUE QUEREMOS PASAR AL DSESCONOCIDO VIEW MODEL VIEW
        DesconocidoModel d = objects.get(position);
        holder.lbl1.setText(d.getNombre());
        holder.lbl2.setText(String.valueOf(d.getCantidad()));
        holder.lbl3.setText(String.valueOf(d.getPrecio()));
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarEliminar(holder.getBindingAdapterPosition()).show();
            }
        });


    }

    private AlertDialog confirmarEliminar(int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("¿Seguro?");
        builder.setCancelable(true);


        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                objects.remove(posicion);
                notifyItemRemoved(posicion);
            }
        });

        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class DesconocidoVH extends RecyclerView.ViewHolder{

        //DAMOS NOMBRE A LOS LABELS Y LAS COSAS DEL RECYLCER VIEW
        private TextView lbl1;
        private TextView lbl2;
        private TextView lbl3;
        private ImageButton btnEliminar;
        private ImageButton btnEditar;


        public DesconocidoVH(@NonNull View itemView) {
            super(itemView);

            lbl1 = itemView.findViewById(R.id.textView5);
            lbl2 = itemView.findViewById(R.id.textView6);
            lbl3 = itemView.findViewById(R.id.textView7);
            btnEditar = itemView.findViewById(R.id.imageButton9);
            btnEliminar = itemView.findViewById(R.id.btnEliminarDesconocidoViewModel);


        }
    }
}
