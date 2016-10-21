package icommerce.com.br.database.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

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
public class DeleteDialog extends DialogFragment {

    private OnDeleteListener listener;
    private Produto produto;

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(!(activity instanceof OnDeleteListener)){
            throw new RuntimeException("A activity deve implementar a interface OnDeleteListener");
        }
        this.listener = (OnDeleteListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja excluir o produto " + produto.getNome() + "?");
        builder.setPositiveButton("Sim", (dialog, which) -> {
            if(listener != null){
                listener.onDelete(produto);
            }
        });
        builder.setNegativeButton("Não", (dialog, which) -> {

        });
        return builder.create();
    }

    public interface OnDeleteListener{
        void onDelete(Produto produto);
    }

}
