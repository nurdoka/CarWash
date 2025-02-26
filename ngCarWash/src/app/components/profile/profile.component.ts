import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { VehicleService } from '../../services/vehicle.service';
import { Vehicle } from '../../models/vehicle';
import { StoreRatingComponent } from '../store-rating/store-rating.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    StoreRatingComponent
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  loggedUser: User = new User();
  edituser: User | null = null;
  newVehicle : Vehicle | null = null;
  vehicles : Vehicle[] = [];
  selectedVehicle : Vehicle | null = null;

  constructor(
    private auth: AuthService,
    private userServ: UserService,
    private vehicleService : VehicleService
    ){}

  ngOnInit(): void {
    this.getUser();
    this.vehicleList();
  }

  displayVehicle(vehicle:Vehicle):void{
    this.selectedVehicle = vehicle;
  }

  updateVehicle(vehicle:Vehicle):void{
    this.vehicleService.updateVehicle(vehicle).subscribe({
      next : (returnedVehicle) => {
        this.selectedVehicle = null;
      },
      error : (err) => {
        console.log('Error updating vehicle: ' + err);
      }
    });
  }

  deleteVehicle(vehicleId: number):void{
    this.vehicleService.deleteVehicle(vehicleId).subscribe({
      next : (returnedVehicle) => {
        this.selectedVehicle = null;
        this.vehicleList();
      }
    });
  }

  setAddVehicle():void{
    this.newVehicle = new Vehicle();
  }

  vehicleList():void{
    this.vehicleService.index().subscribe({
      next: (vehicles) => {
        this.vehicles = vehicles;
        console.log('Retrieved vehicle list');
      },
      error : (err) => {
        console.log('Error retrieving vehicleList(): ' + err);
      }
    });
  }

  addVehicle(vehicle: Vehicle):void{
    this.vehicleService.addVehicle(vehicle).subscribe({
      next: (addedVehicle) => {
        console.log('Vehicle added');
        this.vehicleList();
        this.newVehicle = null;
      },
      error : (err) => {
        console.log('Error adding vehicle: ' + err);
      }
    });
  }

  editUser():void{
    this.edituser = Object.assign({}, this.loggedUser);
  }

  updateUser(user: User):void{
    this.userServ.updateUser(user).subscribe({
      next: (updatedUser) => {
        this.loggedUser = updatedUser;
        this.edituser = null;
      },
      error: (err) => {
        console.log('Error updating user' + err);
      }
    });
  }

  getUser():void{
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedUser = user;
        console.log(user);
      },
      error: (problem) => {
        console.log('Error getting user: ' + problem);
      }
    });
  }

}
