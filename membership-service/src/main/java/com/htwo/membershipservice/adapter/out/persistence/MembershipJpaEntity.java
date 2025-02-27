package com.htwo.membershipservice.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MembershipJpaEntity {

  @Id
  @GeneratedValue
  private Long membershipId;
  private String name;
  private String email;
  private String address;
  private boolean isValid;
  private boolean isCorp;

  @Builder
  private MembershipJpaEntity(
      String name,
      String email,
      String address,
      boolean isValid,
      boolean isCorp
  ) {
    this.name = name;
    this.email = email;
    this.address = address;
    this.isValid = isValid;
    this.isCorp = isCorp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MembershipJpaEntity that = (MembershipJpaEntity) o;
    return Objects.equals(membershipId, that.membershipId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(membershipId);
  }
}
