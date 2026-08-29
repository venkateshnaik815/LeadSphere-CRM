package com.leadsphere.crm.patterns;

public class Statue extends Entity {

  protected int frames;

  protected int delay;

  public Statue(int id) {
    super(id);
    this.frames = 0;
    this.delay = 0;
  }

  public Statue(int id, int delay) {
    super(id);
    this.frames = 0;
    this.delay = delay;
  }

  @Override
  public void update() {
    if (++frames == delay) {
      shootLightning();
      frames = 0;
    }
  }

  private void shootLightning() {
    logger.info("Statue {} shoots lightning!", id);
  }
}
