package com.srmhitter9062.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
//    @NotBlank
    private String title;
   // @NotNull
    private boolean is_completed;
   // @NotBlank
    private String status;
    //@NotNull
    private Timestamp updated_at;
//    @NotNull
    private Timestamp created_at;
    public Todo() {}
    public Todo(int id) {
        this.id = id;
    }

    public Todo(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public Todo(String title, boolean isCompleted,String status,Timestamp createdAt,Timestamp updatedAt) {
        this.title = title;
        this.is_completed = isCompleted;
        this.status = status;
        this.updated_at =  updatedAt;
        this.created_at =  createdAt;
    }


    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String  title){
        this.title = title;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public boolean getIsCompleted(){
        return this.is_completed;
    }
    public void setIsCompleted(boolean is_completed){
        this.is_completed = is_completed;
    }
    public Timestamp getUpdateAt(){
        return this.updated_at;
    }
    public void setUpdatedAt(Timestamp updated_at){
        this.updated_at = updated_at;
    }

    public Timestamp getCreatedAt(){
        return this.created_at;
    }
    public void setCreatedAt(Timestamp created_at){
        this.created_at = created_at;
    }


}
