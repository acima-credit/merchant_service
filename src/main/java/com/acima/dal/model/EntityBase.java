package com.acima.dal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class EntityBase {
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private UUID id;

    @Column(name = "created_at")
    @JsonProperty(value = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @JsonProperty(value = "updated_at")
    @NotNull
    private LocalDateTime update_at;

    @Column(name = "cdeleted_at")
    @JsonProperty(value = "deleted_at")
    private LocalDateTime deleted_at;

    @Column(name = "deactivated_at")
    @JsonProperty(value = "deactivated_at")
    private LocalDateTime deactivated_at;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id;  }

    public LocalDateTime getCreated_at() {  return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public LocalDateTime getUpdate_at() { return update_at; }
    public void setUpdate_at(LocalDateTime update_at) { this.update_at = update_at; }

    public LocalDateTime getDeleted_at() { return deleted_at; }
    public void setDeleted_at(LocalDateTime deleted_at) { this.deleted_at = deleted_at; }

    public LocalDateTime getDeactivated_at() { return deactivated_at; }
    public void setDeactivated_at(LocalDateTime deactivated_at) { this.deactivated_at = deactivated_at; }
}
