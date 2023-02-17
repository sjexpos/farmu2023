package com.farmu.interview.service.urlshortener.domain;

import java.time.LocalDateTime;

public interface Auditable {
    public static final String DELETED_AT_PROPERTY_NAME = "deletedAt";

    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime createdAt);
    String getCreatedBy();
    void setCreatedBy(String createdBy);

    LocalDateTime getModifiedAt();
    void setModifiedAt(LocalDateTime modifiedAt);
    String getModifiedBy();
    void setModifiedBy(String modifiedBy);

}
