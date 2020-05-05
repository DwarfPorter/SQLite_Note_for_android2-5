package ru.geekbrains.notes;

import ru.geekbrains.notes.data.Note;
import ru.geekbrains.notes.ui.AdapterChangeable;

public class NoteLogic {
    private IRepository repository;
    private AdapterChangeable adapter;           // Отправим сигнал изменения данных

    public NoteLogic(IRepository repository){
        this.repository = repository;
    }

    public void setAdapter(AdapterChangeable adapter){
        this.adapter = adapter;
    }

    public void addNote(Note note){
        getRepository().add(note);
        updateNote();
    }

    public void editNote(Note note) {
        getRepository().update(note);
        updateNote();
    }

    public void deleteNote(Note note) {
        getRepository().delete(note);
        updateNote();
    }

    public void clearList() {
        getRepository().deleteAll();
        updateNote();
    }

    public IRepository getRepository() {
        return repository;
    }

    private void updateNote(){
        adapter.notifyDataChange();
    }
}
