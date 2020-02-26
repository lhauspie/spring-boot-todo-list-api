package com.example.todo.demo.tasks;

import com.example.todo.demo.users.UserEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;


@Data
@Accessors(chain = true)
public class TaskEntity {

	@Id
	private UUID id = UUID.randomUUID();
	private String label;
	private UserEntity assignee;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TaskEntity that = (TaskEntity) o;
		return getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
