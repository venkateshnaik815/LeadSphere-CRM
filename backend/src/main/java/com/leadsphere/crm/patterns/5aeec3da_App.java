package com.leadsphere.crm.patterns;

import com.iluwatar.masterworker.system.ArrayTransposeMasterWorker;
import com.iluwatar.masterworker.system.MasterWorker;
import com.iluwatar.masterworker.system.systemmaster.ArrayTransposeMaster;
import com.iluwatar.masterworker.system.systemmaster.Master;
import com.iluwatar.masterworker.system.systemworkers.ArrayTransposeWorker;
import com.iluwatar.masterworker.system.systemworkers.Worker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var mw = new ArrayTransposeMasterWorker();
    var rows = 10;
    var columns = 20;
    var inputMatrix = ArrayUtilityMethods.createRandomIntMatrix(rows, columns);
    var input = new ArrayInput(inputMatrix);
    var result = (ArrayResult) mw.getResult(input);
    if (result != null) {
      ArrayUtilityMethods.printMatrix(inputMatrix);
      ArrayUtilityMethods.printMatrix(result.data);
    } else {
      LOGGER.info("Please enter non-zero input");
    }
  }
}
