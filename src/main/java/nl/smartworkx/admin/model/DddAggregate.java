package nl.smartworkx.admin.model;

import java.time.LocalDateTime;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface DddAggregate extends DddEntity {

    LocalDateTime getCreationDateTime();
}
