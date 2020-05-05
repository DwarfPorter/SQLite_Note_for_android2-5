package ru.geekbrains.notes;

import android.content.Context;

import ru.geekbrains.notes.data.Note;
import ru.geekbrains.notes.data.NoteDataReader;
import ru.geekbrains.notes.data.NoteDataSource;
import ru.geekbrains.notes.ui.AdapterChangeable;

public class NoteLogic {
    private NoteDataSource notesDataSource;     // Источник данных
    private NoteDataReader noteDataReader;      // Читатель данных
    private AdapterChangeable adaper;           // Отправим сигнал изменения данных

    public NoteLogic(Context context){
        notesDataSource = new NoteDataSource(context);
        notesDataSource.open();
        noteDataReader = notesDataSource.getNoteDataReader();
    }

    public void setAdaper(AdapterChangeable adaper){
        this.adaper = adaper;
    }

    public void addNote(Note note){
        notesDataSource.addNote(note);
        updateNote();
    }

    public void editNote(Note note) {
        notesDataSource.editNote(note);
        updateNote();
    }

    public void deleteNote(Note note) {
        notesDataSource.deleteNote(note);
        updateNote();
    }

    public void clearList() {
        notesDataSource.deleteAll();
        updateNote();
    }

    private void updateNote(){
        getNoteDataReader().Refresh();
        adaper.notifyDataChange();
    }

    public NoteDataReader getNoteDataReader() {
        return noteDataReader;
    }
}
