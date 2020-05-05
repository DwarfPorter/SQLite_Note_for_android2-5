package ru.geekbrains.notes.data;

public class Note {
    private long id;
    private String description;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return  description;
    }

    public void setDescription(String note) {
        this.description = note;
    }

    public String getTitle() {
        return  title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note Copy(long id){
        Note newNote = new Note();
        newNote.setDescription(this.getDescription());
        newNote.setTitle(this.getTitle());
        newNote.setId(id);
        return newNote;
    }
}
