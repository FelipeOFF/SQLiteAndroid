package icommerce.com.br.database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por Felipe de Oliveira em 11/04/16.
 * <p>
 * <p/> Este modo de obter a instancia é chamado de singleton
 * e é usado quando queremos retornar a mesma instancia para cada objeto que o
 * chamar. Muito interesante. Pode ser visto com mais detalhes no site:
 * <p>
 * http://www.devmedia.com.br/trabalhando-com-singleton-java/23632
 * <p>
 * Estude este método de utilização e outros Design Patterns
 * <p>
 * Qualquer duvida contate.
 * Tel: (011)95245-7295
 * Email: felipe-not@hotmail.com
 */
public class ProdutoDAO {

    private static ProdutoDAO instance;

    private SQLiteDatabase db;

    private ProdutoDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static ProdutoDAO getInstance(Context context) {
        if (instance == null) {
            instance = new ProdutoDAO(context.getApplicationContext());
        }
        return instance;
    }

    public List<Produto> list() {

        //Array de colunas

        String[] coluns = {
                ProdutosContract.Coluns._ID,
                ProdutosContract.Coluns.NAME,
                ProdutosContract.Coluns.VALOR
        };
        //Uma lista para ser retornada
        List<Produto> produtos = new ArrayList<>();

        Cursor c = db.query(ProdutosContract.TABLE_NAME, coluns, null, null, null, null, ProdutosContract.Coluns.NAME);
        if (c.moveToFirst()) {
            do {
                Produto p = ProdutoDAO.fromCursor(c);
                produtos.add(p);
            } while (c.moveToNext());
        }
        c.close();
        return produtos;

    }

    public Produto save(Produto produto) {
        ContentValues values = new ContentValues();
        values.put(ProdutosContract.Coluns.NAME, produto.getNome());
        values.put(ProdutosContract.Coluns.VALOR, produto.getValor());
        long id = db.insert(ProdutosContract.TABLE_NAME, null, values);
        produto.setId((int) id);
        return produto;
    }

    public int update(Produto produto) {
        ContentValues values = new ContentValues();
        values.put(ProdutosContract.Coluns.NAME, produto.getNome());
        values.put(ProdutosContract.Coluns.VALOR, produto.getValor());

        return db.update(ProdutosContract.TABLE_NAME, values, ProdutosContract.Coluns._ID + " = ?", new String[]{String.valueOf(produto.getId())});
    }

    public int delete(Produto produto) {
        return db.delete(ProdutosContract.TABLE_NAME, ProdutosContract.Coluns._ID + " = ?", new String[]{String.valueOf(produto.getId())});
        //Tanto o update quanto o delete retorna um valor inteiro para ditar quantos registros foram afetados
    }

    private static Produto fromCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(ProdutosContract.Coluns._ID));
        String nome = c.getString(c.getColumnIndex(ProdutosContract.Coluns.NAME));
        double valor = c.getDouble(c.getColumnIndex(ProdutosContract.Coluns.VALOR));
        return new Produto(id, nome, valor);
    }

}
