package com.example.todo.demo;

import com.example.todo.demo.tasks.TaskEntity;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

//import javax.persistence.*;

//@MappedSuperclass
public abstract class AbstractBaseEntity implements Persistable<UUID> {

//	@Id
//	@Column(name = "id", length = 16, unique = true, nullable = false)
	private UUID id = UUID.randomUUID();

//	@Transient
	private Boolean persisted = false;

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public AbstractBaseEntity() {
		persisted = false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TaskEntity that = (TaskEntity) o;
		return getId().equals(that.getId());
	}

//	@PostPersist
//	@PostLoad
	private void setPersisted() {
		persisted = true;
	}

	public <T extends AbstractBaseEntity> T setId(UUID id) {
		this.id = id;
		persisted = true;
		return (T) this;
	}

	public UUID getId() {
		return id;
	}
}
