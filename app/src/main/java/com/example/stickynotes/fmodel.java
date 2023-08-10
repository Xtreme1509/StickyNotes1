package com.example.stickynotes;
public class fmodel {

    private String Title;
    private String Content;

    public fmodel() {
    }

    public fmodel(String title, String content) {
        this.Title = title;
        this.Content = content;
    }

    public  String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}