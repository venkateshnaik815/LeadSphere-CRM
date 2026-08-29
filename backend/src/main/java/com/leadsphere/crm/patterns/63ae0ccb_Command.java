package com.leadsphere.crm.patterns;

import com.iluwatar.model.view.controller.Fatigue;
import com.iluwatar.model.view.controller.Health;
import com.iluwatar.model.view.controller.Nourishment;

public record Command(Fatigue fatigue, Health health, Nourishment nourishment) {}
