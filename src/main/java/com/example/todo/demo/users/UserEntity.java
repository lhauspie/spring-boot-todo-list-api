package com.example.todo.demo.users;

import com.example.todo.demo.AbstractBaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
@Data
public class UserEntity extends AbstractBaseEntity {

	private String firstName;

	private String lasstName;
}
