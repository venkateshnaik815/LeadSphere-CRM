package com.leadsphere.crm.patterns;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class VirtualMachine {

  private final Stack<Integer> stack = new Stack<>();

  private final Wizard[] wizards = new Wizard[2];

  public VirtualMachine() {
    wizards[0] = new Wizard(randomInt(3, 32), randomInt(3, 32), randomInt(3, 32), 0, 0);
    wizards[1] = new Wizard(randomInt(3, 32), randomInt(3, 32), randomInt(3, 32), 0, 0);
  }

  public VirtualMachine(Wizard wizard1, Wizard wizard2) {
    wizards[0] = wizard1;
    wizards[1] = wizard2;
  }

  public void execute(int[] bytecode) {
    for (var i = 0; i < bytecode.length; i++) {
      Instruction instruction = Instruction.getInstruction(bytecode[i]);
      switch (instruction) {
        case LITERAL -> { // Read the next byte from the bytecode.
          int value = bytecode[++i];
          // Push the next value to stack
          stack.push(value);
        }
        case SET_AGILITY -> {
          var amount = stack.pop();
          var wizard = stack.pop();
          setAgility(wizard, amount);
        }
        case SET_WISDOM -> {
          var amount = stack.pop();
          var wizard = stack.pop();
          setWisdom(wizard, amount);
        }
        case SET_HEALTH -> {
          var amount = stack.pop();
          var wizard = stack.pop();
          setHealth(wizard, amount);
        }
        case GET_HEALTH -> {
          var wizard = stack.pop();
          stack.push(getHealth(wizard));
        }
        case GET_AGILITY -> {
          var wizard = stack.pop();
          stack.push(getAgility(wizard));
        }
        case GET_WISDOM -> {
          var wizard = stack.pop();
          stack.push(getWisdom(wizard));
        }
        case ADD -> {
          var a = stack.pop();
          var b = stack.pop();
          stack.push(a + b);
        }
        case DIVIDE -> {
          var a = stack.pop();
          var b = stack.pop();
          stack.push(b / a);
        }
        case PLAY_SOUND -> {
          var wizard = stack.pop();
          getWizards()[wizard].playSound();
        }
        case SPAWN_PARTICLES -> {
          var wizard = stack.pop();
          getWizards()[wizard].spawnParticles();
        }
        default -> {
          throw new IllegalArgumentException("Invalid instruction value");
        }
      }
      LOGGER.info("Executed " + instruction.name() + ", Stack contains " + getStack());
    }
  }

  public void setHealth(int wizard, int amount) {
    wizards[wizard].setHealth(amount);
  }

  public void setWisdom(int wizard, int amount) {
    wizards[wizard].setWisdom(amount);
  }

  public void setAgility(int wizard, int amount) {
    wizards[wizard].setAgility(amount);
  }

  public int getHealth(int wizard) {
    return wizards[wizard].getHealth();
  }

  public int getWisdom(int wizard) {
    return wizards[wizard].getWisdom();
  }

  public int getAgility(int wizard) {
    return wizards[wizard].getAgility();
  }

  private int randomInt(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }
}
