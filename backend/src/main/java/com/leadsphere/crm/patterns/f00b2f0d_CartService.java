package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.CartItem;
import java.util.List;

public interface CartService {

  List<CartItem> getCart(String userId);
}
