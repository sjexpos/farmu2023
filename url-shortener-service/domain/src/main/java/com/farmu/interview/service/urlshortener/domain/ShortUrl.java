package com.farmu.interview.service.urlshortener.domain;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;
import org.hibernate.annotations.Cache;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "short_url")
@Cache(region = "shortUrlCache", usage = CacheConcurrencyStrategy.READ_WRITE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortUrl extends DomainEntity implements Auditable {
    private static final long serialVersionUID = -4164258600038645847L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "key")
    private String key;
    @Column(name = "destination")
    private String destination;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    public String getUrl(String baseDomain, String dispatcherContext) {
        return new StringBuilder(baseDomain)
                .append(StringUtils.hasText(dispatcherContext) ? dispatcherContext : "")
                .append("/")
                .append(this.key).toString();
    }

}
