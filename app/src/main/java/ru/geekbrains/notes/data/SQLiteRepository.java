package ru.geekbrains.notes.data;

import android.content.Context;

import ru.geekbrains.notes.IRepository;

public class SQLiteRepository implements IRepository {

    private NoteDataSource notesDataSource;     // Источник данных
    private NoteDataReader noteDataReader;      // Читатель данных

    public SQLiteRepository(Context context){
        notesDataSource = new NoteDataSource(context);
        notesDataSource.open();
        noteDataReader = notesDataSource.getNoteDataReader();
    }

    @Override
    public Note add(Note note) {
        Note newNote = notesDataSource.addNote(note);
        updateNote();
        return newNote;
    }

    @Override
    public void update(Note note) {
        notesDataSource.editNote(note);
        updateNote();
    }

    @Override
    public Note get(int position) {
        return noteDataReader.getPosition(position);
    }

    @Override
    public void delete(Note note) {
        notesDataSource.deleteNote(note);
        updateNote();
    }

    @Override
    public void deleteAll() {
        notesDataSource.deleteAll();
        updateNote();
    }

    @Override
    public int getCount() {
        return noteDataReader.getCount();
    }

    private void updateNote() {
        noteDataReader.Refresh();
    }
}
