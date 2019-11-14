package br.edu.vicente.todolist.view;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.todolist.R;
import br.edu.vicente.todolist.dao.TarefaDAO;
import br.edu.vicente.todolist.model.Tarefa;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class ListarTarefaActivity extends AppCompatActivity {

    //objeto será utillizado para vincular com o componente da activity
    ListView listaTarefas;

    //objeto para acessar o bD
    TarefaDAO tarefaDAO;

    //objeto para listar os registros do BD
    List<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tarefa);
        setTitle("Listar Tarefas");

        //vincular o obj com o componente
        listaTarefas = (ListView) findViewById(R.id.listar_tarefa_ltv_tarefas);

        //instanciar obj que cria conexã com o BD
        tarefaDAO = new TarefaDAO(this);

        //consultar BD
        tarefas = tarefaDAO.obterTarefas();

        //adaptador para atribuir os valores no ListView
        ArrayAdapter adaptador = new ArrayAdapter<Tarefa>(
                this, android.R.layout.simple_list_item_1,
                tarefas
        );

        //atribuir  os valores á listview
        listaTarefas.setAdapter(adaptador);

        //registro pra dizer que o menu deve ser criado
        registerForContextMenu(listaTarefas);
    }
    public void novaTarefa(View view){
        //redireciona para tela que cadastra nova tarefa
        startActivity(new Intent(this, CadastrarTarefaActivity.class));
    }

    @Override
    protected void onResume(){
        super.onResume();
        //consultar BD
        tarefas = tarefaDAO.obterTarefas();

        //adaptador para atribuir os valores no listview
        ArrayAdapter adaptador = new ArrayAdapter<Tarefa>(
                this, android.R.layout.simple_list_item_1,
                 tarefas
        );

        //atribuir os valores a listview
        listaTarefas.setAdapter(adaptador);
    }


    //metodo usado para criar o menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contexto, menu);
    }

    public void editarTarefa(MenuItem item){
        //obtem o item da lista (ListView) que foi clicado
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //transformar num obj do tipo Tarefa
        Tarefa tarefaEditar = tarefas.get(menuInfo.position);

        //preparar os dados que serão transmitidos para Activity de Cadastro
        Intent intent = new Intent(this, CadastrarTarefaActivity.class);
        intent.putExtra("tarefa", tarefaEditar);

        //abre a Activity de Cadastro
        startActivity(intent);

    }

    //excluir uma tarefa
    public void excluirTarefa(MenuItem item){

    }
}
