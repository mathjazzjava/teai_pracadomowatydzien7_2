package com.mathjazz.guihttpapijdbc;

public class News {

    private long id;
    private String text;

    public News(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public News() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
