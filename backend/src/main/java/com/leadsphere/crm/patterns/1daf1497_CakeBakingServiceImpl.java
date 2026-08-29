
package com.leadsphere.crm.patterns;

import dao.CakeDao;
import dao.CakeLayerDao;
import dao.CakeToppingDao;
import dto.CakeInfo;
import dto.CakeLayerInfo;
import dto.CakeToppingInfo;
import entity.Cake;
import entity.CakeLayer;
import entity.CakeTopping;
import exception.CakeBakingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CakeBakingServiceImpl implements CakeBakingService {

  private final CakeDao cakeDao;
  private final CakeLayerDao cakeLayerDao;
  private final CakeToppingDao cakeToppingDao;

  @Autowired
  public CakeBakingServiceImpl(
      CakeDao cakeDao, CakeLayerDao cakeLayerDao, CakeToppingDao cakeToppingDao) {
    this.cakeDao = cakeDao;
    this.cakeLayerDao = cakeLayerDao;
    this.cakeToppingDao = cakeToppingDao;
  }

  @Override
  public void bakeNewCake(CakeInfo cakeInfo) throws CakeBakingException {
    var allToppings = getAvailableToppingEntities();
    var matchingToppings =
        allToppings.stream()
            .filter(t -> t.getName().equals(cakeInfo.cakeToppingInfo.name))
            .toList();
    if (matchingToppings.isEmpty()) {
      throw new CakeBakingException(
          String.format("Topping %s is not available", cakeInfo.cakeToppingInfo.name));
    }
    var allLayers = getAvailableLayerEntities();
    Set<CakeLayer> foundLayers = new HashSet<>();
    for (var info : cakeInfo.cakeLayerInfos) {
      var found = allLayers.stream().filter(layer -> layer.getName().equals(info.name)).findFirst();
      if (found.isEmpty()) {
        throw new CakeBakingException(String.format("Layer %s is not available", info.name));
      } else {
        foundLayers.add(found.get());
      }
    }

    var topping = cakeToppingDao.findById(matchingToppings.iterator().next().getId());
    if (topping.isPresent()) {
      var cake = new Cake();
      cake.setTopping(topping.get());
      cake.setLayers(foundLayers);
      cakeDao.save(cake);
      topping.get().setCake(cake);
      cakeToppingDao.save(topping.get());
      Set<CakeLayer> foundLayersToUpdate =
          new HashSet<>(foundLayers); // copy set to avoid a ConcurrentModificationException

      for (var layer : foundLayersToUpdate) {
        layer.setCake(cake);
        cakeLayerDao.save(layer);
      }

    } else {
      throw new CakeBakingException(
          String.format("Topping %s is not available", cakeInfo.cakeToppingInfo.name));
    }
  }

  @Override
  public void saveNewTopping(CakeToppingInfo toppingInfo) {
    cakeToppingDao.save(new CakeTopping(toppingInfo.name, toppingInfo.calories));
  }

  @Override
  public void saveNewLayer(CakeLayerInfo layerInfo) {
    cakeLayerDao.save(new CakeLayer(layerInfo.name, layerInfo.calories));
  }

  private List<CakeTopping> getAvailableToppingEntities() {
    List<CakeTopping> result = new ArrayList<>();
    for (CakeTopping topping : cakeToppingDao.findAll()) {
      if (topping.getCake() == null) {
        result.add(topping);
      }
    }
    return result;
  }

  @Override
  public List<CakeToppingInfo> getAvailableToppings() {
    List<CakeToppingInfo> result = new ArrayList<>();
    for (CakeTopping next : cakeToppingDao.findAll()) {
      if (next.getCake() == null) {
        result.add(new CakeToppingInfo(next.getId(), next.getName(), next.getCalories()));
      }
    }
    return result;
  }

  private List<CakeLayer> getAvailableLayerEntities() {
    List<CakeLayer> result = new ArrayList<>();
    for (CakeLayer next : cakeLayerDao.findAll()) {
      if (next.getCake() == null) {
        result.add(next);
      }
    }
    return result;
  }

  @Override
  public List<CakeLayerInfo> getAvailableLayers() {
    List<CakeLayerInfo> result = new ArrayList<>();
    for (CakeLayer next : cakeLayerDao.findAll()) {
      if (next.getCake() == null) {
        result.add(new CakeLayerInfo(next.getId(), next.getName(), next.getCalories()));
      }
    }
    return result;
  }

  @Override
  public void deleteAllCakes() {
    cakeDao.deleteAll();
  }

  @Override
  public void deleteAllLayers() {
    cakeLayerDao.deleteAll();
  }

  @Override
  public void deleteAllToppings() {
    cakeToppingDao.deleteAll();
  }

  @Override
  public List<CakeInfo> getAllCakes() {
    List<CakeInfo> result = new ArrayList<>();
    for (Cake cake : cakeDao.findAll()) {
      var cakeToppingInfo =
          new CakeToppingInfo(
              cake.getTopping().getId(),
              cake.getTopping().getName(),
              cake.getTopping().getCalories());
      List<CakeLayerInfo> cakeLayerInfos = new ArrayList<>();
      for (var layer : cake.getLayers()) {
        cakeLayerInfos.add(new CakeLayerInfo(layer.getId(), layer.getName(), layer.getCalories()));
      }
      var cakeInfo = new CakeInfo(cake.getId(), cakeToppingInfo, cakeLayerInfos);
      result.add(cakeInfo);
    }
    return result;
  }
}
