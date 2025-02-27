package com.htwo.membershipservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
public class Membership {
  private final String membershipId;
  private final String name;
  private final String email;
  private final String address;
  private final boolean isValid;
  private final boolean isCorp;

  @Builder(access = AccessLevel.PRIVATE)
  private Membership(
      String membershipId,
      String name,
      String email,
      String address,
      boolean isValid,
      boolean isCorp
  ) {
    this.membershipId = membershipId;
    this.name = name;
    this.email = email;
    this.address = address;
    this.isValid = isValid;
    this.isCorp = isCorp;
  }

  public static Membership generateMember(
      MembershipId membershipId,
      MembershipName membershipName,
      MembershipEmail membershipEmail,
      MembershipAddress membershipAddress,
      MembershipIsValid membershipIsValid,
      MembershipIsCorp membershipIsCorp
  ) {
    return Membership.builder()
        .membershipId(membershipId.membershipId)
        .name(membershipName.nameValue)
        .email(membershipEmail.emailValue)
        .address(membershipAddress.addressValue)
        .isValid(membershipIsValid.isValidValue)
        .isCorp(membershipIsCorp.isCorpValue)
        .build();
  }

  @Value
  public static class MembershipId {
    public MembershipId(String value) {
      this.membershipId = value;
    }
    String membershipId ;
  }

  @Value
  public static class MembershipName {
    public MembershipName(String value) {
      this.nameValue = value;
    }

    String nameValue;
  }
  @Value
  public static class MembershipEmail {
    public MembershipEmail(String value) {
      this.emailValue = value;
    }
    String emailValue;
  }

  @Value
  public static class MembershipAddress {
    public MembershipAddress(String value) {
      this.addressValue = value;
    }
    String addressValue;
  }

  @Value
  public static class MembershipIsValid {
    public MembershipIsValid(boolean value) {
      this.isValidValue = value;
    }
    boolean isValidValue;
  }

  @Value
  public static class MembershipIsCorp {
    boolean isCorpValue;

    public MembershipIsCorp(boolean isCorpValue) {
      this.isCorpValue = isCorpValue;
    }
  }
}
