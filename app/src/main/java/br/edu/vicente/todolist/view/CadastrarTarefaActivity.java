package br.edu.vicente.todolist.view;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.todolist.R;
import br.edu.vicente.todolist.dao.TarefaDAO;
import br.edu.vicente.todolist.model.Tarefa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarTarefaActivity extends AppCompatActivity {

    private EditText campoTarefa;
    private EditText campoDescricao;
    private EditText campoData;
    private EditText campoHora;

    //criar objeto para acesso ao bd do tipo DAO
    private TarefaDAO tarefaDAO;

    //objeto referente a tarefa
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);
        setTitle("Cadastrar Tarefas");

        campoTarefa = findViewById(R.id.cadastrar_tarefa_edt_tarefa);
        campoDescricao = findViewById(R.id.cadastrar_tarefa_edt_descricao);
        campoData = findViewById(R.id.cadastrar_tarefa_edt_data);
        campoHora = findViewById(R.id.cadastrar_tarefa_edt_hora);

        //instanciar o objeto que estabelece a com=nexão com o bd
        tarefaDAO = new TarefaDAO(this);

        //define um intent para possivelmente resgatar algo
        Intent intent = getIntent();

        //verificar se veio alguma intent de outra Activity
        if (intent.hasExtra("tarefa")) {
            tarefa = (Tarefa) intent.getSerializableExtra("tarefa");

            //preencher os campos do formulário para edição
            campoTarefa.setText(tarefa.getTarefa());
            campoDescricao.setText(tarefa.getDescricao());
            campoData.setText(tarefa.getData());
            campoHora.setText(tarefa.getHora());
        }
    }

    public void salvarTarefa(View view) {

        //verificar se é edição ou novo registro
        if (tarefa == null) {//novo registro
            tarefa = new Tarefa();

            //obter os dados do formulario e salvar no objeto
            tarefa.setTarefa(campoTarefa.getText().toString().trim());
            tarefa.setDescricao(campoDescricao.getText().toString().trim());
            tarefa.setData(campoData.getText().toString().trim());
            tarefa.setHora(campoHora.getText().toString().trim());

            //salvar no bd, recebendo o id da tarefa
            long id = tarefaDAO.inserirTarefa(tarefa);

            //informar ao ususario que foi salvo
            Toast.makeText(this, "Tarefa criada com sucesso. ID: " + id,
                    Toast.LENGTH_LONG).show();
        } else {//atualizar registro existente
            //obter os dados do formulário e salvar no objeto
            tarefa.setTarefa(campoTarefa.getText().toString().trim());
            tarefa.setDescricao(campoDescricao.getText().toString().trim());
            tarefa.setData(campoData.getText().toString().trim());
            tarefa.setHora(campoHora.getText().toString().trim());

            // atualizar os dados no BD
            tarefaDAO.atualizarTarefas(tarefa);

            // emitir mensagem de sucesso
            Toast.makeText(this,
                    "Tarefa atualizada com sucesso!",
                    Toast.LENGTH_LONG).show();
        }
    }
}

