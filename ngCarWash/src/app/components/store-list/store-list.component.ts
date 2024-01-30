import { Component, OnInit } from '@angular/core';
import { Store } from '../../models/store';
import { StoreService } from '../../services/store.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-store-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './store-list.component.html',
  styleUrl: './store-list.component.css'
})
export class StoreListComponent implements OnInit{

  stores : Store[] = [];

  constructor(
    private storeService : StoreService
  ){}

  ngOnInit(): void {
    this.reload();
  }

  reload():void{
    this.storeService.index().subscribe(
      {
        next: (stores) => {
          this.stores = stores;
        },
        error: (problem) => {
          console.error('storeService.index(): error loading stores:');
          console.error(problem);
        }
      }
    );
  }
}
