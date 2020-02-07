import { Injectable } from '@angular/core';
import { Input,Output,EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmtrService {

  constructor() { }

  @Output() fire: EventEmitter<any> = new EventEmitter();

  logInBtnSwitch(value) {
    this.fire.emit(value);
  }  

  getEmittedValueForLogbtnSwitch() {
    return this.fire;
  }
}
