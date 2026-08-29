package com.leadsphere.crm.patterns;

import com.iluwatar.lockableobject.Lockable;
import java.security.SecureRandom;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Feind implements Runnable {

  private final Creature creature;
  private final Lockable target;
  private final SecureRandom random;
  private static final Logger LOGGER = LoggerFactory.getLogger(Feind.class.getName());

  public Feind(@NonNull Creature feind, @NonNull Lockable target) {
    this.creature = feind;
    this.target = target;
    this.random = new SecureRandom();
  }

  @Override
  public void run() {
    if (!creature.acquire(target)) {
      fightForTheSword(creature, target.getLocker(), target);
    } else {
      LOGGER.info("{} has acquired the sword!", target.getLocker().getName());
    }
  }

  private void fightForTheSword(Creature reacher, @NonNull Creature holder, Lockable sword) {
    LOGGER.info("A duel between {} and {} has been started!", reacher.getName(), holder.getName());
    boolean randBool;
    while (this.target.isLocked() && reacher.isAlive() && holder.isAlive()) {
      randBool = random.nextBoolean();
      if (randBool) {
        reacher.attack(holder);
      } else {
        holder.attack(reacher);
      }
    }
    if (reacher.isAlive()) {
      if (!reacher.acquire(sword)) {
        fightForTheSword(reacher, sword.getLocker(), sword);
      } else {
        LOGGER.info("{} has acquired the sword!", reacher.getName());
      }
    }
  }
}
