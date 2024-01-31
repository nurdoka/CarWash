import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Vehicle } from '../models/vehicle';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private url = environment.baseUrl + 'api/vehicles';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.url,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VehicleService.index(): error retrieving vehicles: ' + err)
        );
      })
    );
  }

  addVehicle(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.post<Vehicle>(this.url, vehicle, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VehicleService.addVehicle(): error adding vehicle: ' + err)
        );
      })
    );
  }

  updateVehicle(vehicle: Vehicle): Observable<Vehicle>{
    return this.http.put<Vehicle>(this.url, vehicle, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VehicleService.updateVehicle(): error updating vehicle: ' + err)
        );
      })
    );
  }

  deleteVehicle(vehicleId: number): Observable<Vehicle>{
    return this.http.delete<Vehicle>(this.url + '/' + vehicleId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VehicleService.deleteVehicle(): error deleting vehicle: ' + err)
        )
      })
    );
  }
}
