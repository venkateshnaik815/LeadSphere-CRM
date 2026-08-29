package com.leadsphere.crm.patterns;

public class GameController {

  protected final Bullet bullet;

  public GameController() {
    bullet = new Bullet();
  }

  public void moveBullet(float offset) {
    var currentPosition = bullet.getPosition();
    bullet.setPosition(currentPosition + offset);
  }

  public float getBulletPosition() {
    return bullet.getPosition();
  }
}
