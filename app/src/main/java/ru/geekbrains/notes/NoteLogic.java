package ru.geekbrains.notes;

import android.content.Context;

import ru.geekbrains.notes.data.Note;
import ru.geekbrains.notes.data.NoteDataReader;
import ru.geekbrains.notes.data.NoteDataSource;
import ru.geekbrains.notes.ui.AdapterChangeable;

public class NoteLogic {
    private NoteDataSource notesDataSource;     // Источник данных
    private NoteDataReader noteDataReader;      // Читатель данных
    private AdapterChangeable adapter;           // Отправим сигнал изменения данных

    public NoteLogic(Context context){
        notesDataSource = new NoteDataSource(context);
        getNotesDataSource().open();
        noteDataReader = getNotesDataSource().getNoteDataReader();
    }

    public void setAdapter(AdapterChangeable adaper){
        this.adapter = adaper;
    }

    public void addNote(Note note){
        getNotesDataSource().addNote(note);
        updateNote();
    }

    public void editNote(Note note) {
        getNotesDataSource().editNote(note);
        updateNote();
    }

    public void deleteNote(Note note) {
        getNotesDataSource().deleteNote(note);
        updateNote();
    }

    public void clearList() {
        getNotesDataSource().deleteAll();
        updateNote();
    }

    private void updateNote(){
        getNoteDataReader().Refresh();
        adapter.notifyDataChange();
    }

    public NoteDataReader getNoteDataReader() {
        return noteDataReader;
    }

    public NoteDataSource getNotesDataSource() {
        return notesDataSource;
    }
}
