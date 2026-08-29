
package com.leadsphere.crm.patterns;

import dto.CakeInfo;
import dto.CakeLayerInfo;
import dto.CakeToppingInfo;
import exception.CakeBakingException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CakeBakingService {

  void bakeNewCake(CakeInfo cakeInfo) throws CakeBakingException;

  List<CakeInfo> getAllCakes();

  void saveNewTopping(CakeToppingInfo toppingInfo);

  List<CakeToppingInfo> getAvailableToppings();

  void saveNewLayer(CakeLayerInfo layerInfo);

  List<CakeLayerInfo> getAvailableLayers();

  void deleteAllCakes();

  void deleteAllLayers();

  void deleteAllToppings();
}
