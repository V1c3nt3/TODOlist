package br.edu.vicente.todolist.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private static final String name= "tarefa.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tarefa (" +
                "id integer primary key autoincrement," +
                "tarefa varchar(50)," +
                "descricao varchar(255)," +
                "data varchar(10)," +
                "hora varchar(5))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

