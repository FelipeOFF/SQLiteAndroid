package icommerce.com.br.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import icommerce.com.br.database.R;
import icommerce.com.br.database.data.Produto;

/**
 * Criado por Felipe de Oliveira em 11/04/16.
 * <p>
 * <p>
 * <p>
 * <p>
 * Qualquer duvida contate.
 * Tel: (011)95245-7295
 * Email: felipe-not@hotmail.com
 */
public class ProdutosAdapter extends BaseAdapter {

    public static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private Context context;
    private List<Produto> produtos = new ArrayList<>();

    public ProdutosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Produto getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_list_produtos, parent, false);
            holder = new ViewHolder();
            holder.txtNome = (TextView) view.findViewById(R.id.txt_nome);
            holder.txtValor = (TextView) view.findViewById(R.id.txt_valor);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }

        Produto produto = produtos.get(position);
        holder.txtNome.setText(produto.getNome());
        holder.txtValor.setText(nf.format(produto.getValor()));

        return view;
    }

    public void setList(List<Produto> produtos){
        this.produtos = produtos;
        notifyDataSetChanged();
    }


    private class ViewHolder{
        private TextView txtNome;
        private TextView txtValor;
    }
}
