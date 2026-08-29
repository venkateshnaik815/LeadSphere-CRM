package com.leadsphere.crm.patterns;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
  RequestRepository requestRepository;
  RequestStateMachine requestStateMachine;

  public RequestService(
      RequestRepository requestRepository, RequestStateMachine requestStateMachine) {
    this.requestRepository = requestRepository;
    this.requestStateMachine = requestStateMachine;
  }

  public Request create(UUID uuid) {
    Optional<Request> optReq = requestRepository.findById(uuid);
    return optReq.orElseGet(() -> requestRepository.save(new Request(uuid)));
  }

  public Request start(UUID uuid) {
    Optional<Request> optReq = requestRepository.findById(uuid);
    if (optReq.isEmpty()) {
      throw new RequestNotFoundException(uuid);
    }
    return requestRepository.save(requestStateMachine.next(optReq.get(), Request.Status.STARTED));
  }

  public Request complete(UUID uuid) {
    Optional<Request> optReq = requestRepository.findById(uuid);
    if (optReq.isEmpty()) {
      throw new RequestNotFoundException(uuid);
    }
    return requestRepository.save(requestStateMachine.next(optReq.get(), Request.Status.COMPLETED));
  }
}
