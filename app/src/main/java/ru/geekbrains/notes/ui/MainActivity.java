package ru.geekbrains.notes.ui;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import ru.geekbrains.notes.IRepository;
import ru.geekbrains.notes.NoteLogic;
import ru.geekbrains.notes.R;
import ru.geekbrains.notes.data.Note;
import ru.geekbrains.notes.data.SQLiteRepository;

public class MainActivity extends AppCompatActivity {

    private NoteLogic noteLogic;
    IRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repository = new SQLiteRepository(getApplicationContext());
        initRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement();
            }
        });
    }

    private void initRecyclerView() {
        noteLogic = new NoteLogic(repository);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        // Адаптер для RecyclerView
        NoteAdapter adapter = new NoteAdapter(noteLogic.getRepository());
        noteLogic.setAdapter(adapter);

        adapter.setOnMenuItemClickListener(new NoteAdapter.OnMenuItemClickListener() {
            @Override
            public void onItemEditClick(Note note) {
                editElement(note);
            }

            @Override
            public void onItemDeleteClick(Note note) {
                deleteElement(note);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                addElement();
                return true;
            case R.id.menu_clear:
                noteLogic.clearList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addElement() {
        // Выведем диалоговое окно для ввода новой записи
        LayoutInflater factory = LayoutInflater.from(this);
        // alertView пригодится в дальнейшем для поиска пользовательских элементов
        final View alertView = factory.inflate(R.layout.layout_add_note, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(alertView);
        builder.setTitle(R.string.alert_title_add);
        builder.setNegativeButton(R.string.alert_cancel, null);
        builder.setPositiveButton(R.string.menu_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText editTextNote = alertView.findViewById(R.id.editTextNote);
                EditText editTextNoteTitle = alertView.findViewById(R.id.editTextNoteTitle);
                // если использовать findViewById без alertView то всегда будем получать editText = null
                Note note = new Note();
                note.setTitle(editTextNoteTitle.getText().toString());
                note.setDescription(editTextNote.getText().toString());
                noteLogic.addNote(note);
            }
        });
        builder.show();
    }

    private void editElement(Note note) {
        note.setDescription("Edited");
        note.setTitle("Edited title");
        noteLogic.editNote(note);
    }

    private void deleteElement(Note note) {
        noteLogic.deleteNote(note);
    }
}
