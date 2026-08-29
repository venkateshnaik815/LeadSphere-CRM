package com.leadsphere.crm.patterns;

public class QuickRemoteService implements RemoteService {

  @Override
  public String call() throws RemoteServiceException {
    return "Quick Service is working";
  }
}
