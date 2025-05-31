import { Component, Input, OnInit } from '@angular/core';
import OffersDTO from '../../dto/offers-dto';
import AppConstants from '../../config/app-constants';

@Component({
  selector: 'app-offers-detail',
  templateUrl: './offers-detail.component.html',
  styleUrl: './offers-detail.component.scss'
})
export class OffersDetailComponent implements OnInit {

  @Input() offerDto: OffersDTO = new OffersDTO({});
  offerLink: string = '';
  baseUrl: string = AppConstants.SNEAKER_BASE_URL;
  baseImageUrl: string = AppConstants.IMAGE_BASE_URL_PATH;
  imageSrc: string = '';

  ngOnInit(): void {
    this.offerLink = this.baseUrl + this.offerDto.link;
    this.imageSrc = this.baseImageUrl + this.offerDto.image;

  }
}
