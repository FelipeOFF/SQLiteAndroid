package icommerce.com.br.database.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import icommerce.com.br.database.R;
import icommerce.com.br.database.adapter.ProdutosAdapter;
import icommerce.com.br.database.data.Produto;
import icommerce.com.br.database.data.ProdutoDAO;
import icommerce.com.br.database.dialog.DeleteDialog;

/**
 * Criado por Felipe de Oliveira em 11/04/16.
 * <p>
 * <p> Parou na aula de banco de dados. No minuto 18:58
 * <p>
 * Qualquer duvida contate.
 * Tel: (011)95245-7295
 * Email: felipe-not@hotmail.com
 */
public class ListProdutosActivity extends ListActivity implements DeleteDialog.OnDeleteListener {

    private static final int REQ_EDIT = 100;

    private ProdutosAdapter adapter;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produtos);

        adapter = new ProdutosAdapter(this);
        setListAdapter(adapter);

        getListView().setOnItemLongClickListener((parent, view, position, id) -> {
            Produto produto = adapter.getItem(position);

            //Poderia ser feito desta forma ↓↓

            /*new AlertDialog.Builder(this)
                    .setMessage("Deseja excluir o produto " + produto.getNome() + "?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        produtoDAO.delete(produto);
                        updateList();
                    })
                    .setNegativeButton("Não", (dialog, which) -> {

                    }).create().show();*/

            //Mais foi feito assim ↓↓↓

            DeleteDialog dialog = new DeleteDialog();
            dialog.setProduto(produto);
            dialog.show(getFragmentManager(), "deleteDialog");
            return true;
            //Eu economizaria uma classe e muito codigo.
        });

        produtoDAO = ProdutoDAO.getInstance(this);

        updateList();
    }

    private void updateList(){
        List<Produto> produtos = produtoDAO.list();
        adapter.setList(produtos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_list_produtos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getApplicationContext(), EditProdutoActivity.class);
                startActivityForResult(intent, REQ_EDIT);
                return true;
            case R.id.result:
                double total = 0;
                List<Produto> produtos = produtoDAO.list();
                if(produtos.size() > 0){
                    for(Produto produto : produtos){
                        total += produto.getValor();
                    }
                }

                new AlertDialog.Builder(this)
                        .setMessage("O valor total dos produtos são: " + ProdutosAdapter.nf.format(total))
                        .setTitle("Total")
                        .setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_EDIT && resultCode == RESULT_OK){
            updateList();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), EditProdutoActivity.class);
        intent.putExtra(Produto.PRODUTO, adapter.getItem(position));
        startActivityForResult(intent, REQ_EDIT);
    }

    public void onDelete(Produto produto){
        produtoDAO.delete(produto);
        updateList();
    }

}
