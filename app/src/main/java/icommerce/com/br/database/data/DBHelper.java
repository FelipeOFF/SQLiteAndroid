package icommerce.com.br.database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Criado por Felipe de Oliveira em 14/04/16.
 * <p>
 * <p>
 * <p>
 * <p>
 * Qualquer duvida contate.
 * Tel: (011)95245-7295
 * Email: felipe-not@hotmail.com
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "produtosdb";
    public static final int DB_VERSION = 1;

    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + ProdutosContract.TABLE_NAME;
    private static final String SQL_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT NOT NULL, %s DOUBLE NOT NULL)", ProdutosContract.TABLE_NAME, ProdutosContract.Coluns._ID,
            ProdutosContract.Coluns.NAME, ProdutosContract.Coluns.VALOR);

    private static DBHelper instance;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
