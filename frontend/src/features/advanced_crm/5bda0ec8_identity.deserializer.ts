// @ts-nocheck
import { Deserializer } from '../interfaces/deserializer.interface.js';

export class IdentityDeserializer implements Deserializer {
  deserialize(value: any) {
    return value;
  }
}
