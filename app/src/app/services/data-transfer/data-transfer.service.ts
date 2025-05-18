import { Injectable } from '@angular/core';
/**
 * DataTransferService is a service that allows data transfer between components.
 * It provides methods to set, get, and clear data.
 * This is useful for sharing data between components without using a state management library.
 */
@Injectable({
  providedIn: 'root'
})
export class DataTransferService {

  /**
   * Optains the data to be transferred between components.
   * @returns The data to be transferred.
   */

  private data: any;

  setData(value: any): void {
    this.data = value;
  }

  getData(): any {
    return this.data;
  }

  clearData(): void {
    this.data = null;
  }
}
