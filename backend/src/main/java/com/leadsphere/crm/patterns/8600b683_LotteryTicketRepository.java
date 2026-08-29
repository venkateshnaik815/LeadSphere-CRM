package com.leadsphere.crm.patterns;

import com.iluwatar.hexagonal.domain.LotteryTicket;
import com.iluwatar.hexagonal.domain.LotteryTicketId;
import java.util.Map;
import java.util.Optional;

public interface LotteryTicketRepository {

  Optional<LotteryTicket> findById(LotteryTicketId id);

  Optional<LotteryTicketId> save(LotteryTicket ticket);

  Map<LotteryTicketId, LotteryTicket> findAll();

  void deleteAll();
}
