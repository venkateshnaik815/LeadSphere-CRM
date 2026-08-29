package com.leadsphere.crm.patterns;

public class Skeleton extends Entity {

  private static final int PATROLLING_LEFT_BOUNDING = 0;

  private static final int PATROLLING_RIGHT_BOUNDING = 100;

  protected boolean patrollingLeft;

  public Skeleton(int id) {
    super(id);
    patrollingLeft = false;
  }

  public Skeleton(int id, int position) {
    super(id);
    this.position = position;
    patrollingLeft = false;
  }

  @Override
  public void update() {
    if (patrollingLeft) {
      position -= 1;
      if (position == PATROLLING_LEFT_BOUNDING) {
        patrollingLeft = false;
      }
    } else {
      position += 1;
      if (position == PATROLLING_RIGHT_BOUNDING) {
        patrollingLeft = true;
      }
    }
    logger.info("Skeleton {} is on position {}.", id, position);
  }
}
