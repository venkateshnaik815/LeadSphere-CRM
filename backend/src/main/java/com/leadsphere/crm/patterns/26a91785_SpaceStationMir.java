package com.leadsphere.crm.patterns;

import com.iluwatar.doubledispatch.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpaceStationMir extends GameObject {

  public SpaceStationMir(int left, int top, int right, int bottom) {
    super(left, top, right, bottom);
  }

  @Override
  public void collision(GameObject gameObject) {
    gameObject.collisionResolve(this);
  }

  @Override
  public void collisionResolve(FlamingAsteroid asteroid) {
    LOGGER.info(
        AppConstants.HITS + " {} is damaged! {} is set on fire!",
        asteroid.getClass().getSimpleName(),
        this.getClass().getSimpleName(),
        this.getClass().getSimpleName(),
        this.getClass().getSimpleName());
    setDamaged(true);
    setOnFire(true);
  }

  @Override
  public void collisionResolve(Meteoroid meteoroid) {
    logHits(meteoroid);
    setDamaged(true);
  }

  @Override
  public void collisionResolve(SpaceStationMir mir) {
    logHits(mir);
    setDamaged(true);
  }

  @Override
  public void collisionResolve(SpaceStationIss iss) {
    logHits(iss);
    setDamaged(true);
  }

  private void logHits(GameObject gameObject) {
    LOGGER.info(
        AppConstants.HITS,
        " {} is damaged!",
        gameObject.getClass().getSimpleName(),
        this.getClass().getSimpleName(),
        this.getClass().getSimpleName());
  }
}
