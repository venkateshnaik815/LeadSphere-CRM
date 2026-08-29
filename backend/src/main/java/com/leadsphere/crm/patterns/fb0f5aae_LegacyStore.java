package com.leadsphere.crm.patterns;

import com.iluwatar.corruption.system.DataStore;
import org.springframework.stereotype.Service;

@Service
public class LegacyStore extends DataStore<LegacyOrder> {}
