package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Letter extends LetterComposite {

  private final char character;

  @Override
  protected void printThisBefore() {
    System.out.print(character);
  }
}
