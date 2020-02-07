import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router:Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      if(sessionStorage.getItem('email')!=null
      && sessionStorage.getItem('email')!=undefined
      && sessionStorage.getItem('email')!=''){
        console.log(sessionStorage.email);
        console.log('inside true auth');
       
        return true;
      }
      else
      {
        this.router.navigate(['login']);
        return false;
      }
   
  }
}
