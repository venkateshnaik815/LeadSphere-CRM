package com.leadsphere.crm.patterns;

import static com.iluwatar.iterator.list.ItemType.ANY;
import static com.iluwatar.iterator.list.ItemType.POTION;
import static com.iluwatar.iterator.list.ItemType.RING;
import static com.iluwatar.iterator.list.ItemType.WEAPON;

import com.iluwatar.iterator.bst.BstIterator;
import com.iluwatar.iterator.bst.TreeNode;
import com.iluwatar.iterator.list.ItemType;
import com.iluwatar.iterator.list.TreasureChest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final TreasureChest TREASURE_CHEST = new TreasureChest();

  private static void demonstrateTreasureChestIteratorForType(ItemType itemType) {
    LOGGER.info("------------------------");
    LOGGER.info("Item Iterator for ItemType " + itemType + ": ");
    var itemIterator = TREASURE_CHEST.iterator(itemType);
    while (itemIterator.hasNext()) {
      LOGGER.info(itemIterator.next().toString());
    }
  }

  private static void demonstrateBstIterator() {
    LOGGER.info("------------------------");
    LOGGER.info("BST Iterator: ");
    var root = buildIntegerBst();
    var bstIterator = new BstIterator<>(root);
    while (bstIterator.hasNext()) {
      LOGGER.info("Next node: " + bstIterator.next().getVal());
    }
  }

  private static TreeNode<Integer> buildIntegerBst() {
    var root = new TreeNode<>(8);

    root.insert(3);
    root.insert(10);
    root.insert(1);
    root.insert(6);
    root.insert(14);
    root.insert(4);
    root.insert(7);
    root.insert(13);

    return root;
  }

  public static void main(String[] args) {
    demonstrateTreasureChestIteratorForType(RING);
    demonstrateTreasureChestIteratorForType(POTION);
    demonstrateTreasureChestIteratorForType(WEAPON);
    demonstrateTreasureChestIteratorForType(ANY);

    demonstrateBstIterator();
  }
}
