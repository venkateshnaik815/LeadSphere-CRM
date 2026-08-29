package com.leadsphere.crm.patterns;

import com.iluwatar.component.component.graphiccomponent.GraphicComponent;
import com.iluwatar.component.component.graphiccomponent.ObjectGraphicComponent;
import com.iluwatar.component.component.inputcomponent.DemoInputComponent;
import com.iluwatar.component.component.inputcomponent.InputComponent;
import com.iluwatar.component.component.inputcomponent.PlayerInputComponent;
import com.iluwatar.component.component.physiccomponent.ObjectPhysicComponent;
import com.iluwatar.component.component.physiccomponent.PhysicComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameObject {
  private final InputComponent inputComponent;
  private final PhysicComponent physicComponent;
  private final GraphicComponent graphicComponent;

  private final String name;
  private int velocity = 0;
  private int coordinate = 0;

  public static GameObject createPlayer() {
    return new GameObject(
        new PlayerInputComponent(),
        new ObjectPhysicComponent(),
        new ObjectGraphicComponent(),
        "player");
  }

  public static GameObject createNpc() {
    return new GameObject(
        new DemoInputComponent(), new ObjectPhysicComponent(), new ObjectGraphicComponent(), "npc");
  }

  public void demoUpdate() {
    inputComponent.update(this, 0);
    physicComponent.update(this);
    graphicComponent.update(this);
  }

  public void update(int e) {
    inputComponent.update(this, e);
    physicComponent.update(this);
    graphicComponent.update(this);
  }

  public void updateVelocity(int acceleration) {
    this.velocity += acceleration;
  }

  public void updateCoordinate() {
    this.coordinate += this.velocity;
  }
}
