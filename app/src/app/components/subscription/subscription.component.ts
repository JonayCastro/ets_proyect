import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import LoginDTO from '../../dto/login-dto';
import { DataTransferService } from '../../services/data-transfer/data-transfer.service';

/**
 * SubscriptionComponent is responsible for handling the subscription process.
 * It retrieves the subscription URL from the DataTransferService and opens it in a new tab.
 * After opening the subscription URL, it navigates to the dashboard.
 */
@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.scss']
})
export class SubscriptionComponent implements OnInit{

  urlSubscription: string = '';
  loginDtos: LoginDTO = new LoginDTO({});

  constructor(private router: Router, private dataTransferService: DataTransferService) {}
  
  ngOnInit(): void {
    this.urlSubscription = this.dataTransferService.getData();
    this.dataTransferService.clearData();
  }

  openSubcriptionTab() {
    window.open(this.urlSubscription, '_blank');
    this.goToDashboard();
  }

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }

}
