package com.apiscrum.APIScrum.Entity;

import com.apiscrum.APIScrum.enums.TaskProgress;
import com.apiscrum.APIScrum.enums.TaskTags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="task_title",nullable=false)
    private String task_title;
    @Column(name="description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name="progress")
    private TaskProgress progress ;
    @ManyToOne
    @JoinColumn(name="taskOwner_id",nullable = false)
    private User taskOwner;
    @Enumerated(EnumType.STRING)
    @Column(name="tag")
    private TaskTags tag;
    @ManyToOne
    @JoinColumn(name="user_story_id",nullable=false)
    private UserStory associatedUserStory;


    @Override
    public String toString(){
        return "Task {"+"/n" +
                "id = "+ this.id+"/n" +
                "Title = "+this.task_title +"/n" +
                "Description = "+ this.description +"/n" +
                "Progress = "+this.progress +"/n" +
                "Task Owner = "+this.taskOwner +"/n"+
                "Tag = "+this.tag +"/n"+
                 "User Story Associated = "+this.associatedUserStory+"}";
    }


}
