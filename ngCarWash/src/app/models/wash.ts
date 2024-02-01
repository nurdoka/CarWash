import { empty } from "rxjs";
import { Store } from "./store";
import { Vehicle } from "./vehicle";

// wash.model.ts
export class Wash {
  id: number;
  vehicle: Vehicle;
  store: Store;
  createDate: string;

  constructor(
    id: number = 0,
    vehicle: Vehicle = new Vehicle(),
    store: Store = new Store(),
    createDate: string = ''
  ) {
    this.id = id;
    this.vehicle = vehicle;
    this.store = store;
    this.createDate = createDate;
  }
}

  