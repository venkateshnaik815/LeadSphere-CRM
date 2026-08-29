package com.leadsphere.crm.patterns;

interface Handler<I, O> {
  O process(I input);
}
