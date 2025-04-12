package com.htwo.moneyservice.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.htwo.moneyservice.adapter.axon.command.CreateMemberMoneyCommand;
import com.htwo.moneyservice.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.htwo.moneyservice.adapter.axon.event.MemberMoneyCreatedEvent;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@NoArgsConstructor
public class MemberMoneyAggregate {

  @AggregateIdentifier
  private String id;
  private Long membershipId;
  private String balance;

  @CommandHandler
  public MemberMoneyAggregate(@NotNull CreateMemberMoneyCommand command) {
    log.info("CreateMemberMoneyCommand create MemberMoneyCreatedEvent");
    apply(new MemberMoneyCreatedEvent(command.getTargetMembershipId()));
  }

  @CommandHandler
  public String handle(@NotNull IncreaseMoneyRequestCommand command) {
    log.info("IncreaseMoneyRequestCommand IncreaseMemberMoneyEvent");
    this.id = command.getAggregateIdentifier();
    apply(
        new IncreaseMemberMoneyEvent(
            command.getTargetMembershipId(),
            command.getAggregateIdentifier(),
            command.getMoneyAmount()
        )
    );
    return id;
  }

  @EventSourcingHandler
  public void on(MemberMoneyCreatedEvent event) {
    log.info("call memberMoneyCreatedEvent handler");
    id = UUID.randomUUID().toString();
    membershipId = Long.parseLong(event.getTargetMembershipId());
    balance = "0";
  }

  @EventSourcingHandler
  public void on(IncreaseMemberMoneyEvent event) {
    log.info("call IncreaseMemberMoneyEvent handler");
    id = event.getAggregateIdentifier();
    membershipId = Long.parseLong(event.getTargetMembershipId());
    balance = event.getAmount();
  }

}
