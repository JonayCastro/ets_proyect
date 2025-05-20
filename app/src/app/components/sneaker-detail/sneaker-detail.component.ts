import { Component, Input, OnInit } from '@angular/core';
import SneakerDTO from '../../dto/sneaker-dto';

@Component({
  selector: 'app-sneaker-detail',
  templateUrl: './sneaker-detail.component.html',
  styleUrls: ['./sneaker-detail.component.scss']
})
export class SneakerDetailComponent implements OnInit{
  
  @Input() sneakerDto!: SneakerDTO;
  sneakerLink: string = '';
  baseUrl: string = 'https://www.fittestfreakest.es/';

  constructor() {}
  
  ngOnInit(): void {
    this.sneakerLink = this.baseUrl + this.sneakerDto.link;
  }
}
