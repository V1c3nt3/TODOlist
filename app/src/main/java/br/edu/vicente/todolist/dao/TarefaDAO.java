package br.edu.vicente.todolist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.vicente.todolist.model.Tarefa;

public class TarefaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public TarefaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserirTarefa(Tarefa tarefa){
        //objeto contendo os valores para salvar no bd
        ContentValues values = new ContentValues();
        //colocar os valores
        values.put("tarefa", tarefa.getTarefa());
        values.put("descricao", tarefa.getDescricao());
        values.put("data", tarefa.getData());
        values.put("hora", tarefa.getHora());

        return banco.insert("tarefa", null,values);
    }
    public List<Tarefa> obterTarefas(){
        List<Tarefa> tarefas = new ArrayList<>();

        //obter os dados do BD
        Cursor cursor = banco.query(
                "tarefa",
                new String[]{"id","tarefa","descricao","data","hora"},
                null,
                null,
                null,
                null,
                null
        );

        //loop para percorrer os registros
        while (cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            //transformando o registro num obj
            tarefa.setId(cursor.getInt(0));
            tarefa.setTarefa(cursor.getString(1));
            tarefa.setDescricao(cursor.getString(2));
            tarefa.setData(cursor.getString(3));
            tarefa.setHora(cursor.getString(4));

            //adicionar a tarefa a lista de tarefas
            tarefas.add(tarefa);
        }

        return tarefas;
    }
    public void excluirTarefas(){

    }
    public void atualizarTarefas(Tarefa tarefa){
        //obj contendo os valores para salvar no BD
        ContentValues values = new ContentValues();

        //envia os dados após a edição do registro
        banco.update(
                "tarefa",
                values,
                "id = ?",
                new String[]{String.valueOf(tarefa.getId())});
    }
}
