import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import LoginDTO from '../../dto/login-dto';
import { DataTransferService } from '../../services/data-transfer/data-transfer.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrl: './subscription.component.scss'
})
export class SubscriptionComponent implements OnInit{

  urlSubscription: string = '';
  loginDtos: LoginDTO = new LoginDTO({});

  constructor(private router: Router, private dataTransferService: DataTransferService) {}
  
  ngOnInit(): void {
    this.urlSubscription = this.dataTransferService.getData();
  }

  openSubcriptionTab() {
    window.open(this.urlSubscription, '_blank');
    this.goToDashboard();
  }

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }

}
