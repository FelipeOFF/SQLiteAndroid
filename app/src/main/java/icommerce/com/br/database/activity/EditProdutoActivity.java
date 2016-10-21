package icommerce.com.br.database.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import icommerce.com.br.database.R;
import icommerce.com.br.database.data.Produto;
import icommerce.com.br.database.data.ProdutoDAO;

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
public class EditProdutoActivity extends Activity {

    private ProdutoDAO produtoDAO;
    private EditText edtNome;
    private EditText edtValor;
    private Produto produto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produto);

        edtNome = (EditText) findViewById(R.id.edt_nome);
        edtValor = (EditText) findViewById(R.id.edt_valor);

        produtoDAO = ProdutoDAO.getInstance(this);

        produto = (Produto) getIntent().getSerializableExtra(Produto.PRODUTO);

        if(produto != null){
            edtNome.setText(produto.getNome());
            edtValor.setText(String.valueOf(produto.getValor()));
        }
    }

    public void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    public void process(View view){
        String nome = edtNome.getText().toString();
        double valor = Double.parseDouble(edtValor.getText().toString());

        String msg;

        if(produto == null){
            Produto produto = new Produto(nome, valor);
            produtoDAO.save(produto);
            msg = "Produto gravado com ID " + produto.getId();
        }else{
            produto.setNome(nome);
            produto.setValor(valor);
            produtoDAO.update(produto);
            msg = "Produto atualizado com o id " + produto.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();

    }

}
