package com.farmu.interview.service.urlshortener.orm;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.farmu.interview.service.urlshortener.domain.Auditable;

@Component
public class CustomAuditingEntityListener {
    private ObjectFactory<AuditingHandler> handler; 
    
    public CustomAuditingEntityListener(ObjectFactory<AuditingHandler> auditingHandler) {
        this.handler = auditingHandler;
    }

	@PrePersist
	public void touchForCreate(Object target) {
		Assert.notNull(target, "Entity must not be null!");
		if (handler != null) {
			AuditingHandler object = handler.getObject();
			if (object != null) {
				object.markCreated(target);
			}
		}
	}

	@PreUpdate
	public void touchForUpdate(Object target) {
		Assert.notNull(target, "Entity must not be null!");
		if (handler != null) {
			AuditingHandler object = handler.getObject();
			if (object != null) {
				object.markModified(target);
                if (Auditable.class.isAssignableFrom(target.getClass()) && ((Auditable)target).isDeleted() && ((Auditable)target).getDeletedBy() == null) {
                    ((Auditable)target).setDeletedBy( ((Auditable)target).getModifiedBy() );
                }
			}
		}
	}
    
}
