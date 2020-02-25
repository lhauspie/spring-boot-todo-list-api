package com.example.todo.demo.tasks;

import com.example.todo.demo.AbstractBaseEntity;
import com.example.todo.demo.users.User;
import com.example.todo.demo.users.UserEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_task")
public class TaskEntity extends AbstractBaseEntity {

	@Column(name = "label")
	private String label;

	@ManyToOne
	private UserEntity assignee;
}
