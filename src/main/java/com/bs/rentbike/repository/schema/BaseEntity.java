package com.bs.rentbike.repository.schema;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;


@Getter
@Accessors(chain = true)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @CreatedDate
  @Column(name = "CREATE_DATE_TIME", updatable = false)
  private OffsetDateTime createDateTime;

  @LastModifiedDate
  @Column(name = "UPDATE_DATE_TIME")
  private OffsetDateTime updateDateTime;

  public BaseEntity setId(Integer id) {
    this.id = Long.getLong(id.toString());
    return this;
  }

  public BaseEntity setId(Long id) {
    this.id = id;
    return this;
  }
}
