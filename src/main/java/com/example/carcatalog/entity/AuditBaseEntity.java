package com.example.carcatalog.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base class for all entities that need to be audited.
 * <p>
 *     The annotations are:
 *     <ul>
 *         <li>{@link MappedSuperclass} - the class is not an entity, but it is the base class for all entities</li>
 *     </ul>
 *     The fields are:
 *     <ul>
 *         <li>{@link LocalDateTime} created - the date and time of creation</li>
 *         <li>{@link LocalDateTime} modified - the date and time of modification</li>
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AuditBaseEntity extends BaseEntity {
    private LocalDateTime created;
    private LocalDateTime modified;

    /**
     * Method that updates the modified field before the entity is updated.
     */
    @PreUpdate
    private void updateModified() {
        modified = LocalDateTime.now();
    }

    /**
     * Method that updates the created field before the entity is persisted.
     */
    @PrePersist
    private void updateCreated() {
        created = LocalDateTime.now();
    }
}
